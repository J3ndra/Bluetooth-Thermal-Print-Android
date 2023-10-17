package com.junianto.edcsekolah.menu.qris

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.junianto.edcsekolah.AppViewModel
import com.junianto.edcsekolah.R
import com.junianto.edcsekolah.a90.printer.A90PrintManager
import com.junianto.edcsekolah.data.model.Receipt
import com.junianto.edcsekolah.menu.qris.viewmodel.QrisViewModel
import com.junianto.edcsekolah.util.DeviceInfo
import com.junianto.edcsekolah.util.PrintingManager
import com.junianto.edcsekolah.util.formatAmount
import com.junianto.edcsekolah.util.generateQRCode
import com.junianto.edcsekolah.util.getCurrentDate
import com.junianto.edcsekolah.util.getCurrentDateTime
import com.junianto.edcsekolah.util.getCurrentTime
import com.mazenrashed.printooth.Printooth
import com.mazenrashed.printooth.ui.ScanningActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QrisDetailFragment : Fragment() {

    private val appViewModel: AppViewModel by viewModels()
    private val qrisViewModel: QrisViewModel by viewModels()

    private lateinit var amount: String

    private lateinit var schoolName: String
    private lateinit var majorName: String
    private lateinit var schoolLogo: String
    private var isImagePrinted = false
    private var isSdkInitialized = false

    // VIEW
    private lateinit var btnNext: Button
    private lateinit var tvQris: TextView
    private lateinit var ivQris: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_qris_detail, container, false)

        val bundle = arguments
        if (bundle != null) {
            amount = bundle.getString("amount", "") ?: ""
        }

        appViewModel.appSetup.observe(viewLifecycleOwner) {
            schoolName = it.school_name
            majorName = it.major_name
            schoolLogo = it.school_logo
            isImagePrinted = it.is_image_printed
            isSdkInitialized = it.is_sdk_initialized

            updateUI()
        }

        btnNext = rootView.findViewById(R.id.btn_next)
        tvQris = rootView.findViewById(R.id.tv_qris)
        ivQris = rootView.findViewById(R.id.iv_qris)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cpuArchitecture = DeviceInfo.getCpuArchitecture()

        btnNext.setOnClickListener {
            printReceipt()
        }
    }

    private fun updateUI() {
        tvQris.text = buildString {
            append("Total yang harus dibayar adalah ")
            append(formatAmount(amount))
            append(". Silahkan scan QRIS di atas untuk melakukan pembayaran.")
        }

        val qrContent = buildString {
            append(schoolName + "\n")
            append(formatAmount(amount))
        }

        ivQris.setImageBitmap(generateQRCode(qrContent))
    }

    private fun printReceipt() {
        val receipt = Receipt(
            id = 0,
            amount = amount.toInt(),
            date = getCurrentDateTime(),
            cardId = "QRIS",
            paymentType = 3,
        )

        qrisViewModel.insertReceipt(receipt) {
            if (!Printooth.hasPairedPrinter()) {
                if (isSdkInitialized) {
                    A90PrintManager.printReceiptSuccess(
                        context = requireContext(),
                        schoolLogo = schoolLogo,
                        schoolName = schoolName,
                        majorName = majorName,
                        traceId = it.toInt(),
                        date = getCurrentDate(),
                        time = getCurrentTime(),
                        paymentType = 3,
                        amount = amount,
                        cardId = "QRIS",
                        type = "SALE",
                        reprint = false,
                        isImagePrint = isImagePrinted
                    )

                    requireActivity().runOnUiThread {
                        findNavController().navigate(R.id.action_qrisDetailFragment_to_qrisSuccessFragment, Bundle().apply {
                            putInt("traceId", it.toInt())
                            putString("cardId", "QRIS")
                            putString("amount", amount)
                        })
                    }
                } else {
                    Toast.makeText(requireContext(), R.string.please_connect_to_bluetooth_printer, Toast.LENGTH_LONG).show()
                }

            } else {
                PrintingManager.printManager(
                    context = requireContext(),
                    schoolLogo = schoolLogo,
                    schoolName = schoolName,
                    majorName = majorName,
                    traceId = it.toInt(),
                    date = getCurrentDate(),
                    time = getCurrentTime(),
                    paymentType = 3,
                    amount = amount,
                    cardId = "QRIS",
                    type = "SALE",
                    reprint = false,
                    isImagePrint = isImagePrinted
                )

                requireActivity().runOnUiThread {
                    findNavController().navigate(R.id.action_qrisDetailFragment_to_qrisSuccessFragment, Bundle().apply {
                        putInt("traceId", it.toInt())
                        putString("cardId", "QRIS")
                        putString("amount", amount)
                    })
                }
            }
        }
    }
}
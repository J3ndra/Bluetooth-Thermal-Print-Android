package com.junianto.edcsekolah.menu.delete

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.junianto.edcsekolah.AppViewModel
import com.junianto.edcsekolah.R
import com.junianto.edcsekolah.a90.printer.A90PrintManager
import com.junianto.edcsekolah.util.PrintingManager
import com.junianto.edcsekolah.util.formatAmount
import com.junianto.edcsekolah.util.getCurrentDate
import com.junianto.edcsekolah.util.getCurrentTime
import com.mazenrashed.printooth.Printooth
import com.mazenrashed.printooth.ui.ScanningActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class DeleteSuccessFragment : Fragment() {

    private lateinit var cardId: String
    private lateinit var amount: String
    private lateinit var traceId: String
    private var paymentType: Int = 0

    private lateinit var schoolName: String
    private lateinit var majorName: String
    private lateinit var schoolLogo: String
    private var isImagePrinted = false
    private var isSdkInitialized = false

    private lateinit var tvPaymentDesc: TextView
    private lateinit var btnReprintReceipt: Button

    private val appViewModel: AppViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_delete_success, container, false)

        // Retrieve the bundle from arguments
        val bundle = arguments
        if (bundle != null) {
            cardId = bundle.getString("card_id", "") ?: ""
            amount = bundle.getString("amount", "") ?: ""
            traceId = bundle.getString("trace_id", "") ?: ""
            paymentType = bundle.getInt("payment_type", 1)

            Timber.d("DELETE SUCCESS FRAGMENT : $cardId, $amount, $traceId, $paymentType")
        }

        // HANDLE APP VIEWMODEL
        appViewModel.appSetup.observe(viewLifecycleOwner) {
            schoolName = it.school_name
            majorName = it.major_name
            schoolLogo = it.school_logo
            isImagePrinted = it.is_image_printed
            isSdkInitialized = it.is_sdk_initialized
        }

        tvPaymentDesc = rootView.findViewById(R.id.tv_payment_desc)
        btnReprintReceipt = rootView.findViewById(R.id.btn_reprint_receipt)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvPaymentDesc.text = buildString {
            append("Void pembayaran sebesar ")
            append(formatAmount(amount))
            append(" dengan ID ")
            append(traceId)
            append(" berhasil!")
        }

        btnReprintReceipt.setOnClickListener {
            if (!Printooth.hasPairedPrinter()) {
                val scanningIntent = Intent(requireContext(), ScanningActivity::class.java)
                resultLauncher.launch(scanningIntent)
            } else {
                printReceipt()
            }
        }
    }

    /* Inbuilt activity to pair device with printer or select from list of pair bluetooth devices */
    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == ScanningActivity.SCANNING_FOR_PRINTER &&  result.resultCode == Activity.RESULT_OK) {
            printReceipt()
        }
    }

    private fun printReceipt() {
        if (!Printooth.hasPairedPrinter()) {
            if (isSdkInitialized) {
                A90PrintManager.printReceiptSuccess(
                    context = requireContext(),
                    schoolLogo = schoolLogo,
                    schoolName = schoolName,
                    majorName = majorName,
                    traceId = traceId.toInt(),
                    date = getCurrentDate(),
                    time = getCurrentTime(),
                    paymentType = paymentType,
                    amount = amount,
                    cardId = cardId,
                    type = "VOID",
                    reprint = true,
                    isImagePrint = isImagePrinted
                )

                findNavController().navigate(R.id.action_deleteSuccessFragment_to_appFragment)
            } else {
                Toast.makeText(requireContext(), R.string.please_connect_to_bluetooth_printer, Toast.LENGTH_SHORT).show()
            }
        } else {
            PrintingManager.printManager(
                context = requireContext(),
                schoolLogo = schoolLogo,
                schoolName = schoolName,
                majorName = majorName,
                traceId = traceId.toInt(),
                date = getCurrentDate(),
                time = getCurrentTime(),
                paymentType = paymentType,
                amount = amount,
                cardId = cardId,
                type = "VOID",
                reprint = true,
                isImagePrint = isImagePrinted
            )

            findNavController().navigate(R.id.action_deleteSuccessFragment_to_appFragment)
        }
    }
}
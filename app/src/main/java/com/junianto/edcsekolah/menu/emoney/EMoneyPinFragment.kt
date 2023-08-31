package com.junianto.edcsekolah.menu.emoney

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.junianto.edcsekolah.AppViewModel
import com.junianto.edcsekolah.R
import com.junianto.edcsekolah.data.model.Receipt
import com.junianto.edcsekolah.menu.emoney.viewmodel.EMoneyViewModel
import com.junianto.edcsekolah.util.PrintingManager
import com.junianto.edcsekolah.util.getCurrentDate
import com.junianto.edcsekolah.util.getCurrentDateTime
import com.junianto.edcsekolah.util.getCurrentTime
import com.mazenrashed.printooth.Printooth
import com.mazenrashed.printooth.ui.ScanningActivity
import com.mazenrashed.printooth.utilities.Printing
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class EMoneyPinFragment : Fragment() {
    private val eMoneyViewModel: EMoneyViewModel by viewModels()
    private val appViewModel: AppViewModel by viewModels()

    private lateinit var cardId: String
    private lateinit var amount: String
    private lateinit var schoolName: String
    private lateinit var majorName: String
    private lateinit var schoolLogo: String

    // EDIT TEXT
    private lateinit var etPin1: EditText
    private lateinit var etPin2: EditText
    private lateinit var etPin3: EditText
    private lateinit var etPin4: EditText
    private lateinit var etPin5: EditText
    private lateinit var etPin6: EditText

    // BUTTON
    private lateinit var btnPin1: Button
    private lateinit var btnPin2: Button
    private lateinit var btnPin3: Button
    private lateinit var btnPin4: Button
    private lateinit var btnPin5: Button
    private lateinit var btnPin6: Button
    private lateinit var btnPin7: Button
    private lateinit var btnPin8: Button
    private lateinit var btnPin9: Button
    private lateinit var btnPin0: Button
    private lateinit var btnPin000: Button
    private lateinit var btnPinStop: Button
    private lateinit var btnPinClear: Button
    private lateinit var btnPinOk: Button

    private lateinit var pinEditTexts: List<EditText>

    private lateinit var tvSchoolName: TextView
    private lateinit var tvSchoolAddress: TextView
    private lateinit var ivSchoolLogo: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_e_money_pin, container, false)

        // Retrieve the bundle from arguments
        val bundle = arguments
        if (bundle != null) {
            cardId = bundle.getString("cardId", "") ?: ""
            amount = bundle.getString("amount", "") ?: ""
        }

        // SETUP VIEW
        etPin1 = rootView.findViewById(R.id.et_pin_1)
        etPin2 = rootView.findViewById(R.id.et_pin_2)
        etPin3 = rootView.findViewById(R.id.et_pin_3)
        etPin4 = rootView.findViewById(R.id.et_pin_4)
        etPin5 = rootView.findViewById(R.id.et_pin_5)
        etPin6 = rootView.findViewById(R.id.et_pin_6)

        btnPin1 = rootView.findViewById(R.id.btn_1)
        btnPin2 = rootView.findViewById(R.id.btn_2)
        btnPin3 = rootView.findViewById(R.id.btn_3)
        btnPin4 = rootView.findViewById(R.id.btn_4)
        btnPin5 = rootView.findViewById(R.id.btn_5)
        btnPin6 = rootView.findViewById(R.id.btn_6)
        btnPin7 = rootView.findViewById(R.id.btn_7)
        btnPin8 = rootView.findViewById(R.id.btn_8)
        btnPin9 = rootView.findViewById(R.id.btn_9)
        btnPin0 = rootView.findViewById(R.id.btn_0)
        btnPin000 = rootView.findViewById(R.id.btn_000)
        btnPinStop = rootView.findViewById(R.id.btn_stop)
        btnPinClear = rootView.findViewById(R.id.btn_clear)
        btnPinOk = rootView.findViewById(R.id.btn_ok)

        tvSchoolName = rootView.findViewById(R.id.tv_school_name)
        tvSchoolAddress = rootView.findViewById(R.id.tv_school_address)

        ivSchoolLogo = rootView.findViewById(R.id.iv_school_logo)

        pinEditTexts = listOf(etPin1, etPin2, etPin3, etPin4, etPin5, etPin6)

        // HANDLE APP VIEWMODEL
        appViewModel.appSetup.observe(viewLifecycleOwner) {
            schoolName = it.school_name
            majorName = it.major_name

            tvSchoolName.text = schoolName
            tvSchoolAddress.text = it.school_address

            schoolLogo = it.school_logo

            if (schoolLogo == "") {
                ivSchoolLogo.setImageResource(R.drawable.tutwuri_logo)
            } else {
                ivSchoolLogo.setImageURI(Uri.parse(it.school_logo))
            }
        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnPin1.setOnClickListener {
            appendPinNumber("1")
        }
        btnPin2.setOnClickListener {
            appendPinNumber("2")
        }
        btnPin3.setOnClickListener {
            appendPinNumber("3")
        }
        btnPin4.setOnClickListener {
            appendPinNumber("4")
        }
        btnPin5.setOnClickListener {
            appendPinNumber("5")
        }
        btnPin6.setOnClickListener {
            appendPinNumber("6")
        }
        btnPin7.setOnClickListener {
            appendPinNumber("7")
        }
        btnPin8.setOnClickListener {
            appendPinNumber("8")
        }
        btnPin9.setOnClickListener {
            appendPinNumber("9")
        }
        btnPin0.setOnClickListener {
            appendPinNumber("0")
        }

        // BUTTON FUNCTION
        btnPinOk.setOnClickListener {
            processPin()
        }
        btnPinClear.setOnClickListener {
            clearPin()
        }
        btnPinStop.setOnClickListener {
            findNavController().navigate(R.id.action_EMoneyPinFragment_to_menuFragment)
        }
    }

    private fun appendPinNumber(number: String) {
        for (i in pinEditTexts.indices) {
            val editText = pinEditTexts[i]
            if (editText.text.isEmpty()) {
                editText.setText(number)
                if (i < pinEditTexts.size - 1) {
                    pinEditTexts[i + 1].requestFocus()
                }
                break
            }
        }
    }

    private fun clearPin() {
        for (editText in pinEditTexts) {
            editText.text.clear()
        }
        etPin1.requestFocus()
    }

    private fun processPin() {
        val enteredPin = pinEditTexts.joinToString("") { it.text.toString() }

        if(enteredPin == "000000") {
            if (!Printooth.hasPairedPrinter()) {
                val scanningIntent = Intent(requireContext(), ScanningActivity::class.java)
                resultLauncher.launch(scanningIntent)
            } else {
                printReceipt()
            }
        } else {
            Toast.makeText(requireContext(), "PIN Salah", Toast.LENGTH_SHORT).show()
        }
        // Perform validation or processing with enteredPin
    }

    /* Inbuilt activity to pair device with printer or select from list of pair bluetooth devices */
    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == ScanningActivity.SCANNING_FOR_PRINTER &&  result.resultCode == Activity.RESULT_OK) {
            printReceipt()
        }
    }

    private fun printReceipt() {

        val receipt = Receipt(
            0,
            amount.toInt(),
            getCurrentDateTime(),
            cardId,
            true
        )

        eMoneyViewModel.insertReceipt(receipt) { insertedId ->
            PrintingManager.printManager(
                context = requireContext(),
                schoolLogo = schoolLogo,
                schoolName = schoolName,
                majorName = majorName,
                traceId = insertedId.toInt(),
                date = getCurrentDate(),
                time = getCurrentTime(),
                status = true,
                amount = amount,
                cardId = cardId,
                type = "SALE",
                reprint = false,
            )

            requireActivity().runOnUiThread {
                findNavController().navigate(R.id.action_EMoneyPinFragment_to_EMoneySuccessFragment, Bundle().apply {
                    putInt("traceId", insertedId.toInt())
                    putString("cardId", cardId)
                    putString("amount", amount)
                })

                Timber.d("TRACE ID FROM BUNDLE : $insertedId")
            }
        }
    }
}
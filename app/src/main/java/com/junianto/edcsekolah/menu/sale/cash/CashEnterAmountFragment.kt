package com.junianto.edcsekolah.menu.sale.cash

import android.app.AlertDialog
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
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.junianto.edcsekolah.AppViewModel
import com.junianto.edcsekolah.R
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.text.NumberFormat
import java.util.Locale

@AndroidEntryPoint
class CashEnterAmountFragment : Fragment() {

    private val appViewModel: AppViewModel by viewModels()

    private lateinit var ivSchoolLogo: ImageView
    private lateinit var schoolLogo: String

    // EDIT TEXT
    private lateinit var etAmount: EditText

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

    // TEXT VIEW
    private lateinit var tvSchoolName: TextView
    private lateinit var tvSchoolAddress: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_cash_enter_amount, container, false)

        // SETUP VIEW
        ivSchoolLogo = rootView.findViewById(R.id.iv_school_logo)

        etAmount = rootView.findViewById(R.id.et_amount)

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

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appViewModel.appSetup.observe(viewLifecycleOwner) { appSetup ->
            schoolLogo = appSetup.school_logo

            tvSchoolName.text = appSetup.school_name
            tvSchoolAddress.text = appSetup.school_address

            if (appSetup.school_logo == "") {
                ivSchoolLogo.setImageResource(R.drawable.tutwuri_logo)
            } else {
                ivSchoolLogo.setImageURI(Uri.parse(appSetup.school_logo))
            }
        }

        btnPin1.setOnClickListener { appendAmount("1") }
        btnPin2.setOnClickListener { appendAmount("2") }
        btnPin3.setOnClickListener { appendAmount("3") }
        btnPin4.setOnClickListener { appendAmount("4") }
        btnPin5.setOnClickListener { appendAmount("5") }
        btnPin6.setOnClickListener { appendAmount("6") }
        btnPin7.setOnClickListener { appendAmount("7") }
        btnPin8.setOnClickListener { appendAmount("8") }
        btnPin9.setOnClickListener { appendAmount("9") }
        btnPin0.setOnClickListener { appendAmount("0") }
        btnPin000.setOnClickListener { appendAmount("000") }

        btnPinClear.setOnClickListener {
            clearLastDigit()
        }
        btnPinStop.setOnClickListener {
            findNavController().popBackStack()
        }
        btnPinOk.setOnClickListener {
            Timber.d("Amount: ${etAmount.text}")
            if (etAmount.text.toString() != "0" && etAmount.text.toString() != "") {
                findNavController().navigate(R.id.action_cashEnterAmountFragment_to_cashEnterPinFragment, Bundle().apply {
                    putString("amount", etAmount.text.toString().replace(".", "").replace(",", ""))
                })
            } else {
                AlertDialog.Builder(requireContext())
                    .setTitle("Perhatian")
                    .setMessage("Masukkan nominal terlebih dahulu")
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
    }

    private fun appendAmount(amount: String) {
        val currentValue = etAmount.text.toString().replace(".", "").replace(",", "")

        // Append the new amount to the current value
        val newValue = currentValue + amount

        // Convert the formatted string to a numeric value
        val numericValue = newValue.replace(",", "").toLong()

        // Format the numericValue with thousands separators
        val formattedValue = NumberFormat.getInstance().format(numericValue)

        // Convert formattedValue to string
        val formattedValueString = formattedValue.toString().replace(",", ".")

        etAmount.setText(formattedValueString)
    }

    private fun clearLastDigit() {
        val currentValue = etAmount.text.toString().replace(".", "").replace(",", "")

        if (currentValue.isNotEmpty()) {
            val newValue = currentValue.substring(0, currentValue.length - 1)

            // Convert the string value back to a Long
            val numericValue = if (newValue.isNotEmpty()) newValue.toLong() else 0

            // Format the numericValue with thousands separators using a custom format
            val customFormat = NumberFormat.getInstance(Locale.US)
            customFormat.isGroupingUsed = true
            customFormat.minimumFractionDigits = 0
            customFormat.maximumFractionDigits = 0

            // Format the numericValue using the custom format
            val formattedValue = customFormat.format(numericValue)

            val formattedValueString = formattedValue.toString().replace(",", ".")

            etAmount.setText(formattedValueString)
        }
    }
}
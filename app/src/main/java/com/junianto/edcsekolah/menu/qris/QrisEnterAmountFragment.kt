package com.junianto.edcsekolah.menu.qris

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
import com.junianto.edcsekolah.util.appendAmount
import com.junianto.edcsekolah.util.clearLastDigit
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class QrisEnterAmountFragment : Fragment() {

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

        btnPin1.setOnClickListener { appendAmount("1", etAmount) }
        btnPin2.setOnClickListener { appendAmount("2", etAmount) }
        btnPin3.setOnClickListener { appendAmount("3", etAmount) }
        btnPin4.setOnClickListener { appendAmount("4", etAmount) }
        btnPin5.setOnClickListener { appendAmount("5", etAmount) }
        btnPin6.setOnClickListener { appendAmount("6", etAmount) }
        btnPin7.setOnClickListener { appendAmount("7", etAmount) }
        btnPin8.setOnClickListener { appendAmount("8", etAmount) }
        btnPin9.setOnClickListener { appendAmount("9", etAmount) }
        btnPin0.setOnClickListener { appendAmount("0", etAmount) }
        btnPin000.setOnClickListener { appendAmount("000", etAmount) }

        btnPinClear.setOnClickListener {
            clearLastDigit(etAmount)
        }
        btnPinStop.setOnClickListener {
            findNavController().popBackStack()
        }
        btnPinOk.setOnClickListener {
            Timber.d("Amount: ${etAmount.text}")
            if (etAmount.text.toString() != "0" && etAmount.text.toString() != "") {
                findNavController().navigate(R.id.action_qrisEnterAmountFragment_to_qrisEnterPinFragment, Bundle().apply {
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
}
package com.junianto.edcsekolah.menu.delete

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
import com.junianto.edcsekolah.menu.delete.viewmodel.VoidViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchReceiptFragment : Fragment() {

    private val appViewModel: AppViewModel by viewModels()
    private val voidViewModel: VoidViewModel by viewModels()

    private lateinit var tvSchoolName: TextView
    private lateinit var tvSchoolAddress: TextView
    private lateinit var ivSchoolLogo: ImageView

    private lateinit var etTraceId: EditText

    private lateinit var schoolLogo: String

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_search_receipt, container, false)

        tvSchoolName = rootView.findViewById(R.id.tv_school_name)
        tvSchoolAddress = rootView.findViewById(R.id.tv_school_address)
        etTraceId = rootView.findViewById(R.id.et_trace_id)
        ivSchoolLogo = rootView.findViewById(R.id.iv_school_logo)

        // SETUP VIEW
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

        // HANDLE APP VIEWMODEL
        appViewModel.appSetup.observe(viewLifecycleOwner) {
            tvSchoolName.text = it.school_name
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
            etTraceId.append("1")
        }
        btnPin2.setOnClickListener {
            etTraceId.append("2")
        }
        btnPin3.setOnClickListener {
            etTraceId.append("3")
        }
        btnPin4.setOnClickListener {
            etTraceId.append("4")
        }
        btnPin5.setOnClickListener {
            etTraceId.append("5")
        }
        btnPin6.setOnClickListener {
            etTraceId.append("6")
        }
        btnPin7.setOnClickListener {
            etTraceId.append("7")
        }
        btnPin8.setOnClickListener {
            etTraceId.append("8")
        }
        btnPin9.setOnClickListener {
            etTraceId.append("9")
        }
        btnPin0.setOnClickListener {
            etTraceId.append("0")
        }
        btnPin000.setOnClickListener {
            etTraceId.append("000")
        }
        btnPinClear.setOnClickListener {
            etTraceId.setText("")
        }
        btnPinStop.setOnClickListener {
            findNavController().popBackStack()
        }
        btnPinOk.setOnClickListener {
            voidViewModel.didReceiptExist(etTraceId.text.toString().toInt()).observe(viewLifecycleOwner) { exists ->
                // Handle the result asynchronously
                if (exists) {
                    findNavController().navigate(
                        R.id.action_searchReceiptFragment_to_deleteFragment,
                        Bundle().apply {
                            putString("trace_id", etTraceId.text.toString())
                        })
                } else {
                    AlertDialog.Builder(requireContext())
                        .setTitle("Error")
                        .setMessage("Receipt not found")
                        .setPositiveButton("OK") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .show()
                }
            }
        }
    }
}
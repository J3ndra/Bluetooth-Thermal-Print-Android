package com.junianto.edcsekolah.menu.delete

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.junianto.edcsekolah.AppViewModel
import com.junianto.edcsekolah.R
import com.junianto.edcsekolah.menu.delete.viewmodel.VoidViewModel
import com.junianto.edcsekolah.util.formatAmount
import com.junianto.edcsekolah.util.getCurrentDate
import com.junianto.edcsekolah.util.getCurrentTime
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeleteFragment : Fragment() {

    private val appViewModel: AppViewModel by viewModels()
    private val voidViewModel: VoidViewModel by viewModels()

    private lateinit var schoolName: String
    private lateinit var schoolAddress: String

    private lateinit var traceId: String
    private lateinit var amount: String
    private lateinit var cardId: String
    private var paymentType: Int = 0

    private lateinit var tvSchoolName: TextView
    private lateinit var tvSchoolName2: TextView
    private lateinit var tvSchoolAddress: TextView
    private lateinit var tvDate: TextView
    private lateinit var tvTime: TextView
    private lateinit var tvTraceId: TextView
    private lateinit var tvCardId: TextView
    private lateinit var tvAmount: TextView

    private lateinit var btnVoid: Button

    private lateinit var ivSchoolLogo: ImageView
    private lateinit var schoolLogo: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_delete, container, false)

        // Retrieve the bundle from arguments
        val bundle = arguments
        if (bundle != null) {
            traceId = bundle.getString("trace_id", "") ?: ""
        }

        tvSchoolName = rootView.findViewById(R.id.tv_school_name)
        tvSchoolName2 = rootView.findViewById(R.id.tv_school_name_2)
        tvSchoolAddress = rootView.findViewById(R.id.tv_school_address)
        tvDate = rootView.findViewById(R.id.tv_date)
        tvTime = rootView.findViewById(R.id.tv_time)
        tvTraceId = rootView.findViewById(R.id.tv_trace_id)
        tvCardId = rootView.findViewById(R.id.tv_card_id)
        tvAmount = rootView.findViewById(R.id.tv_amount)

        btnVoid = rootView.findViewById(R.id.btn_void)

        ivSchoolLogo = rootView.findViewById(R.id.iv_school_logo)

        // HANDLE APP VIEWMODEL
        appViewModel.appSetup.observe(viewLifecycleOwner) {
            schoolName = it.school_name
            schoolAddress = it.school_address

            schoolLogo = it.school_logo

            if (schoolLogo == "") {
                ivSchoolLogo.setImageResource(R.drawable.tutwuri_logo)
            } else {
                ivSchoolLogo.setImageURI(Uri.parse(it.school_logo))
            }
        }

        // HANDLE VOID VIEWMODEL
        voidViewModel.searchReceiptById(traceId.toInt())

        return  rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        voidViewModel.receiptDetail.observe(viewLifecycleOwner) {
            tvSchoolName.text = schoolName
            tvSchoolName2.text = schoolName
            tvSchoolAddress.text = schoolAddress
            tvDate.text = buildString {
                append("DATE : ")
                append(getCurrentDate())
            }
            tvTime.text = buildString {
                append("TIME : ")
                append(getCurrentTime())
            }
            tvTraceId.text = buildString {
                append("TRACE ID : ")
                append(it.id)
            }
            tvCardId.text = buildString {
                append("CARD ID : ")
                append(it.cardId)
            }
            tvAmount.text = buildString {
                append("- ")
                append(formatAmount(it.amount.toString()))
            }

            amount = it.amount.toString()
            cardId = it.cardId
            paymentType = it.paymentType
        }

        btnVoid.setOnClickListener {
            findNavController().navigate(R.id.action_deleteFragment_to_deletePinFragment, Bundle().apply {
                putString("trace_id", traceId)
                putString("amount", amount)
                putString("card_id", cardId)
                putInt("payment_type", paymentType)
            })
        }
    }
}
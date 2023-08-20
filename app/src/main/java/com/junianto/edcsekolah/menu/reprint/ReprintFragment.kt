package com.junianto.edcsekolah.menu.reprint

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.junianto.edcsekolah.AppViewModel
import com.junianto.edcsekolah.R
import com.junianto.edcsekolah.data.model.Receipt
import com.junianto.edcsekolah.menu.reprint.adapter.ReprintAdapter
import com.junianto.edcsekolah.menu.reprint.adapter.ReprintButtonClickListener
import com.junianto.edcsekolah.menu.reprint.viewmodel.ReprintViewModel
import com.junianto.edcsekolah.util.PrintingManager
import com.junianto.edcsekolah.util.getCurrentDate
import com.junianto.edcsekolah.util.getCurrentTime
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ReprintFragment : Fragment(), ReprintButtonClickListener {

    private val appViewModel: AppViewModel by viewModels()
    private val reprintViewModel: ReprintViewModel by viewModels()

    private lateinit var reprintAdapter: ReprintAdapter
    private lateinit var recyclerView: RecyclerView

    private var receipts: List<Receipt> = emptyList()

    private lateinit var schoolName: String
    private lateinit var majorName: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_reprint, container, false)

        recyclerView = rootView.findViewById(R.id.rv_reprint)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        reprintAdapter = ReprintAdapter()
        reprintAdapter.reprintButtonClickListener(this)
        recyclerView.adapter = reprintAdapter

        reprintViewModel.allReceipts.observe(viewLifecycleOwner) { receiptList ->
            receipts = receiptList
            reprintAdapter.setReceipts(receipts)
        }

        appViewModel.appSetup.observe(viewLifecycleOwner) { appSetup ->
            schoolName = appSetup.school_name
            majorName = appSetup.major_name
        }

        return rootView
    }

    override fun onReprintButtonClick(receipt: Receipt) {
        Timber.d("REPRINT FROM FRAGMENT : ${receipt.id}")
        PrintingManager.printManager(
            requireContext(),
            schoolName,
            majorName,
            receipt.id,
            getCurrentDate(),
            getCurrentTime(),
            receipt.status,
            receipt.cardId,
            receipt.amount.toString(),
            "SALE",
            true,
        )
    }
}
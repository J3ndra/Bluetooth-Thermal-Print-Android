package com.junianto.edcsekolah.menu.settlement

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.junianto.edcsekolah.AppViewModel
import com.junianto.edcsekolah.R
import com.junianto.edcsekolah.a90.printer.A90PrintManager
import com.junianto.edcsekolah.data.model.Receipt
import com.junianto.edcsekolah.menu.settlement.adapter.SettlementAdapter
import com.junianto.edcsekolah.menu.settlement.viewmodel.SettlementViewModel
import com.junianto.edcsekolah.util.ImageSaver
import com.junianto.edcsekolah.util.formatAmount
import com.junianto.edcsekolah.util.getCurrentDate
import com.junianto.edcsekolah.util.getCurrentTime
import com.junianto.edcsekolah.util.loadAndResizeBitmap
import com.mazenrashed.printooth.Printooth
import com.mazenrashed.printooth.data.printable.ImagePrintable
import com.mazenrashed.printooth.data.printable.Printable
import com.mazenrashed.printooth.data.printable.TextPrintable
import com.mazenrashed.printooth.data.printer.DefaultPrinter
import com.mazenrashed.printooth.ui.ScanningActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettlementFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SettlementAdapter

    private val viewModel: SettlementViewModel by viewModels()
    private val appViewModel: AppViewModel by viewModels()

    private lateinit var schoolName: String
    private lateinit var majorName: String
    private lateinit var schoolLogo: String
    private var isImagePrinted = false
    private var isSdkInitialized = false

    private var receipts: List<Receipt> = emptyList()

    private lateinit var tvTotalAmount: TextView
    private lateinit var btnSettlement: Button

    var totalAmount = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_settlement, container, false)

        tvTotalAmount = rootView.findViewById(R.id.tv_total_amount)
        btnSettlement = rootView.findViewById(R.id.btn_settlements)

        recyclerView = rootView.findViewById(R.id.rv_receipts)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = SettlementAdapter()
        recyclerView.adapter = adapter

        viewModel.allReceipts.observe(viewLifecycleOwner) { receipts ->
            this.receipts = receipts
            adapter.setReceipt(receipts) // Update the adapter's data
            totalAmount = calculateTotalAmount(receipts)
            tvTotalAmount.text = formatAmount(totalAmount.toString())
        }

        // HANDLE APP VIEWMODEL
        appViewModel.appSetup.observe(viewLifecycleOwner) {
            schoolName = it.school_name
            majorName = it.major_name
            schoolLogo = it.school_logo
            isImagePrinted = it.is_image_printed
            isSdkInitialized = it.is_sdk_initialized
        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSettlement.setOnClickListener {
            if (!Printooth.hasPairedPrinter()) {
                if (isSdkInitialized) {
                    A90PrintManager.printSettlement(
                        context = requireContext(),
                        schoolLogo = schoolLogo,
                        schoolName = schoolName,
                        majorName = majorName,
                        date = getCurrentDate(),
                        time = getCurrentTime(),
                        type = "SALE",
                        reprint = true,
                        isImagePrint = isImagePrinted,
                        receipts = receipts,
                    )

                    lifecycleScope.launch {
                        viewModel.deleteAllReceiptsAndResetId()
                    }

                    findNavController().navigate(R.id.action_settlementFragment_to_appFragment)
                } else {
                    Toast.makeText(requireContext(), R.string.please_connect_to_bluetooth_printer, Toast.LENGTH_SHORT).show()
                }
            } else {
                printSettlement(schoolName, majorName, isImagePrinted)

                lifecycleScope.launch {
                    viewModel.deleteAllReceiptsAndResetId()
                }

                findNavController().navigate(R.id.action_settlementFragment_to_appFragment)
            }
        }
    }

    private fun calculateTotalAmount(receipts: List<Receipt>): Int {
        return receipts.sumOf { it.amount }
    }

    private fun printSettlement(
        schoolName: String,
        majorName: String,
        isImagePrinted: Boolean,
    ) {
        val printables = ArrayList<Printable>()

        val option = BitmapFactory.Options().apply {
            inSampleSize = 3
        }

        val tutWuriLogo = ImagePrintable.Builder(BitmapFactory.decodeResource(requireContext().resources, R.drawable.tut_wuri_logo_2, option))
            .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
            .build()

        val smkText = TextPrintable.Builder()
            .setText("SMK\n")
            .setEmphasizedMode(DefaultPrinter.EMPHASIZED_MODE_BOLD)
            .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
            .setFontSize(DefaultPrinter.FONT_SIZE_LARGE)
            .build()
        val majorText = TextPrintable.Builder()
            .setText("$majorName\n")
            .setFontSize(DefaultPrinter.FONT_SIZE_NORMAL)
            .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
            .build()
        val schoolText = TextPrintable.Builder()
            .setText("$schoolName\n")
            .setEmphasizedMode(DefaultPrinter.EMPHASIZED_MODE_BOLD)
            .setFontSize(DefaultPrinter.FONT_SIZE_NORMAL)
            .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
            .build()
        val edcNoText = TextPrintable.Builder()
            .setText("""
                EDC No. 493.24 TYPE 101
                23112022
            """.trimIndent() + "\n")
            .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
            .setFontSize(DefaultPrinter.FONT_SIZE_NORMAL)
            .build()
        val linedText = TextPrintable.Builder()
            .setText("================================\n")
            .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
            .build()
        val oneLinedText = TextPrintable.Builder()
            .setText("--------------------------------\n")
            .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
            .build()
        val terminalIdText = TextPrintable.Builder()
            .setText("TERMINAL ID : 000000\n")
            .setAlignment(DefaultPrinter.ALIGNMENT_LEFT)
            .setFontSize(DefaultPrinter.FONT_SIZE_NORMAL)
            .build()
        val merchantIdText = TextPrintable.Builder()
            .setText("MERCHANT ID : 000000000\n")
            .setAlignment(DefaultPrinter.ALIGNMENT_LEFT)
            .setFontSize(DefaultPrinter.FONT_SIZE_NORMAL)
            .build()
        val reffText = TextPrintable.Builder()
            .setText("REFF NO : 000000\n")
            .setAlignment(DefaultPrinter.ALIGNMENT_LEFT)
            .setFontSize(DefaultPrinter.FONT_SIZE_NORMAL)
            .build()
        val aprvText = TextPrintable.Builder()
            .setText("APRV NO : 000000\n")
            .setAlignment(DefaultPrinter.ALIGNMENT_LEFT)
            .setFontSize(DefaultPrinter.FONT_SIZE_NORMAL)
            .build()
        val batchNoText = TextPrintable.Builder()
            .setText("BATCH NO : 000000\n")
            .setAlignment(DefaultPrinter.ALIGNMENT_LEFT)
            .setFontSize(DefaultPrinter.FONT_SIZE_NORMAL)
            .build()
        val settlementText = TextPrintable.Builder()
            .setText("SETTLEMENT OF GOODS\n")
            .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
            .setFontSize(DefaultPrinter.FONT_SIZE_NORMAL)
            .build()
        val copyText = TextPrintable.Builder()
            .setText("** BANK COPY **")
            .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
            .setFontSize(DefaultPrinter.FONT_SIZE_NORMAL)
            .setNewLinesAfter(3)
            .build()
        val totalAmountText = TextPrintable.Builder()
            .setText("TOTAL AMOUNT : ${formatAmount(totalAmount.toString())}\n")
            .setAlignment(DefaultPrinter.ALIGNMENT_LEFT)
            .setFontSize(DefaultPrinter.FONT_SIZE_NORMAL)
            .build()

        if (isImagePrinted) {
            if (schoolLogo == "") {
                printables.add(tutWuriLogo)
            } else {
                val bitmap: Bitmap? = ImageSaver(requireContext())
                    .setFileName("school_logo.png")
                    .setDirectoryName("images")
                    .load(option)

                bitmap?.let {
                    val printableImage = ImagePrintable.Builder(it)
                        .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
                        .setNewLinesAfter(1)
                        .build()

                    printables.add(printableImage)
                }
            }
        }
        printables.add(smkText)
        printables.add(majorText)
        printables.add(schoolText)
        printables.add(edcNoText)
        printables.add(linedText)
        printables.add(terminalIdText)
        printables.add(merchantIdText)
        printables.add(reffText)
        printables.add(aprvText)
        printables.add(batchNoText)

        for (i in receipts.indices) {
            val traceIdText = TextPrintable.Builder()
                .setText("TRACE ID : ${receipts[i].id}\n")
                .setAlignment(DefaultPrinter.ALIGNMENT_LEFT)
                .setFontSize(DefaultPrinter.FONT_SIZE_NORMAL)
                .build()
            val dateText = TextPrintable.Builder()
                .setText("DATE : ${receipts[i].date}\n")
                .setAlignment(DefaultPrinter.ALIGNMENT_LEFT)
                .setFontSize(DefaultPrinter.FONT_SIZE_NORMAL)
                .build()

            var typeText = ""
            when (receipts[i].paymentType) {
                1 -> typeText = "CASH"
                2 -> typeText = "NFC"
                3 -> typeText = "QR"
                4 -> typeText = "IC"
                5 -> typeText = "MAGNETIC"
            }
            val paymentTypeText = TextPrintable.Builder()
                .setText("TYPE : $typeText\n")
                .setAlignment(DefaultPrinter.ALIGNMENT_LEFT)
                .setFontSize(DefaultPrinter.FONT_SIZE_NORMAL)
                .build()
            val amountText = TextPrintable.Builder()
                .setText("AMOUNT : ${formatAmount(receipts[i].amount.toString())}\n")
                .setAlignment(DefaultPrinter.ALIGNMENT_LEFT)
                .setFontSize(DefaultPrinter.FONT_SIZE_NORMAL)
                .build()
            printables.add(oneLinedText)
            printables.add(traceIdText)
            printables.add(dateText)
            printables.add(paymentTypeText)
            printables.add(amountText)
        }
        printables.add(linedText)
        printables.add(totalAmountText)
        printables.add(linedText)
        printables.add(settlementText)
        printables.add(copyText)

        Printooth.printer().print(printables)
    }

    /* Inbuilt activity to pair device with printer or select from list of pair bluetooth devices */
    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == ScanningActivity.SCANNING_FOR_PRINTER &&  result.resultCode == Activity.RESULT_OK) {
            printSettlement(schoolName, majorName, isImagePrinted)
        }
    }
}
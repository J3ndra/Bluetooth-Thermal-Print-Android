package com.junianto.edcsekolah.menu.settings.setup

import android.app.Activity
import android.bluetooth.BluetoothSocket
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.junianto.edcsekolah.a90.printer.A90PrintManager
import com.junianto.edcsekolah.AppViewModel
import com.junianto.edcsekolah.R
import com.junianto.edcsekolah.util.ImageSaver
import com.junianto.edcsekolah.util.loadAndResizeBitmap
import com.mazenrashed.printooth.Printooth
import com.mazenrashed.printooth.data.printable.ImagePrintable
import com.mazenrashed.printooth.data.printable.Printable
import com.mazenrashed.printooth.data.printable.TextPrintable
import com.mazenrashed.printooth.data.printer.DefaultPrinter
import com.mazenrashed.printooth.ui.ScanningActivity
import com.mazenrashed.printooth.utilities.Printing
import com.mazenrashed.printooth.utilities.PrintingCallback
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.io.OutputStream

@AndroidEntryPoint
class SetupFragment : Fragment(), PrintingCallback {

    private val appViewModel: AppViewModel by viewModels()

    private lateinit var etSchoolName: EditText
    private lateinit var etMajorName: EditText
    private lateinit var etSchoolAddress: EditText

    private lateinit var btnSave: Button
    private lateinit var btnCetak: Button
    private lateinit var btnPrintLoop: Button

    private lateinit var ivSchoolLogo: ImageView

    // PRINTING SETUP
    private var printing: Printing? = null

    private lateinit var schoolLogo: String

    private lateinit var btsocket: BluetoothSocket
    private lateinit var outputStream: OutputStream

    private val FONT_TYPE: Byte = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etSchoolName = view.findViewById(R.id.et_school_name)
        etSchoolAddress = view.findViewById(R.id.et_school_address)
        etMajorName = view.findViewById(R.id.et_major_name)

        ivSchoolLogo = view.findViewById(R.id.iv_school_logo)

        btnPrintLoop = view.findViewById(R.id.btn_print_loop)
        btnPrintLoop.setOnClickListener {
            if (!Printooth.hasPairedPrinter()) {
                val scanningIntent = Intent(requireContext(), ScanningActivity::class.java)
                resultLauncher.launch(scanningIntent)
            } else {
                printLoop()
            }

            /* callback from printooth to get printer process */
            printing?.printingCallback = object : PrintingCallback {
                override fun connectingWithPrinter() {
                    Toast.makeText(requireContext(), "Connecting with printer", Toast.LENGTH_SHORT).show()
                    Timber.d("PRINTER STATUS : Connecting with printer")
                }

                override fun printingOrderSentSuccessfully() {
                    Toast.makeText(requireContext(), "Order sent to printer", Toast.LENGTH_SHORT).show()
                    Timber.d("PRINTER STATUS : Order sent to printer")
                }

                override fun connectionFailed(error: String) {
                    Toast.makeText(requireContext(), "Failed to connect printer", Toast.LENGTH_SHORT).show()
                    Timber.d("PRINTER STATUS : Failed to connect printer")
                }

                override fun onError(error: String) {
                    Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
                    Timber.d("PRINTER STATUS : $error")
                }

                override fun onMessage(message: String) {
                    Toast.makeText(requireContext(), "Message: $message", Toast.LENGTH_SHORT).show()
                    Timber.d("PRINTER STATUS : Message: $message")
                }

                override fun disconnected() {
                    Toast.makeText(requireContext(), "Disconnected Printer", Toast.LENGTH_SHORT).show()
                    Timber.d("PRINTER STATUS : Disconnected Printer")
                }

            }
        }

        btnSave = view.findViewById(R.id.btn_save)
        btnSave.setOnClickListener {
            val schoolName = etSchoolName.text.toString()
            val schoolAddress = etSchoolAddress.text.toString()
            val majorName = etMajorName.text.toString()

            ImageSaver(requireContext())
                .setFileName("school_logo.png")
                .setDirectoryName("images")
                .save(ivSchoolLogo.drawable.toBitmap())

            appViewModel.updateAppSetup(schoolName, schoolAddress, majorName, schoolLogo)
        }

        appViewModel.appSetup.observe(viewLifecycleOwner) { appSetup ->
            // Populate the UI with the retrieved app setup data
            etSchoolName.setText(appSetup.school_name)
            etSchoolAddress.setText(appSetup.school_address)
            etMajorName.setText(appSetup.major_name)

            schoolLogo = appSetup.school_logo

            if (schoolLogo == "") {
                ivSchoolLogo.setImageResource(R.drawable.tutwuri_logo)
            } else {
                val bitmap: Bitmap? = ImageSaver(requireContext())
                    .setFileName("school_logo.png")
                    .setDirectoryName("images")
                    .load()

                ivSchoolLogo.setImageBitmap(bitmap)
                
//                ivSchoolLogo.setImageURI(Uri.parse(appSetup.school_logo))
            }
        }

        btnCetak = view.findViewById(R.id.btn_cetak)
        btnCetak.setOnClickListener {
            if (!Printooth.hasPairedPrinter()) {
                A90PrintManager.printReceiptText(requireContext())
            } else {
                printDetails()
            }
        }

        ivSchoolLogo.setOnClickListener {
            pickMediaLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    }

    private val pickMediaLauncher = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            schoolLogo = uri.toString()

//            val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION and Intent.FLAG_GRANT_WRITE_URI_PERMISSION
//
//            context?.contentResolver?.takePersistableUriPermission(uri, flag)

            Glide.with(requireContext())
                .load(uri)
                .into(ivSchoolLogo)
        }
    }

    /* Inbuilt activity to pair device with printer or select from list of pair bluetooth devices */
    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == ScanningActivity.SCANNING_FOR_PRINTER &&  result.resultCode == Activity.RESULT_OK) {
            printDetails()
        }
    }

    private fun printLoop() {
        val printables = ArrayList<Printable>()

        for (i in 0..10) {
            val textPrintable = TextPrintable.Builder()
                .setText("Hello World $i\n")
                .build()
            printables.add(textPrintable)
        }

        Printooth.printer().print(printables)
    }

    private fun printDetails() {
        val printables = ArrayList<Printable>()

        val tutWuriLogo = loadAndResizeBitmap(requireContext(), schoolLogo)?.let {
            ImagePrintable.Builder(it)
                .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
                .build()
        }
        val smkText = TextPrintable.Builder()
            .setText("SMK\n")
            .setCharacterCode(DefaultPrinter.CHARCODE_PC1252)
            .setEmphasizedMode(DefaultPrinter.EMPHASIZED_MODE_BOLD)
            .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
            .setFontSize(DefaultPrinter.FONT_SIZE_LARGE)
            .build()
        val majorText = appViewModel.appSetup.value?.major_name?.let {
            TextPrintable.Builder()
                .setText(it + "\n")
                .setCharacterCode(DefaultPrinter.CHARCODE_PC1252)
                .setFontSize(DefaultPrinter.FONT_SIZE_NORMAL)
                .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
                .build()
        }
        val edcNoText = TextPrintable.Builder()
            .setText("""
                EDC No. 493.24 TYPE 101
                23112022
            """.trimIndent() + "\n")
            .setCharacterCode(DefaultPrinter.CHARCODE_PC1252)
            .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
            .setFontSize(DefaultPrinter.FONT_SIZE_NORMAL)
            .build()
        val linedText = TextPrintable.Builder()
            .setText("================================\n")
            .setCharacterCode(DefaultPrinter.CHARCODE_PC1252)
            .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
            .build()
        val leftText = TextPrintable.Builder()
            .setText("LEFT TEXT\n")
            .setCharacterCode(DefaultPrinter.CHARCODE_PC1252)
            .setAlignment(DefaultPrinter.ALIGNMENT_LEFT)
            .setFontSize(DefaultPrinter.FONT_SIZE_NORMAL)
            .build()
        val centerText = TextPrintable.Builder()
            .setText("CENTER TEXT\n")
            .setCharacterCode(DefaultPrinter.CHARCODE_PC1252)
            .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
            .setFontSize(DefaultPrinter.FONT_SIZE_NORMAL)
            .build()
        val rightText = TextPrintable.Builder()
            .setText("RIGHT TEXT\n")
            .setCharacterCode(DefaultPrinter.CHARCODE_PC1252)
            .setAlignment(DefaultPrinter.ALIGNMENT_RIGHT)
            .setFontSize(DefaultPrinter.FONT_SIZE_NORMAL)
            .build()
        val blankText = TextPrintable.Builder()
            .setText("")
            .setCharacterCode(DefaultPrinter.CHARCODE_PC1252)
            .setNewLinesAfter(1)
            .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
            .setFontSize(DefaultPrinter.FONT_SIZE_NORMAL)
            .build()


        if (tutWuriLogo != null) {
            printables.add(tutWuriLogo)
        }
        printables.add(smkText)
        if (majorText != null) {
            printables.add(majorText)
        }
        printables.add(edcNoText)
        printables.add(linedText)

        val mWriter = MultiFormatWriter()

        try {
            val mMatrix = mWriter.encode("""
                EDC SEKOLAH (SAMPLE)
                CREATED BY JUNIANTO
            """.trimIndent(), BarcodeFormat.QR_CODE, 256, 256)
            val mEncoder = BarcodeEncoder()
            val mBitmap = mEncoder.createBitmap(mMatrix)

            Timber.i("BITMAP : $mBitmap")

            printables.add(ImagePrintable.Builder(mBitmap)
                .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
                .build())
        } catch (e: WriterException) {
            Timber.e("WRITER EXCEPTION : $e")
            e.printStackTrace()
        }

        printables.add(leftText)
        printables.add(centerText)
        printables.add(rightText)
        printables.add(blankText)

        Printooth.printer().print(printables)
    }


    override fun connectingWithPrinter() {
        Toast.makeText(requireContext(), "Connecting with printer", Toast.LENGTH_SHORT).show()
        Timber.d("PRINTER STATUS : Connecting with printer")
    }

    override fun printingOrderSentSuccessfully() {
        Toast.makeText(requireContext(), "Order sent to printer", Toast.LENGTH_SHORT).show()
        Timber.d("PRINTER STATUS : Order sent to printer")
    }

    override fun connectionFailed(error: String) {
        Toast.makeText(requireContext(), "Failed to connect printer", Toast.LENGTH_SHORT).show()
        Timber.d("PRINTER STATUS : Failed to connect printer")
    }

    override fun onError(error: String) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        Timber.d("PRINTER STATUS : $error")
    }

    override fun onMessage(message: String) {
        Toast.makeText(requireContext(), "Message: $message", Toast.LENGTH_SHORT).show()
        Timber.d("PRINTER STATUS : Message: $message")
    }

    override fun disconnected() {
        Toast.makeText(requireContext(), "Disconnected Printer", Toast.LENGTH_SHORT).show()
        Timber.d("PRINTER STATUS : Disconnected Printer")
    }
}
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
import android.widget.CheckBox
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
import com.junianto.edcsekolah.util.DeviceInfo
import com.junianto.edcsekolah.util.ImageSaver
import com.junianto.edcsekolah.util.PrintingManager
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
import kotlin.properties.Delegates

@AndroidEntryPoint
class SetupFragment : Fragment(), PrintingCallback {

    private val appViewModel: AppViewModel by viewModels()

    private lateinit var etSchoolName: EditText
    private lateinit var etMajorName: EditText
    private lateinit var etSchoolAddress: EditText
    private lateinit var cbIsImagePrinted: CheckBox

    private lateinit var btnSave: Button
    private lateinit var btnCetak: Button
    private lateinit var btnPrintLoop: Button

    private lateinit var ivSchoolLogo: ImageView

    private lateinit var schoolLogo: String
    private var isImagePrinted = false

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
        cbIsImagePrinted = view.findViewById(R.id.cb_print_logo)

        ivSchoolLogo = view.findViewById(R.id.iv_school_logo)

        btnSave = view.findViewById(R.id.btn_save)
        btnSave.setOnClickListener {
            val schoolName = etSchoolName.text.toString()
            val schoolAddress = etSchoolAddress.text.toString()
            val majorName = etMajorName.text.toString()
            val isImagePrinted = cbIsImagePrinted.isChecked

            ImageSaver(requireContext())
                .setFileName("school_logo.png")
                .setDirectoryName("images")
                .save(ivSchoolLogo.drawable.toBitmap())

            appViewModel.updateAppSetup(schoolName, schoolAddress, majorName, schoolLogo, isImagePrinted)
        }

        appViewModel.appSetup.observe(viewLifecycleOwner) { appSetup ->
            // Populate the UI with the retrieved app setup data
            etSchoolName.setText(appSetup.school_name)
            etSchoolAddress.setText(appSetup.school_address)
            etMajorName.setText(appSetup.major_name)

            schoolLogo = appSetup.school_logo

            cbIsImagePrinted.isChecked = isImagePrinted

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

        val cpuArchitecture = DeviceInfo.getCpuArchitecture()

        btnCetak = view.findViewById(R.id.btn_cetak)
        btnCetak.setOnClickListener {
            if (!Printooth.hasPairedPrinter()) {
                when (cpuArchitecture) {
                    "armeabi" -> {
//                        A90PrintManager.printReceiptText(requireContext())
                        A90PrintManager.printReceiptSuccess(
                            requireContext(),
                            schoolLogo,
                            etSchoolName.text.toString(),
                            etMajorName.text.toString(),
                            123456,
                            "12/12/2021",
                            "12:12:12",
                            1,
                            "1234567890",
                            "Rp. 100.000",
                            "Pembayaran SPP",
                            reprint = false,
                            isImagePrint = true,
                        )
                    }
                    "armeabi-v7a" -> {
//                        A90PrintManager.printReceiptText(requireContext())
                        A90PrintManager.printReceiptSuccess(
                            requireContext(),
                            schoolLogo,
                            etSchoolName.text.toString(),
                            etMajorName.text.toString(),
                            123456,
                            "12/12/2021",
                            "12:12:12",
                            1,
                            "1234567890",
                            "Rp. 100.000",
                            "Pembayaran SPP",
                            reprint = false,
                            isImagePrint = true,
                        )
                    }
                    else -> {
                        Toast.makeText(requireContext(), "Please connect the thermal printer in setting.", Toast.LENGTH_LONG).show()
                    }
                }

            } else {
                printDetails()
            }
        }

        ivSchoolLogo.setOnClickListener {
            pickMediaLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        cbIsImagePrinted.setOnCheckedChangeListener { _, isChecked ->
            isImagePrinted = isChecked
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

    private fun printDetails() {
        PrintingManager.printManager(
            requireContext(),
            schoolLogo,
            etSchoolName.text.toString(),
            etMajorName.text.toString(),
            123456,
            "12/12/2021",
            "12:12:12",
            1,
            "1234567890",
            "Rp. 100.000",
            "Pembayaran SPP",
            false,
            false,
        )
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
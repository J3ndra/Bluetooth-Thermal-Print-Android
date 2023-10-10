package com.junianto.edcsekolah

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.junianto.edcsekolah.util.ImageSaver
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class AppFragment : Fragment() {

    private val appViewModel: AppViewModel by viewModels()

    private lateinit var tvSchoolName: TextView
    private lateinit var tvSchoolAddress: TextView
    private lateinit var tvMajorName: TextView

    private lateinit var ivSchoolLogo: ImageView

    private lateinit var schoolLogo: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_app, container, false)

        val btnGoToMenu = view.findViewById<Button>(R.id.btn_go_to_menu)
        btnGoToMenu?.setOnClickListener {
            findNavController().navigate(R.id.action_appFragment_to_menuFragment)
        }

        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvSchoolName = view.findViewById(R.id.tv_school_name)
        tvSchoolAddress = view.findViewById(R.id.tv_school_address)
        tvMajorName = view.findViewById(R.id.tv_major_name)
        ivSchoolLogo = view.findViewById(R.id.iv_school_logo)

        appViewModel.appSetup.observe(viewLifecycleOwner) { appSetup ->
            // Populate the UI with the retrieved app setup data
            tvSchoolName.text = appSetup.school_name
            tvSchoolAddress.text = appSetup.school_address
            tvMajorName.text = appSetup.major_name

            schoolLogo = appSetup.school_logo

            Timber.d("APP FRAGMENT : ${appSetup.school_address} | ${appSetup.school_name} | ${appSetup.school_logo}")

            if (schoolLogo == "") {
                ivSchoolLogo.setImageResource(R.drawable.tutwuri_logo)
            } else {
                val option = BitmapFactory.Options().apply {
                    inSampleSize = 1
                }

                val bitmap: Bitmap? = ImageSaver(requireContext())
                    .setFileName("school_logo.png")
                    .setDirectoryName("images")
                    .load(option)

                ivSchoolLogo.setImageBitmap(bitmap)

//                ivSchoolLogo.setImageURI(Uri.parse(schoolLogo))
            }

            Timber.d("APP FRAGMENT : $appSetup")
        }
    }

    override fun onResume() {
        super.onResume()
        val supportActionBar: androidx.appcompat.app.ActionBar? = (requireActivity() as? AppCompatActivity)?.supportActionBar
        supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        val supportActionBar: androidx.appcompat.app.ActionBar? = (requireActivity() as? AppCompatActivity)?.supportActionBar
        supportActionBar?.show()
    }


//    EXAMPLE PRINTING
//    /* Customize your printer here with text, logo and QR code */
//    private fun getSomePrintables() = ArrayList<Printable>().apply {
//
//        add(RawPrintable.Builder(byteArrayOf(27, 100, 4)).build()) // feed lines example in raw mode
//
//        add(
//            TextPrintable.Builder()
//                .setText("Printer")
//                .setLineSpacing(DefaultPrinter.LINE_SPACING_60)
//                .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
//                .setFontSize(DefaultPrinter.FONT_SIZE_LARGE)
//                .setEmphasizedMode(DefaultPrinter.EMPHASIZED_MODE_BOLD)
//                .setUnderlined(DefaultPrinter.UNDERLINED_MODE_OFF)
//                .setNewLinesAfter(1)
//                .build())
//
//
//        add(
//            TextPrintable.Builder()
//                .setText("TID: 1111123322" )
//                .setCharacterCode(DefaultPrinter.CHARCODE_PC1252)
//                .setNewLinesAfter(1)
//                .build())
//
//        add(
//            TextPrintable.Builder()
//                .setText("RRN: : 234566dfgg4456")
//                .setCharacterCode(DefaultPrinter.CHARCODE_PC1252)
//                .setNewLinesAfter(1)
//                .build())
//
//        add(
//            TextPrintable.Builder()
//                .setText("Amount: NGN$200,000")
//                .setCharacterCode(DefaultPrinter.CHARCODE_PC1252)
//                .setNewLinesAfter(2)
//                .build())
//
//
//        add(
//            TextPrintable.Builder()
//                .setText("APPROVED")
//                .setLineSpacing(DefaultPrinter.LINE_SPACING_60)
//                .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
//                .setFontSize(DefaultPrinter.FONT_SIZE_LARGE)
//                .setEmphasizedMode(DefaultPrinter.EMPHASIZED_MODE_BOLD)
//                .setUnderlined(DefaultPrinter.UNDERLINED_MODE_OFF)
//                .setNewLinesAfter(1)
//                .build())
//
//
//        add(
//            TextPrintable.Builder()
//                .setText("Transaction: Withdrawal")
//                .setCharacterCode(DefaultPrinter.CHARCODE_PC1252)
//                .setNewLinesAfter(1)
//                .build())
//
//
//        val qr: Bitmap = QRCode.from("RRN: : 234566dfgg4456\nAmount: NGN\$200,000\n")
//            .withSize(200, 200).bitmap()
//
//        add(
//            ImagePrintable.Builder(qr)
//                .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
//                .build())
//
//
//        add(TextPrintable.Builder()
//            .setText("Hello World")
//            .setLineSpacing(DefaultPrinter.LINE_SPACING_60)
//            .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
//            .setEmphasizedMode(DefaultPrinter.EMPHASIZED_MODE_BOLD)
//            .setUnderlined(DefaultPrinter.UNDERLINED_MODE_ON)
//            .setNewLinesAfter(1)
//            .build())
//
//        add(TextPrintable.Builder()
//            .setText("Hello World")
//            .setAlignment(DefaultPrinter.ALIGNMENT_RIGHT)
//            .setEmphasizedMode(DefaultPrinter.EMPHASIZED_MODE_BOLD)
//            .setUnderlined(DefaultPrinter.UNDERLINED_MODE_ON)
//            .setNewLinesAfter(1)
//            .build())
//
//        add(RawPrintable.Builder(byteArrayOf(27, 100, 4)).build())
//
//    }
}
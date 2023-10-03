package com.junianto.edcsekolah.a90.printer

import android.content.Context
import com.junianto.edcsekolah.R
import com.junianto.edcsekolah.util.bitmapToByteArray
import com.junianto.edcsekolah.util.drawableToByteArray
import com.junianto.edcsekolah.util.loadAndResizeBitmap
import com.vanstone.trans.api.PrinterApi
import com.vanstone.trans.api.SystemApi
import timber.log.Timber

object A90PrintManager {

    fun printReceiptSuccess(
        context: Context,
        schoolLogo: String,
        schoolName: String,
        majorName: String,
        traceId: Int,
        date: String,
        time: String,
        paymentType: Int,
        cardId: String,
        amount: String,
        type: String,
        reprint: Boolean,
        isImagePrint: Boolean,
    ) {
        PrinterApi.PrnClrBuff_Api()
        PrinterApi.printSetAlign_Api(1)
        PrinterApi.printSetTextSize_Api(36)
        PrinterApi.PrnSetGray_Api(10)
        PrinterApi.printSetBlodText_Api(true)
        if (isImagePrint) {
            if (schoolLogo == "") {
                PrinterApi.printAddImage_Api(
                    0x07,
                    256,
                    256,
                    drawableToByteArray(
                        context,
                        R.drawable.tut_wuri_logo_2
                    )
                )
            }
        }
        PrinterApi.PrnStr_Api("SMK")
        PrinterApi.printSetTextSize_Api(24)
        PrinterApi.printSetBlodText_Api(false)
        PrinterApi.PrnStr_Api(majorName)
        PrinterApi.printSetTextSize_Api(36)
        PrinterApi.printSetBlodText_Api(true)
        PrinterApi.PrnStr_Api(schoolName)

        PrinterApi.PrnStr_Api("\n\n\n")
        printData()
        PrinterApi.PrnCut_Api()
    }

    fun printReceiptText(context: Context) {
        PrinterApi.PrnOpen_Api("Test Print", context)
        PrinterApi.PrnClrBuff_Api()
        PrinterApi.PrnLineSpaceSet_Api(5.toShort(), 0x00)
//        PrinterApi.PrnFontSet_Api(32, 32, 0)
        PrinterApi.printSetTextSize_Api(30)
        PrinterApi.PrnSetGray_Api(10)
        PrinterApi.printSetBlodText_Api(true)
        PrinterApi.PrnLineSpaceSet_Api(5.toShort(), 0)
        PrinterApi.printSetAlign_Api(1)
        PrinterApi.PrnStr_Api("POS Receipt")
        PrinterApi.printSetAlign_Api(1)
//        PrinterApi.PrnFontSet_Api(24, 24, 0)
        PrinterApi.printSetTextSize_Api(30)
        PrinterApi.PrnStr_Api("CARDHOLDER COPY")
        PrinterApi.printSetAlign_Api(0)
        PrinterApi.printSetBlodText_Api(false)

        PrinterApi.PrnStr_Api("--------------------------------")
        PrinterApi.printSetBlodText_Api(true)

//        PrinterApi.PrnFontSet_Api(20, 20, 0)
        PrinterApi.printSetTextSize_Api(22)

        PrinterApi.PrnStr_Api("MERCHANT NAME: TEST MERCHANT NAME")
        PrinterApi.PrnStr_Api("MERCHANT ID: TEST MERCHANT ID")
        PrinterApi.PrnStr_Api("TERMINAL ID: ${SystemApi.ReadPosSn_Api()}")
        PrinterApi.PrnStr_Api("CASHIER ID: TEST CASHIER ID")
        PrinterApi.PrnStr_Api("PAYMENT TYPE: TEST PAYMENT TYPE")
        PrinterApi.PrnStr_Api("PAYMENT TIME: TEST PAYMENT TIME")

        PrinterApi.PrnStr_Api("\n")

        PrinterApi.PrnFontSet_Api(24, 24, 0)

        PrinterApi.printSetBlodText_Api(false)

        PrinterApi.PrnStr_Api("--------------------------------")
        PrinterApi.printSetBlodText_Api(true)

//        PrinterApi.PrnFontSet_Api(20, 20, 0)
        PrinterApi.printSetTextSize_Api(22)

        PrinterApi.PrnStr_Api("TOTAL QUANTITY: 5")
        PrinterApi.PrnStr_Api("TOTAL AMOUNT: Rp. 100.000")

        PrinterApi.printSetBlodText_Api(false)

        PrinterApi.PrnStr_Api("I accept this trade and allow it on my account")
        PrinterApi.PrnFontSet_Api(24, 24, 0)
        PrinterApi.PrnStr_Api("----------x------------x-------")
        PrinterApi.PrnStr_Api("\n\n\n")

        printData()

        PrinterApi.PrnCut_Api()
    }

    fun printData(): Int {
        val ret: Int
        var msg: String = ""

        while (true) {
            ret = PrinterApi.PrnStart_Api()
            Timber.d("aabb", "PrnStart_Api:$ret")

            when (ret) {
                2 -> msg = "Paper is not enough"
                3 -> msg = "Printer is too hot"
                4 -> msg = "PLS put it back\nPress any key to reprint"
                0 -> return 0
            }

            Timber.i(msg)

            return -1
        }
    }
}
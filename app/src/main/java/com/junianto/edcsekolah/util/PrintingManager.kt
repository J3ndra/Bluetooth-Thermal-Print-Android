package com.junianto.edcsekolah.util

import android.content.Context
import android.graphics.Bitmap
import com.junianto.edcsekolah.R
import com.mazenrashed.printooth.Printooth
import com.mazenrashed.printooth.data.printable.ImagePrintable
import com.mazenrashed.printooth.data.printable.Printable
import com.mazenrashed.printooth.data.printable.TextPrintable
import com.mazenrashed.printooth.data.printer.DefaultPrinter

object PrintingManager {
    fun printManager(
        context: Context,
        schoolLogo: String,
        schoolName: String,
        majorName: String,
        traceId: Int,
        date: String,
        time: String,
        status: Boolean,
        cardId: String,
        amount: String,
        type: String,
        reprint: Boolean,
    ) {
        val printables = ArrayList<Printable>()

        val tutWuriLogo = loadAndResizeBitmap(context, schoolLogo)?.let {
            ImagePrintable.Builder(it)
                .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
                .setNewLinesAfter(1)
                .build()
        }
        val smkText = TextPrintable.Builder()
            .setText("SMK\n")
            .setCharacterCode(DefaultPrinter.CHARCODE_PC1252)
            .setEmphasizedMode(DefaultPrinter.EMPHASIZED_MODE_BOLD)
            .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
            .setFontSize(DefaultPrinter.FONT_SIZE_LARGE)
            .build()
        val majorText = TextPrintable.Builder()
            .setText("$majorName\n")
            .setCharacterCode(DefaultPrinter.CHARCODE_PC1252)
            .setFontSize(DefaultPrinter.FONT_SIZE_NORMAL)
            .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
            .build()
        val schoolText = TextPrintable.Builder()
            .setText("$schoolName\n")
            .setCharacterCode(DefaultPrinter.CHARCODE_PC1252)
            .setEmphasizedMode(DefaultPrinter.EMPHASIZED_MODE_BOLD)
            .setFontSize(DefaultPrinter.FONT_SIZE_NORMAL)
            .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
            .build()
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
            .setCharacterCode(DefaultPrinter.CHARCODE_PC1252)
            .setText("================================\n")
            .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
            .build()
        val terminalIdText = TextPrintable.Builder()
            .setText("TERMINAL ID : 000000\n")
            .setAlignment(DefaultPrinter.ALIGNMENT_LEFT)
            .setFontSize(DefaultPrinter.FONT_SIZE_NORMAL)
            .build()
        val merchantIdText = TextPrintable.Builder()
            .setCharacterCode(DefaultPrinter.CHARCODE_PC1252)
            .setText("MERCHANT ID : 000000000\n")
            .setAlignment(DefaultPrinter.ALIGNMENT_LEFT)
            .setFontSize(DefaultPrinter.FONT_SIZE_NORMAL)
            .build()
        val dateText = TextPrintable.Builder()
            .setCharacterCode(DefaultPrinter.CHARCODE_PC1252)
            .setText("DATE : $date\n")
            .setAlignment(DefaultPrinter.ALIGNMENT_LEFT)
            .setFontSize(DefaultPrinter.FONT_SIZE_NORMAL)
            .build()
        val timeText = TextPrintable.Builder()
            .setCharacterCode(DefaultPrinter.CHARCODE_PC1252)
            .setText("TIME : $time\n")
            .setAlignment(DefaultPrinter.ALIGNMENT_LEFT)
            .setFontSize(DefaultPrinter.FONT_SIZE_NORMAL)
            .build()
        val reffText = TextPrintable.Builder()
            .setCharacterCode(DefaultPrinter.CHARCODE_PC1252)
            .setText("REFF NO : 000000\n")
            .setAlignment(DefaultPrinter.ALIGNMENT_LEFT)
            .setFontSize(DefaultPrinter.FONT_SIZE_NORMAL)
            .build()
        val aprvText = TextPrintable.Builder()
            .setCharacterCode(DefaultPrinter.CHARCODE_PC1252)
            .setText("APRV NO : 000000\n")
            .setAlignment(DefaultPrinter.ALIGNMENT_LEFT)
            .setFontSize(DefaultPrinter.FONT_SIZE_NORMAL)
            .build()
        val traceText = TextPrintable.Builder()
            .setCharacterCode(DefaultPrinter.CHARCODE_PC1252)
            .setText("TRACE NO : $traceId\n")
            .setAlignment(DefaultPrinter.ALIGNMENT_LEFT)
            .setFontSize(DefaultPrinter.FONT_SIZE_NORMAL)
            .build()
        val batchNoText = TextPrintable.Builder()
            .setText("BATCH NO : 000000\n")
            .setCharacterCode(DefaultPrinter.CHARCODE_PC1252)
            .setAlignment(DefaultPrinter.ALIGNMENT_LEFT)
            .setFontSize(DefaultPrinter.FONT_SIZE_NORMAL)
            .build()
        val statusText = TextPrintable.Builder()
            .setText("STATUS : ${if (status) "PAID" else "SETTLED"}\n")
            .setCharacterCode(DefaultPrinter.CHARCODE_PC1252)
            .setAlignment(DefaultPrinter.ALIGNMENT_LEFT)
            .setFontSize(DefaultPrinter.FONT_SIZE_LARGE)
            .build()
        val cardIdText = TextPrintable.Builder()
            .setText("CARD ID : $cardId\n\n")
            .setCharacterCode(DefaultPrinter.CHARCODE_PC1252)
            .setAlignment(DefaultPrinter.ALIGNMENT_LEFT)
            .setFontSize(DefaultPrinter.FONT_SIZE_NORMAL)
            .build()
        val typeText = TextPrintable.Builder()
            .setText("$type\n")
            .setCharacterCode(DefaultPrinter.CHARCODE_PC1252)
            .setAlignment(DefaultPrinter.ALIGNMENT_LEFT)
            .setFontSize(DefaultPrinter.FONT_SIZE_LARGE)
            .setEmphasizedMode(DefaultPrinter.EMPHASIZED_MODE_BOLD)
            .build()
        val amountText = TextPrintable.Builder()
            .setText("AMOUNT : ${if (type == "VOID") "-${formatAmount(amount)}" else formatAmount(amount)}\n")
            .setCharacterCode(DefaultPrinter.CHARCODE_PC1252)
            .setAlignment(DefaultPrinter.ALIGNMENT_LEFT)
            .setFontSize(DefaultPrinter.FONT_SIZE_NORMAL)
            .build()
        val signatureText = TextPrintable.Builder()
            .setText("SIGNATURE\n\n")
            .setCharacterCode(DefaultPrinter.CHARCODE_PC1252)
            .setAlignment(DefaultPrinter.ALIGNMENT_LEFT)
            .setFontSize(DefaultPrinter.FONT_SIZE_LARGE)
            .setEmphasizedMode(DefaultPrinter.EMPHASIZED_MODE_BOLD)
            .build()
        val cardHolderText = TextPrintable.Builder()
            .setText("CARDHOLDER ACKNOWLEDGE RECEIPT OF GOODS AND/OR SERVICES\n")
            .setCharacterCode(DefaultPrinter.CHARCODE_PC1252)
            .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
            .setFontSize(DefaultPrinter.FONT_SIZE_NORMAL)
            .build()
        val copyText = TextPrintable.Builder()
            .setText(if (reprint) "** BANK COPY **" else "** CUSTOMER COPY **")
            .setCharacterCode(DefaultPrinter.CHARCODE_PC1252)
            .setAlignment(DefaultPrinter.ALIGNMENT_CENTER)
            .setFontSize(DefaultPrinter.FONT_SIZE_NORMAL)
            .setNewLinesAfter(3)
            .build()

        if (tutWuriLogo != null) {
            printables.add(tutWuriLogo)
        }
        printables.add(smkText)
        printables.add(majorText)
        printables.add(schoolText)
        printables.add(edcNoText)
        printables.add(terminalIdText)
        printables.add(merchantIdText)
        printables.add(dateText)
        printables.add(timeText)
        printables.add(reffText)
        printables.add(aprvText)
        printables.add(traceText)
        printables.add(batchNoText)
//        printables.add(statusText)
        printables.add(cardIdText)
        printables.add(typeText)
        printables.add(amountText)
        printables.add(signatureText)
        printables.add(linedText)
        printables.add(cardHolderText)
        printables.add(copyText)

        Printooth.printer().print(printables)
    }
}
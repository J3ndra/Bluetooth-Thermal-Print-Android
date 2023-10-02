package com.junianto.edcsekolah.util

import android.graphics.Bitmap
import android.widget.EditText
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.journeyapps.barcodescanner.BarcodeEncoder
import java.text.NumberFormat
import java.util.Locale

fun appendAmount(amount: String, etAmount: EditText) {
    val currentValue = etAmount.text.toString().replace(".", "").replace(",", "")

    // Append the new amount to the current value
    val newValue = currentValue + amount

    // Convert the formatted string to a numeric value
    val numericValue = newValue.replace(",", "").toLong()

    // Format the numericValue with thousands separators
    val formattedValue = NumberFormat.getInstance().format(numericValue)

    // Convert formattedValue to string
    val formattedValueString = formattedValue.toString().replace(",", ".")

    etAmount.setText(formattedValueString)
}

fun clearLastDigit(etAmount: EditText) {
    val currentValue = etAmount.text.toString().replace(".", "").replace(",", "")

    if (currentValue.isNotEmpty()) {
        val newValue = currentValue.substring(0, currentValue.length - 1)

        // Convert the string value back to a Long
        val numericValue = if (newValue.isNotEmpty()) newValue.toLong() else 0

        // Format the numericValue with thousands separators using a custom format
        val customFormat = NumberFormat.getInstance(Locale.US)
        customFormat.isGroupingUsed = true
        customFormat.minimumFractionDigits = 0
        customFormat.maximumFractionDigits = 0

        // Format the numericValue using the custom format
        val formattedValue = customFormat.format(numericValue)

        val formattedValueString = formattedValue.toString().replace(",", ".")

        etAmount.setText(formattedValueString)
    }
}

fun generateQRCode(content: String): Bitmap? {
    try {
        val multiFormatWriter = MultiFormatWriter()
        val bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 250, 250)
        val barcodeEncoder = BarcodeEncoder()
        return barcodeEncoder.createBitmap(bitMatrix)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}
package com.junianto.edcsekolah.util

import java.text.NumberFormat

fun formatAmount(amount: String): String {
    // Format the numericValue with thousands separators
    val formattedValue = NumberFormat.getInstance().format(amount.toDouble())

    // Convert formattedValue to string
    val formattedValueString = formattedValue.toString().replace(",", ".")

    return "Rp $formattedValueString,-"
}
package com.junianto.edcsekolah.util

import android.content.Context
import android.content.pm.PackageManager

object NfcUtils {
    fun isNfcSupported(context: Context): Boolean {
        val pm = context.packageManager
        return pm.hasSystemFeature(PackageManager.FEATURE_NFC)
    }
}
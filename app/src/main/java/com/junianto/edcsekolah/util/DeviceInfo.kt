package com.junianto.edcsekolah.util

import android.os.Build

object DeviceInfo {
    fun getCpuArchitecture(): String {
        val supportedABIs = Build.SUPPORTED_ABIS
        return if (supportedABIs.isNotEmpty()) {
            supportedABIs[0]
        } else {
            Build.CPU_ABI
        }
    }
}
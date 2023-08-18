package com.junianto.edcsekolah.util

fun ByteArrayToHexString(inByteArray: ByteArray): String {
    val sb = StringBuilder()
    for (b in inByteArray) {
        sb.append(String.format("%02x", b))
    }
    return sb.toString()
}
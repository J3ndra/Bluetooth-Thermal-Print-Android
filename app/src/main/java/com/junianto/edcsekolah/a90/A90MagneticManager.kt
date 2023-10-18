package com.junianto.edcsekolah.a90

import com.vanstone.trans.api.LcdApi
import com.vanstone.trans.api.MagCardApi
import com.vanstone.trans.api.SystemApi
import timber.log.Timber

object A90MagneticManager {
    private var slottedCard: Boolean = true

    fun turnOnLed() {
        LcdApi.LedLightOff_Api(0x0F)
        LcdApi.LedLightOn_Api(0x01)
        SystemApi.Beep_Api(0)
    }

    fun turnOffLed() {
        LcdApi.LedLightOff_Api(0x0F)
        SystemApi.Beep_Api(0)
    }

    fun openMagneticCard() {
        MagCardApi.MagClose_Api()
        turnOffLed()

        val ret = MagCardApi.MagOpen_Api()
        Timber.i("MagOpen_Api: $ret")
        turnOnLed()

        if (ret != 0) {
            Timber.e("MagOpen_Api failed to open")
            slottedCard = false
        }

        Timber.i("MagOpen_Api success")
    }

    fun closeMagneticCard() {
        MagCardApi.MagClose_Api()
        turnOffLed()
    }
}
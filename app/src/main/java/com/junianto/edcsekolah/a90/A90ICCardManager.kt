package com.junianto.edcsekolah.a90

import com.vanstone.trans.api.LcdApi
import com.vanstone.trans.api.PiccApi
import com.vanstone.trans.api.SystemApi
import timber.log.Timber

object A90ICCardManager {
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

    fun openICCard() {
        // Close PiccAPi & Turn off led
        PiccApi.PiccClose_Api()
        turnOffLed()

        // Open PiccApi & Turn on led
        val ret = PiccApi.PiccOpen_Api()
        Timber.d("PiccOpen_Api: $ret")
        turnOnLed()

        // Check if PiccApi is open
        if (ret != 0) {
            Timber.e("PiccOpen_Api failed to open")
            slottedCard = false
        }

        Timber.i("PiccOpen_Api success")
    }

    fun closeICCard() {
        // Close PiccApi & Turn off led
        PiccApi.PiccClose_Api()
        turnOffLed()
    }
}
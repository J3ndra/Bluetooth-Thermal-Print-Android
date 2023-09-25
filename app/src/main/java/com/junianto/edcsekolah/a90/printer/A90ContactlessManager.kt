package com.junianto.edcsekolah.a90.printer

import android.util.Log
import com.vanstone.l2.Common
import com.vanstone.trans.api.IcApi
import com.vanstone.trans.api.LcdApi
import com.vanstone.trans.api.MagCardApi
import com.vanstone.trans.api.PiccApi
import com.vanstone.trans.api.SystemApi
import com.vanstone.utils.ByteUtils
import com.vanstone.utils.CommonConvert
import timber.log.Timber

object A90ContactlessManager {

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

    fun readContactlessCard() {
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

        object : Thread() {
            override fun run() {
                super.run()
                while (slottedCard) {
                    var timerId = 0
                    timerId = SystemApi.TimerSet_Api()
                    val cardType = ByteArray(2)
                    val serialNo = ByteArray(20)

                    while (SystemApi.TimerCheck_Api(timerId, 20 * 1000) == 0) {
                        val aret = PiccApi.PiccCheck_Api(0, cardType, serialNo)
                        Timber.i("PiccCheck_Api: $aret")

                        slottedCard = false
                    }
                }
            }
        }.start()
    }
}
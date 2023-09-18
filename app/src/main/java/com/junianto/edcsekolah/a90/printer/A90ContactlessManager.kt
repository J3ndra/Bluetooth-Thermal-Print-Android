package com.junianto.edcsekolah.a90.printer

import com.vanstone.l2.Common
import com.vanstone.trans.api.LcdApi
import com.vanstone.trans.api.PiccApi
import com.vanstone.trans.api.SystemApi
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

    fun openContactlessCard() {
        // Close PiccAPi
        PiccApi.PiccClose_Api()

        var ret = PiccApi.PiccOpen_Api()

        if (ret != 0) {
            Timber.e("PiccOpen_Api failed")
            PiccApi.PiccClose_Api()
            ret = PiccApi.PiccOpen_Api()
            Timber.d("aabb", "PiccOpen_Api ret: $ret")
            if (ret != 0) {
                Timber.e("PiccOpen_Api failed")
                slottedCard = false
            }
        }

        if (ret == 0) {
            Timber.i("PiccOpen_Api success")
        }

        Thread {
            while (slottedCard) {
                var timerId = 0
                timerId = SystemApi.TimerSet_Api()

                val cardData = ByteArray(1024)
                val useCardLenAddress = ByteArray(4)
                val cardType = ByteArray(2)
                val serialNo = ByteArray(20)

                while (SystemApi.TimerCheck_Api(timerId, 20 * 1000) == 0) {
                    val cardFlag = false
                    val iccCardFlag = false
                    var piccCardFlag = false

                    val aret = PiccApi.PiccCheck_Api(0, cardType, serialNo)

                    if (aret == 0) {
                        piccCardFlag = true
                    }

                    if (piccCardFlag) {
                        var kType: Int
                        Timber.d("Processing Picc Card...")

                        Common.SetIcCardType_Api(Common.PEDPICCCARD, 0.toByte())

                        while (true) {
                            // TODO
                        }
                    }
                }
            }
        }
    }
}
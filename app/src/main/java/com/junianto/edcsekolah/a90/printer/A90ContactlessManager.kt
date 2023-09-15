package com.junianto.edcsekolah.a90.printer

import com.vanstone.trans.api.ContactlessApi
import com.vanstone.trans.api.LcdApi
import com.vanstone.trans.api.PiccApi
import com.vanstone.trans.api.PiccExApi
import com.vanstone.trans.api.SystemApi
import timber.log.Timber

object A90ContactlessManager {

    private var ret = -1000
    private var cardType = 0

    fun readCard() {
        LcdApi.LedLightOff_Api(0x0F)
        LcdApi.LedLightOn_Api(0x01)
        SystemApi.Beep_Api(0)

        ret = PiccApi.PiccOpen_Api()

        if (ret != 0) {
            Timber.e("Contactless module open failed")
        } else {
            Timber.d("Contactless module open success")
        }

        cardType = PiccApi.PiccCheck_Api(0, ByteArray(16), ByteArray(126))

        if (cardType == 0) {
            Timber.d("Contactless module check card success")

            ContactlessApi.openField_Api()

        } else {
            Timber.e("Contactless module check card failed")
        }
    }
}
package com.junianto.edcsekolah.util

import com.vanstone.l2.COMMON_TERMINAL_PARAM
import com.vanstone.l2.EMV_TERM_PARAM

object GlobalConstants {
    var CurAppDir = ""
    var PosCom = PosCom()
    var g_EmvFullOrSim = 0
    var g_SwipedFlag = 0
    var stEmvParam = EMV_TERM_PARAM()
    var termParam = COMMON_TERMINAL_PARAM()
    var isPinPad: Int = DefConstants.PIN_PED
    var SelAppFlag = false
    var SelAppInx = 0xff
    var gCtrlParam: CtrlParam = CtrlParam()
}
package com.junianto.edcsekolah.util

import com.vanstone.base.interfaces.StructInterface
import com.vanstone.utils.CommonConvert

class PosCom : StructInterface {
    var sPIN = ByteArray(9) // 个人密码
    var sField40_oldTerNum = ByteArray(4) // 原交易终端号后3位
    var iField48Len = 0
    var sField48 = ByteArray(512) // 48域存放结算数据
    var sField54 = ByteArray(64)
    var iField58Len = 0
    var sField58 = ByteArray(512)
    var sField61 = ByteArray(64)
    var sField62 = ByteArray(512)
    var ucPrtflag // 交易结束后是否打印凭条
            = 0
    var ucWriteLog // 交易结束后是否记日志
            = 0
    var ucPointType // 积分类型 '0'联名/'1'特定
            = 0
    var ucRmbSettRsp // 人民币卡结算结果
            = 0
    var ucFrnSettRsp // 外币卡结算结果
            = 0
    var ucAmtSign // 金额的正负号 '+' '-'
            : Byte = 0
    var sOrignTransInfo = ByteArray(31) // 原交易信息，撤销、冲正交易用到
    var balanceAmount = ByteArray(41) // #48帐户余额 an...020
    var track1 = ByteArray(88) // #35 1磁道数据 Z..37 u8 Track1[2+37];
    var track2 = ByteArray(39) // #35 2磁道数据 Z..37
    var track3 = ByteArray(109) // #36 3磁道数据 Z..104
    var track1Len // 磁道1长度
            = 0
    var track2Len // 磁道2长度
            = 0
    var track3Len // 磁道3长度
            = 0
    var isSaveLogAndRev // 是否需要冲正或者保存日志
            = 0

    //public  Pos8583PacketStrc stTrnTbl=new Pos8583PacketStrc(); 	// 交易打包，是否打印等的信息
    var stTrans: LogStrc = LogStrc() // 交易要素信息，需要记录到日志中的数据
    var haveInputAmt // 本笔交易已经输入了金额了
            : Long = 0
    var nRespIccLen // 返回的IC卡验证数据
            = 0
    var respIccData = ByteArray(512)

    // EMV
    var szCertData = ByteArray(23) // type(2)ID no(20)
    var iReversalDE55Len = 0
    var sReversalDE55 = ByteArray(128) // 冲正和脚本通知的BIT 55数据 //11/06/15
    var iScriptDE55Len = 0
    var sScriptDE55 = ByteArray(128)
    var HaveInputPin: Byte = 0

    //新增
    var bEmvFullOrSim = 0
    var tc = ByteArray(8) //联机交易最后的tc
    fun getsPIN(): ByteArray {
        return sPIN
    }

    fun setsPIN(sPIN: ByteArray) {
        this.sPIN = sPIN
    }

    fun getsField40_oldTerNum(): ByteArray {
        return sField40_oldTerNum
    }

    fun setsField40_oldTerNum(sField40_oldTerNum: ByteArray) {
        this.sField40_oldTerNum = sField40_oldTerNum
    }

    fun getiField48Len(): Int {
        return iField48Len
    }

    fun setiField48Len(iField48Len: Int) {
        this.iField48Len = iField48Len
    }

    fun getsField48(): ByteArray {
        return sField48
    }

    fun setsField48(sField48: ByteArray) {
        this.sField48 = sField48
    }

    fun getsField54(): ByteArray {
        return sField54
    }

    fun setsField54(sField54: ByteArray) {
        this.sField54 = sField54
    }

    fun getiField58Len(): Int {
        return iField58Len
    }

    fun setiField58Len(iField58Len: Int) {
        this.iField58Len = iField58Len
    }

    fun getsField58(): ByteArray {
        return sField58
    }

    fun setsField58(sField58: ByteArray) {
        this.sField58 = sField58
    }

    fun getsField61(): ByteArray {
        return sField61
    }

    fun setsField61(sField61: ByteArray) {
        this.sField61 = sField61
    }

    fun getsField62(): ByteArray {
        return sField62
    }

    fun setsField62(sField62: ByteArray) {
        this.sField62 = sField62
    }

    fun getsOrignTransInfo(): ByteArray {
        return sOrignTransInfo
    }

    fun setsOrignTransInfo(sOrignTransInfo: ByteArray) {
        this.sOrignTransInfo = sOrignTransInfo
    }

    /*public Pos8583PacketStrc getStTrnTbl() {
     return stTrnTbl;
 }

 public void setStTrnTbl(Pos8583PacketStrc stTrnTbl) {
     this.stTrnTbl = stTrnTbl;
 }*/
    fun getStTrans(): LogStrc {
        return stTrans
    }

    fun setStTrans(stTrans: LogStrc) {
        this.stTrans = stTrans
    }

    fun getnRespIccLen(): Int {
        return nRespIccLen
    }

    fun setnRespIccLen(nRespIccLen: Int) {
        this.nRespIccLen = nRespIccLen
    }

    fun getiReversalDE55Len(): Int {
        return iReversalDE55Len
    }

    fun setiReversalDE55Len(iReversalDE55Len: Int) {
        this.iReversalDE55Len = iReversalDE55Len
    }

    fun getsReversalDE55(): ByteArray {
        return sReversalDE55
    }

    fun setsReversalDE55(sReversalDE55: ByteArray) {
        this.sReversalDE55 = sReversalDE55
    }

    fun getiScriptDE55Len(): Int {
        return iScriptDE55Len
    }

    fun setiScriptDE55Len(iScriptDE55Len: Int) {
        this.iScriptDE55Len = iScriptDE55Len
    }

    fun getsScriptDE55(): ByteArray {
        return sScriptDE55
    }

    fun setsScriptDE55(sScriptDE55: ByteArray) {
        this.sScriptDE55 = sScriptDE55
    }

    override fun toBytes(): ByteArray {
        val msgByte = ByteArray(size())
        var tmp: ByteArray? = null
        var len = 0
        tmp = ByteArray(sPIN.size)
        tmp = sPIN
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(sField40_oldTerNum.size)
        tmp = sField40_oldTerNum
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(iField48Len)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(sField48.size)
        tmp = sField48
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(sField54.size)
        tmp = sField54
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(iField58Len)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(sField58.size)
        tmp = sField58
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(sField61.size)
        tmp = sField61
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(sField62.size)
        tmp = sField62
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(ucPrtflag)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(ucWriteLog)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(ucPointType)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(ucRmbSettRsp)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(ucFrnSettRsp)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(1)
        tmp[0] = ucAmtSign
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(sOrignTransInfo.size)
        tmp = sOrignTransInfo
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(balanceAmount.size)
        tmp = balanceAmount
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(track1.size)
        tmp = track1
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(track2.size)
        tmp = track2
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(track3.size)
        tmp = track3
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(track1Len)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(track2Len)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(track3Len)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(isSaveLogAndRev)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        /*tmp=new byte[stTrnTbl.size()];
		tmp=stTrnTbl.toBytes();
		System.arraycopy(tmp, 0, msgByte, len, tmp.length);
		len+=tmp.length;*/tmp = ByteArray(stTrans.size())
        tmp = stTrans.toBytes()
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(4)
        tmp = CommonConvert.longToBytes(haveInputAmt)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(nRespIccLen)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(respIccData.size)
        tmp = respIccData
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(szCertData.size)
        tmp = szCertData
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(iReversalDE55Len)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(sReversalDE55.size)
        tmp = sReversalDE55
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(iScriptDE55Len)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(sScriptDE55.size)
        tmp = sScriptDE55
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        if (len % 4 != 0) {
            tmp = ByteArray(4 - len % 4)
            System.arraycopy(tmp, 0, msgByte, len, tmp.size)
            len += tmp.size
        }
        return msgByte
    }

    override fun size(): Int {
        var len = 0
        len += sPIN.size
        len += sField40_oldTerNum.size
        len += 4
        len += sField48.size
        len += sField54.size
        len += 4
        len += sField58.size
        len += sField61.size
        len += sField62.size
        len += 4
        len += 4
        len += 4
        len += 4
        len += 4
        len += 1
        len += sOrignTransInfo.size
        len += balanceAmount.size
        len += track1.size
        len += track2.size
        len += track3.size
        len += 4
        len += 4
        len += 4
        len += 4
        //len+=stTrnTbl.size();
        len += stTrans.size()
        len += 4
        len += 4
        len += respIccData.size
        len += szCertData.size
        len += 4
        len += sReversalDE55.size
        len += 4
        len += sScriptDE55.size
        if (len % 4 != 0) {
            len += 4 - len % 4
        }
        return len
    }

    override fun toBean(buf: ByteArray) {
        var tmp: ByteArray? = null
        var len = 0
        tmp = ByteArray(sPIN.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        sPIN = tmp
        len += tmp.size
        tmp = ByteArray(sField40_oldTerNum.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        sField40_oldTerNum = tmp
        len += tmp.size
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        iField48Len = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(sField48.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        sField48 = tmp
        len += tmp.size
        tmp = ByteArray(sField54.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        sField54 = tmp
        len += tmp.size
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        iField58Len = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(sField58.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        sField58 = tmp
        len += tmp.size
        tmp = ByteArray(sField61.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        sField61 = tmp
        len += tmp.size
        tmp = ByteArray(sField62.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        sField62 = tmp
        len += tmp.size
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        ucPrtflag = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        ucWriteLog = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        ucPointType = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        ucRmbSettRsp = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        ucFrnSettRsp = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(1)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        ucAmtSign = tmp[0]
        len += tmp.size
        tmp = ByteArray(sOrignTransInfo.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        sOrignTransInfo = tmp
        len += tmp.size
        tmp = ByteArray(balanceAmount.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        balanceAmount = tmp
        len += tmp.size
        tmp = ByteArray(track1.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        track1 = tmp
        len += tmp.size
        tmp = ByteArray(track2.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        track2 = tmp
        len += tmp.size
        tmp = ByteArray(track3.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        track3 = tmp
        len += tmp.size
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        track1Len = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        track2Len = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        track3Len = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        isSaveLogAndRev = CommonConvert.bytesToInt(tmp)
        len += 4
        /*tmp=new byte[stTrnTbl.size()];
		System.arraycopy(buf, len, tmp, 0, tmp.length);
		stTrnTbl.toBean(tmp);
		len+=tmp.length;*/tmp = ByteArray(stTrans.size())
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        stTrans.toBean(tmp)
        len += tmp.size
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        haveInputAmt = CommonConvert.bytesToLong(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        nRespIccLen = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(respIccData.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        respIccData = tmp
        len += tmp.size
        tmp = ByteArray(szCertData.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        szCertData = tmp
        len += tmp.size
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        iReversalDE55Len = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(sReversalDE55.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        sReversalDE55 = tmp
        len += tmp.size
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        iScriptDE55Len = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(sScriptDE55.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        sScriptDE55 = tmp
        len += tmp.size
    }
}
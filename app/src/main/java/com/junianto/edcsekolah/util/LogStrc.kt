package com.junianto.edcsekolah.util

import com.vanstone.base.interfaces.StructInterface
import com.vanstone.utils.CommonConvert

class LogStrc : StructInterface {
    var ucRecFalg // 本笔交易是否已上送
            = 0
    var eccTrans // 电子现金交易
            = 0
    var eccOnline // ICC online falg
            : Byte = 0
    var iccFallBack // TRUE, FALSE
            = 0
    var nIccDataLen // 卡片数据长度
            = 0
    var trans_id // 交易id
            = 0

    // u8 SecondTransId; //前面交易大类下面的小分类，适用于相似交易
    var ucSwipedFlag // 刷卡类型
            = 0
    var mainAcc = ByteArray(19) // #2 主帐号 n..19
    var tradeAmount = ByteArray(7) // #4 交易金额 BCD6
    var tradeDate = ByteArray(4) // #13 交易日期 n4(YYYYMMDD)(4BCD码 如:19700101

    // 1970-01-01)
    var tradeTime = ByteArray(3) // #12 交易时间 n3(hhmmss)(3位BCD码 如: 111231 11:12:31)
    var operatorNo // 操作员号
            = 0
    var lTraceNo // #11 流水号 n6
            = 0
    var lNowBatchNum // #9 批次号 n8
            = 0
    var szRespCode = ByteArray(3) // #39 交易返回码

    // 52字节
    var bitmapsend = ByteArray(8) // 发送的位图 2
    var resProcCode = ByteArray(4) // 第三域6位处理码的3、4位数字标识主帐户类型(由主机返回)
    var tipAmount = ByteArray(6) // #6 小费金额n12
    var tradeDateAndTime = ByteArray(5) // #7 传输时间 n5(MMDDhhmmss)
    var expDate = ByteArray(4) // #14 有效期 年/月/日
    var entryMode = ByteArray(4) // #22 POS输入点方式 码n3
    var field_26 = ByteArray(2) // #26 中油标志n2
    var desAndFrCardFlag_28 // #28 DES/外卡标志n1
            = 0
    var centerId = ByteArray(9) // #32 受理方标识码
    var sysReferNo = ByteArray(13) // #37 系统参考号 an12
    var authCode = ByteArray(7) // #38 授权码 an6
    var terminalNo = ByteArray(9) // #41 终端号 ans8
    var merchantNo = ByteArray(16) // #42 商户号 ans15
    var szIssuerBankId = ByteArray(9)
    var szRecvBankId = ByteArray(9)
    var secondAmount = ByteArray(6) // 第二个金额，用于需要第二个数额的场合
    var secondAcc = ByteArray(21) // #48 第二帐号 //撤销用第二账号保存交易代码
    var holdCardName = ByteArray(20) // 持卡人姓名
    var cardType = ByteArray(17) // 卡别，58域的前16位
    var iccSn = ByteArray(2) // #23 IC卡系列号 n3
    var addInfo = ByteArray(123) // #54，62域等 积分信息，分期信息等等附加信息
    var oldTraceNo // 原交易流水 用于消费撤销
            = 0
    var oldBatchNum // #原交易批次号
            = 0
    var oldTransDate = ByteArray(9) // #15 原交易日期
    var oldSysRefNo = ByteArray(13) // 原系统参考号

    // EMV交易数据
    var iccData = ByteArray(256) // #55 IC卡数据域 var up to 255
    var szCardUnit = ByteArray(4) // 卡组织：CUP VIS MAS
    var bPanSeqNoOk // ADVT case 43 [3/31/2006 Tommy]
            = 0
    var ucPanSeqNo // App. PAN sequence number
            : Byte = 0
    var sAppCrypto = ByteArray(8) // app. cryptogram
    var sAuthRspCode = ByteArray(2)
    var sTVR = ByteArray(5)
    var szAID = ByteArray(33)
    var szAppLable = ByteArray(17)
    var sTSI = ByteArray(2)
    var sATC = ByteArray(2)
    var szAppPreferName = ByteArray(17)
    var ec_Balance = ByteArray(6) // 电子现金余额
    var szCardTypeName = ByteArray(20) // 后台返回的卡类别名称
    var szAcquirer = ByteArray(7) // 收单行
    var szIssuerResp = ByteArray(21) // #63.2用于打印备注
    var szCenterResp = ByteArray(21) // #63.3
    var szRecvBankResp = ByteArray(21) // #63.4
    var szTransCode = ByteArray(7) // 处理码 用于结账判卡种
    var issueBankName = ByteArray(41) // 返回的发卡行名称
    var oldTransCode = ByteArray(7) // 原交易处理码(主要用于脚本结果上送)

    //新增
    var state //交易的状态 0--正常  1--联机失败2--被取消(或输入有误) 3--被撤销 4-被调整过 5--ARPC错误 6--脱机拒绝 7--联机拒绝
            = 0

    //下面是联机交易最终的密文，用于TC上送
    var Arpc = ByteArray(8) //用于卡片认证ARPC错时,向后台上送
    var tc = ByteArray(8) //联机交易最后的tc
    var cid = ByteArray(1) //最终的
    var tvr = ByteArray(5) //最终的
    var bOffline = 0
    var needSignature: Byte = 0
    fun getnIccDataLen(): Int {
        return nIccDataLen
    }

    fun setnIccDataLen(nIccDataLen: Int) {
        this.nIccDataLen = nIccDataLen
    }

    fun getlTraceNo(): Int {
        return lTraceNo
    }

    fun setlTraceNo(lTraceNo: Int) {
        this.lTraceNo = lTraceNo
    }

    fun getlNowBatchNum(): Int {
        return lNowBatchNum
    }

    fun setlNowBatchNum(lNowBatchNum: Int) {
        this.lNowBatchNum = lNowBatchNum
    }

    fun getbPanSeqNoOk(): Int {
        return bPanSeqNoOk
    }

    fun setbPanSeqNoOk(bPanSeqNoOk: Int) {
        this.bPanSeqNoOk = bPanSeqNoOk
    }

    fun getsAppCrypto(): ByteArray {
        return sAppCrypto
    }

    fun setsAppCrypto(sAppCrypto: ByteArray) {
        this.sAppCrypto = sAppCrypto
    }

    fun getsAuthRspCode(): ByteArray {
        return sAuthRspCode
    }

    fun setsAuthRspCode(sAuthRspCode: ByteArray) {
        this.sAuthRspCode = sAuthRspCode
    }

    fun getsTVR(): ByteArray {
        return sTVR
    }

    fun setsTVR(sTVR: ByteArray) {
        this.sTVR = sTVR
    }

    fun getsTSI(): ByteArray {
        return sTSI
    }

    fun setsTSI(sTSI: ByteArray) {
        this.sTSI = sTSI
    }

    fun getsATC(): ByteArray {
        return sATC
    }

    fun setsATC(sATC: ByteArray) {
        this.sATC = sATC
    }

    override fun toBytes(): ByteArray {
        val msgByte = ByteArray(size())
        var tmp: ByteArray? = null
        var len = 0
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(ucRecFalg)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(eccTrans)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(1)
        tmp[0] = eccOnline
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(iccFallBack)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(nIccDataLen)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(trans_id)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(ucSwipedFlag)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(mainAcc.size)
        tmp = mainAcc
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(tradeAmount.size)
        tmp = tradeAmount
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(tradeDate.size)
        tmp = tradeDate
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(tradeTime.size)
        tmp = tradeTime
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(operatorNo)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(lTraceNo)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(lNowBatchNum)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(szRespCode.size)
        tmp = szRespCode
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(bitmapsend.size)
        tmp = bitmapsend
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(resProcCode.size)
        tmp = resProcCode
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(tipAmount.size)
        tmp = tipAmount
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(tradeDateAndTime.size)
        tmp = tradeDateAndTime
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(expDate.size)
        tmp = expDate
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(entryMode.size)
        tmp = entryMode
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(field_26.size)
        tmp = field_26
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(desAndFrCardFlag_28)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(centerId.size)
        tmp = centerId
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(sysReferNo.size)
        tmp = sysReferNo
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(authCode.size)
        tmp = authCode
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(terminalNo.size)
        tmp = terminalNo
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(merchantNo.size)
        tmp = merchantNo
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(szIssuerBankId.size)
        tmp = szIssuerBankId
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(szRecvBankId.size)
        tmp = szRecvBankId
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(secondAmount.size)
        tmp = secondAmount
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(secondAcc.size)
        tmp = secondAcc
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(holdCardName.size)
        tmp = holdCardName
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(cardType.size)
        tmp = cardType
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(iccSn.size)
        tmp = iccSn
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(addInfo.size)
        tmp = addInfo
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(oldTraceNo)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(oldBatchNum)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(oldTransDate.size)
        tmp = oldTransDate
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(oldSysRefNo.size)
        tmp = oldSysRefNo
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(iccData.size)
        tmp = iccData
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(szCardUnit.size)
        tmp = szCardUnit
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(bPanSeqNoOk)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(1)
        tmp[0] = ucPanSeqNo
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(sAppCrypto.size)
        tmp = sAppCrypto
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(sAuthRspCode.size)
        tmp = sAuthRspCode
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(sTVR.size)
        tmp = sTVR
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(szAID.size)
        tmp = szAID
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(szAppLable.size)
        tmp = szAppLable
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(sTSI.size)
        tmp = sTSI
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(sATC.size)
        tmp = sATC
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(szAppPreferName.size)
        tmp = szAppPreferName
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(ec_Balance.size)
        tmp = ec_Balance
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(szCardTypeName.size)
        tmp = szCardTypeName
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(szAcquirer.size)
        tmp = szAcquirer
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(szIssuerResp.size)
        tmp = szIssuerResp
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(szCenterResp.size)
        tmp = szCenterResp
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(szRecvBankResp.size)
        tmp = szRecvBankResp
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(szTransCode.size)
        tmp = szTransCode
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(issueBankName.size)
        tmp = issueBankName
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(oldTransCode.size)
        tmp = oldTransCode
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
        len += 4
        len += 4
        len += 1
        len += 4
        len += 4
        len += 4
        len += 4
        len += mainAcc.size
        len += tradeAmount.size
        len += tradeDate.size
        len += tradeTime.size
        len += 4
        len += 4
        len += 4
        len += szRespCode.size
        len += bitmapsend.size
        len += resProcCode.size
        len += tipAmount.size
        len += tradeDateAndTime.size
        len += expDate.size
        len += entryMode.size
        len += field_26.size
        len += 4
        len += centerId.size
        len += sysReferNo.size
        len += authCode.size
        len += terminalNo.size
        len += merchantNo.size
        len += szIssuerBankId.size
        len += szRecvBankId.size
        len += secondAmount.size
        len += secondAcc.size
        len += holdCardName.size
        len += cardType.size
        len += iccSn.size
        len += addInfo.size
        len += 4
        len += 4
        len += oldTransDate.size
        len += oldSysRefNo.size
        len += iccData.size
        len += szCardUnit.size
        len += 4
        len += 1
        len += sAppCrypto.size
        len += sAuthRspCode.size
        len += sTVR.size
        len += szAID.size
        len += szAppLable.size
        len += sTSI.size
        len += sATC.size
        len += szAppPreferName.size
        len += ec_Balance.size
        len += szCardTypeName.size
        len += szAcquirer.size
        len += szIssuerResp.size
        len += szCenterResp.size
        len += szRecvBankResp.size
        len += szTransCode.size
        len += issueBankName.size
        len += oldTransCode.size
        if (len % 4 != 0) {
            len += 4 - len % 4
        }
        return len
    }

    override fun toBean(buf: ByteArray) {
        var tmp: ByteArray? = null
        var len = 0
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        ucRecFalg = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        eccTrans = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(1)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        eccOnline = tmp[0]
        len += tmp.size
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        iccFallBack = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        nIccDataLen = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        trans_id = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        ucSwipedFlag = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(mainAcc.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        mainAcc = tmp
        len += tmp.size
        tmp = ByteArray(tradeAmount.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        tradeAmount = tmp
        len += tmp.size
        tmp = ByteArray(tradeDate.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        tradeDate = tmp
        len += tmp.size
        tmp = ByteArray(tradeTime.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        tradeTime = tmp
        len += tmp.size
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        operatorNo = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        lTraceNo = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        lNowBatchNum = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(szRespCode.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        szRespCode = tmp
        len += tmp.size
        tmp = ByteArray(bitmapsend.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        bitmapsend = tmp
        len += tmp.size
        tmp = ByteArray(resProcCode.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        resProcCode = tmp
        len += tmp.size
        tmp = ByteArray(tipAmount.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        tipAmount = tmp
        len += tmp.size
        tmp = ByteArray(tradeDateAndTime.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        tradeDateAndTime = tmp
        len += tmp.size
        tmp = ByteArray(expDate.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        expDate = tmp
        len += tmp.size
        tmp = ByteArray(entryMode.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        entryMode = tmp
        len += tmp.size
        tmp = ByteArray(field_26.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        field_26 = tmp
        len += tmp.size
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        desAndFrCardFlag_28 = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(centerId.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        centerId = tmp
        len += tmp.size
        tmp = ByteArray(sysReferNo.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        sysReferNo = tmp
        len += tmp.size
        tmp = ByteArray(authCode.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        authCode = tmp
        len += tmp.size
        tmp = ByteArray(terminalNo.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        terminalNo = tmp
        len += tmp.size
        tmp = ByteArray(merchantNo.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        merchantNo = tmp
        len += tmp.size
        tmp = ByteArray(szIssuerBankId.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        szIssuerBankId = tmp
        len += tmp.size
        tmp = ByteArray(szRecvBankId.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        szRecvBankId = tmp
        len += tmp.size
        tmp = ByteArray(secondAmount.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        secondAmount = tmp
        len += tmp.size
        tmp = ByteArray(secondAcc.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        secondAcc = tmp
        len += tmp.size
        tmp = ByteArray(holdCardName.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        holdCardName = tmp
        len += tmp.size
        tmp = ByteArray(cardType.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        cardType = tmp
        len += tmp.size
        tmp = ByteArray(iccSn.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        iccSn = tmp
        len += tmp.size
        tmp = ByteArray(addInfo.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        addInfo = tmp
        len += tmp.size
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        oldTraceNo = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        oldBatchNum = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(oldTransDate.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        oldTransDate = tmp
        len += tmp.size
        tmp = ByteArray(oldSysRefNo.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        oldSysRefNo = tmp
        len += tmp.size
        tmp = ByteArray(iccData.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        iccData = tmp
        len += tmp.size
        tmp = ByteArray(szCardUnit.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        szCardUnit = tmp
        len += tmp.size
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        bPanSeqNoOk = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(1)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        ucPanSeqNo = tmp[0]
        len += tmp.size
        tmp = ByteArray(sAppCrypto.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        sAppCrypto = tmp
        len += tmp.size
        tmp = ByteArray(sAuthRspCode.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        sAuthRspCode = tmp
        len += tmp.size
        tmp = ByteArray(sTVR.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        sTVR = tmp
        len += tmp.size
        tmp = ByteArray(szAID.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        szAID = tmp
        len += tmp.size
        tmp = ByteArray(szAppLable.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        szAppLable = tmp
        len += tmp.size
        tmp = ByteArray(sTSI.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        sTSI = tmp
        len += tmp.size
        tmp = ByteArray(sATC.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        sATC = tmp
        len += tmp.size
        tmp = ByteArray(szAppPreferName.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        szAppPreferName = tmp
        len += tmp.size
        tmp = ByteArray(ec_Balance.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        ec_Balance = tmp
        len += tmp.size
        tmp = ByteArray(szCardTypeName.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        szCardTypeName = tmp
        len += tmp.size
        tmp = ByteArray(szAcquirer.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        szAcquirer = tmp
        len += tmp.size
        tmp = ByteArray(szIssuerResp.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        szIssuerResp = tmp
        len += tmp.size
        tmp = ByteArray(szCenterResp.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        szCenterResp = tmp
        len += tmp.size
        tmp = ByteArray(szRecvBankResp.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        szRecvBankResp = tmp
        len += tmp.size
        tmp = ByteArray(szTransCode.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        szTransCode = tmp
        len += tmp.size
        tmp = ByteArray(issueBankName.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        issueBankName = tmp
        len += tmp.size
        tmp = ByteArray(oldTransCode.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        oldTransCode = tmp
        len += tmp.size
    }
}
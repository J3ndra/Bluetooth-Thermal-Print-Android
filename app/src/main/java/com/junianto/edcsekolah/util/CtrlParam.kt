package com.junianto.edcsekolah.util

import com.vanstone.base.interfaces.StructInterface
import com.vanstone.utils.CommonConvert

class CtrlParam : StructInterface {
    var pinpad_type //PINPAD标志, 0-内置, 1-外置
            = 0
    var aKeyIndes //主密钥索引 单des（0-99）  3des 102-203
            = 0
    var mainKeyIdx //主密钥索引 单des（0-99）  3des 102-203
            = 0
    var pinKeyIndes //工作密钥pinkey密钥索引 单des（0-21  150-254）  3des 22-149
            = 0
    var macKeyIndes //工作密钥MacKey密钥索引 单des（0-21  150-254）  3des 22-149
            = 0
    var magKeyIndes //工作密钥MagKey密钥索引 单des（0-21  150-254）  3des 22-149(对磁道进行加密)
            = 0
    var lTraceNo //流水号
            = 0
    var termSNo = ByteArray(11)
    var beepForInput //是否按键时发声 0--缺省，不发声
            = 0
    var oprTimeoutValue //超时时限 控制超时时限，就是用户输入的超时
            = 0
    var tradeTimeoutValue //交易超时时限，等待中心应答数据的超时时限
            = 0
    var termSysAdminPwd = ByteArray(13) //管理员密钥 缺省20060101
    var payOptPwd = ByteArray(12) //支付密钥 缺省888888
    var logonDate = ByteArray(5) //签到日期 yyyymmdd bcd
    var managerPwd = ByteArray(12) //主管密码
    var terminalNo = ByteArray(9) //终端号 ans8
    var merchantNo = ByteArray(16) //商户号 ans12
    var finance_card = ByteArray(20) //结算卡号FINANCE_CARD
    var merchantName = ByteArray(41) //商户中文名称 ans40
    var currencyCode = ByteArray(4) //货币代码
    var cDTVer = ByteArray(18) //机器版本
    var version = ByteArray(8) //版本号
    var printErrReport //是否打印故障报告单
            = 0
    var maskForVoidTransPwd //撤销类交易是否输入密码得掩码 PWD_MASK_FOR_SALEVOID, PWD_MASK_FOR_PREAUTHVOID, PWD_MASK_FOR_AUTHDONEVOID, PWD_MASK_FOR_AUTHDONE
            = 0
    var maskForTransUseCard //交易是否出卡得掩码 CARD_MASK_FOR_SALEVOID, CARD_MASK_FOR_AUTHDONEVOID
            = 0
    var defaultTrade //缺省交易
            = 0
    var handInput //是否允许手输卡   (用于控制授权类交易)
            = 0
    var desType //Des类型 低半字节 1:单DES;0:3DES  高半字节：0-mackey,pinkey, 1--mackey,pinkey,magkey
            = 0
    var completeMode //磁卡交易预授权方式: 0 都支持  1 联机 2 离线
            = 0
    var preDial //是否预拨号
            = 0
    var promptICC //是否提示ICC卡 0 不提示 1 提示
            = 0
    var shieldPAN //是否屏蔽卡号 0 不屏蔽 1 屏蔽
            = 0
    var dCC_stage //是否是第一次上送主机
            = 0
    var preauth_flag //预授权标志PREAUTH
            = 0
    var icbccard_flag //本行卡标志, not used, always open
            = 0
    var pboc_flag //银联卡标志
            = 0
    var nwk_flag //内外卡标志NWK: 1-同时受理
            = 0
    var offline_flag //离线交易标志1-OFFLINE
            = 0
    var settle_flag //结账POS	  SETT--'1', settle pos open
            = 0
    var comm_type = 0
    var tickets_num //打印页数TICKETS, 1--1张,2--2张
            = 0
    var card_table // 是否安装卡表 CARDTABLEEXIST--'0',不安装
            = 0
    var pos_type // POS类型
            = 0
    var manual_flag //外卡手工输入标志 1-allowed
            = 0
    var terminalType //终端类型
            = 0
    var iTransNum //当前交易总笔数
            = 0
    var lNowBatchNum //批次号
            = 0
    var oper_limit = ByteArray(13) //操作员限额OP_JIN
    var manage_limit = ByteArray(13) //管理员限额GL_JIN
    var settDate = ByteArray(5) //结账时间
    var capkVer = ByteArray(9) //公钥版本
    var paraVer = ByteArray(13) //参数版本
    var appType //EMV新加
            = 0
    var forceOnline //EMV新加
            = 0
    var differential //级差
            = 0
    var lowAmt = ByteArray(13) //最低交易金额
    var maxAmt = ByteArray(13) //最高交易金额
    var maxRefundAmt = ByteArray(6) //退货最大金额
    var maxReimgursedAmt = ByteArray(6) //财务报销限额
    var paramIsDown //参数是否已经下载过
            = 0
    var mainKeyDown //主密钥是否已经下载
            = 0
    var supportICC //是否支持IC卡交易 0 不支持  1 支持
            = 0
    var supportDCC //支持DCC标志
            = 0
    var supportCNPC //支持中油标志
            = 0
    var supportECC //电子现金是否允许 终端是否支持电子现子功能  0:不支持  1:支持
            = 0
    var supportFallBack // 是否支持IC卡降级 0: 不支持, 1: 支持
            = 0
    var ifSupportPICC //是否非接卡
            : Byte = 0
    var ifSaleSupportPICC //消费是否支持挥卡
            : Byte = 0
    var supportPICC // 0不支持 3内置 4外置
            : Byte = 0
    var pICCDelayTime //非接寻卡等待时间
            : Byte = 0
    var offlineTranFlag //离线上送方式  (0 批结前 1 下笔联机)
            : Byte = 0
    var ucBatchStatus //当前批上送状态：RMBLOG 上送人民币卡 FRNLOG上送外卡 ALLLOG都上送状态  WORK_STATUS非批上送，工作状态
            = 0
    var ucClearLog //结算后是否未清日志    TURE:未清
            = 0
    var ucAutoLogoff //是否自动签退 1-是  0- 否
            = 0
    var ucLogonFlag //POS签到标志
            = 0
    var installment_flag //分期付款标志
            = 0
    var installment_BigAmt //分期大额标志
            = 0
    var installment_Maxnew = ByteArray(6) //分期付款限额
    var qpbocChannel //非接交易通道0--联机优先 1--脱机优先
            : Byte = 0
    var paypass //快速消费标志
            = 0
    var auth_flag //反交易是否授权标志AUTHFLAG
            = 0
    var tip_flag //小费标志FEEFLAG-'1'--closed
            = 0
    var tip_rate //小费百分比
            = 0
    var maxTradeNum //最大交易笔数
            = 0
    var maxSignNum //最大签名存储笔数
            : Short = 0
    var reverseTimes //冲正次数
            = 0
    var offlineTimes //离线上送笔数
            = 0
    var ifPrnDetail //是否打印明细
            = 0
    var ifPrnEnglish //签购单是否打印英文
            = 0
    var prntTicketType //签购单抬头(0 中文 1 银联LOGO 2农信银LOGO)
            : Byte = 0
    var ticketStype //签购单类型：0--旧 1--新
            = 0
    var useDefTicketHead //是否使用缺省的签购单抬头，1-是，0-否
            = 0
    var ticketHead = ByteArray(32) //签购单抬头：覆盖默认的抬头,最多支持10个汉字，20个字母
    var sManageNum = ByteArray(20) //管理电话
    var commSelf //mis是否自主通讯
            : Byte = 0
    var isMis //是否是MIS  1是MIS程序 0是传统POS
            : Byte = 0

    //每个交易的使能标识
    var transflag = ByteArray(8) //交易标志位
    var flagCentreReq //报文头中的中心处理要求
            = 0
    var flagReserve //保留
            = 0
    var emvDownFlag = ByteArray(4) //EMV参数等下载断点
    var reimCompanyCard = ByteArray(21) //报销交易注册的公司的卡号
    var nComBps //串口通讯的BPS速率
            = 0

    //说明0(0x0B开始下载capk; 0x0E下载param)1(当前capk断点)2(当前param断点)
    //tms 参数
    var terminalTmsVer = ByteArray(37) //当前终端应用的TMS端的版本号 响应为需要下载的版本号。格式为厂商(2)+型号(2)+版本号(32),不足用空格补足
    var downFlag //下载控制位  00不需要下载 01普通下载 02强制下载
            = 0
    var downTime = ByteArray(29) //下载时间
    var virtualTermNo = ByteArray(12) //虚拟终端号
    var appTradeType = ByteArray(2) //软件交易类型
    var prnENRecvBankId //打印中文收单行             0-否 1-是
            : Byte = 0
    var isCData //MIS传输数据是否加密
            : Byte = 0
    var bTName = ByteArray(14) //蓝牙名称
    var bTMacAdr = ByteArray(18) //蓝牙mac地址
    var uUID = ByteArray(32 + 1) //mpos和app绑定的唯一标识
    var appNum = ByteArray(16 + 1) //TMS应用程序版本号
    var misComPort //Miss与收银机串口通信的串口号
            : Byte = 0
    var commWithCash //mis与收银机通讯方式0串口,1蓝牙,2USB
            : Byte = 0

    //pbox扩展应用参数
    var supportQPbocExType //b1=1:支持分段扣费，b2=1:支持脱机预授权,
            = 0

    // b3=1:支持押金抵扣, b4=1:如果卡片不支持特定的扩展应用则继续交易，否则就终止交易
    var qpbocExSFI //qpboc扩展应用变长文件的SFI
            = 0
    var qpbocExAppId = ByteArray(2) //扩展应用ID号

    //手写板设置
    var supportSignPad //是否有手写板设备(0-不支持 1-内置 2-外接)
            : Byte = 0
    var supportSignPrn //主机是否支持电子签名
            : Byte = 0
    var supPrnMerSign //是否支持打印商户联和银行存根
            : Byte = 0
    var signTimeoutS //手写操作超时时间
            : Byte = 0
    var signRecNum //当前还有几笔未上送
            : Short = 0
    var signMaxNum //最多多少条未上送启动上送
            : Byte = 0
    var signBagMaxLen //手写包最大多少。如果超过这个值就要分包。
            : Short = 0
    var signBagFlag //分包传输
            : Byte = 0
    var supportPortionPaid //是否支持部分支付
            = 0
    var securityPwd = ByteArray(6 + 1)
    var tradeResendTimes //交易重复次数
            = 0
    var aIDForECCOnly = ByteArray(16) //纯电子现金卡的AID
    var lenOfAIDForECCOnly //纯电子现金卡的AID的长度
            = 0
    var bSupAuthRepresent //是否支持小额代授权
            = 0
    var agenyCode = ByteArray(13)
    var customNum = ByteArray(14)
    var fuyouWeb = ByteArray(20)
    var prtStatus //打印状态，0-打印完成，1-该打第一联，2……高位是重打印标志
            : Byte = 0
    var bMKeyNeedConfirm //主密钥未更新标志，需要发起 主密钥更新结束 交易
            : Byte = 0
    var sMasterKey = ByteArray(20) //主密钥(带有校验值)
    var commuProtocol //通讯协议 0.socket 1.https
            : Byte = 0
    var hostPath = ByteArray(128 + 1) //主机路径 适用于https

    // 闪卡相关设置参数	added by ms 0929
    var currFlashTimeout //
            = 0
    var flashCardTimeout //
            = 0
    var bEnableFlushCard // 是否支持闪卡 0 不支持  1 支持
            : Byte = 0
    var maxNumFashCardRec // 可保存的闪卡记录
            : Byte = 0

    // 20160929
    var ifNeedManagerKey // 是否需要主管密码(撤销/退货)
            = 0

    fun getlTraceNo(): Int {
        return lTraceNo
    }

    fun setlTraceNo(lTraceNo: Int) {
        this.lTraceNo = lTraceNo
    }

    fun getiTransNum(): Int {
        return iTransNum
    }

    fun setiTransNum(iTransNum: Int) {
        this.iTransNum = iTransNum
    }

    fun getlNowBatchNum(): Int {
        return lNowBatchNum
    }

    fun setlNowBatchNum(lNowBatchNum: Int) {
        this.lNowBatchNum = lNowBatchNum
    }

    fun getsManageNum(): ByteArray {
        return sManageNum
    }

    fun setsManageNum(sManageNum: ByteArray) {
        this.sManageNum = sManageNum
    }

    fun getnComBps(): Int {
        return nComBps
    }

    fun setnComBps(nComBps: Int) {
        this.nComBps = nComBps
    }

    fun getbSupAuthRepresent(): Int {
        return bSupAuthRepresent
    }

    fun setbSupAuthRepresent(bSupAuthRepresent: Int) {
        this.bSupAuthRepresent = bSupAuthRepresent
    }

    fun getbMKeyNeedConfirm(): Byte {
        return bMKeyNeedConfirm
    }

    fun setbMKeyNeedConfirm(bMKeyNeedConfirm: Byte) {
        this.bMKeyNeedConfirm = bMKeyNeedConfirm
    }

    fun getsMasterKey(): ByteArray {
        return sMasterKey
    }

    fun setsMasterKey(sMasterKey: ByteArray) {
        this.sMasterKey = sMasterKey
    }

    fun getbEnableFlushCard(): Byte {
        return bEnableFlushCard
    }

    fun setbEnableFlushCard(bEnableFlushCard: Byte) {
        this.bEnableFlushCard = bEnableFlushCard
    }

    override fun toBytes(): ByteArray {
        val msgByte = ByteArray(size())
        var tmp: ByteArray? = null
        var len = 0
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(pinpad_type)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(aKeyIndes)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(mainKeyIdx)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(pinKeyIndes)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(macKeyIndes)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(magKeyIndes)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(lTraceNo)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(termSNo.size)
        tmp = termSNo
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(beepForInput)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(oprTimeoutValue)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(tradeTimeoutValue)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(termSysAdminPwd.size)
        tmp = termSysAdminPwd
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(payOptPwd.size)
        tmp = payOptPwd
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(logonDate.size)
        tmp = logonDate
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(managerPwd.size)
        tmp = managerPwd
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
        tmp = ByteArray(finance_card.size)
        tmp = finance_card
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(merchantName.size)
        tmp = merchantName
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(currencyCode.size)
        tmp = currencyCode
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(cDTVer.size)
        tmp = cDTVer
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(version.size)
        tmp = version
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(printErrReport)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(maskForVoidTransPwd)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(maskForTransUseCard)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(defaultTrade)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(handInput)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(desType)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(completeMode)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(preDial)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(promptICC)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(shieldPAN)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(dCC_stage)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(preauth_flag)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(icbccard_flag)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(pboc_flag)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(nwk_flag)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(offline_flag)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(settle_flag)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(comm_type)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(tickets_num)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(card_table)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(pos_type)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(manual_flag)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(terminalType)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(iTransNum)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(lNowBatchNum)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(oper_limit.size)
        tmp = oper_limit
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(manage_limit.size)
        tmp = manage_limit
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(settDate.size)
        tmp = settDate
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(capkVer.size)
        tmp = capkVer
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(paraVer.size)
        tmp = paraVer
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(appType)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(forceOnline)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(differential)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(lowAmt.size)
        tmp = lowAmt
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(maxAmt.size)
        tmp = maxAmt
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(maxRefundAmt.size)
        tmp = maxRefundAmt
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(maxReimgursedAmt.size)
        tmp = maxReimgursedAmt
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(paramIsDown)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(mainKeyDown)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(supportICC)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(supportDCC)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(supportCNPC)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(supportECC)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(supportFallBack)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(1)
        tmp[0] = ifSupportPICC
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(1)
        tmp[0] = ifSaleSupportPICC
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(1)
        tmp[0] = supportPICC
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(1)
        tmp[0] = pICCDelayTime
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(1)
        tmp[0] = offlineTranFlag
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(ucBatchStatus)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(ucClearLog)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(ucAutoLogoff)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(ucLogonFlag)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(installment_flag)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(installment_BigAmt)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(installment_Maxnew.size)
        tmp = installment_Maxnew
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(1)
        tmp[0] = qpbocChannel
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(paypass)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(auth_flag)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(tip_flag)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(tip_rate)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(maxTradeNum)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(2)
        tmp = CommonConvert.shortToBytes(maxSignNum)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 2
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(reverseTimes)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(offlineTimes)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(ifPrnDetail)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(ifPrnEnglish)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(1)
        tmp[0] = prntTicketType
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(ticketStype)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(useDefTicketHead)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(ticketHead.size)
        tmp = ticketHead
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(sManageNum.size)
        tmp = sManageNum
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(1)
        tmp[0] = commSelf
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(1)
        tmp[0] = isMis
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(transflag.size)
        tmp = transflag
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(flagCentreReq)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(flagReserve)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(emvDownFlag.size)
        tmp = emvDownFlag
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(reimCompanyCard.size)
        tmp = reimCompanyCard
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(nComBps)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(terminalTmsVer.size)
        tmp = terminalTmsVer
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(downFlag)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(downTime.size)
        tmp = downTime
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(virtualTermNo.size)
        tmp = virtualTermNo
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(appTradeType.size)
        tmp = appTradeType
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(1)
        tmp[0] = prnENRecvBankId
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(1)
        tmp[0] = isCData
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(bTName.size)
        tmp = bTName
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(bTMacAdr.size)
        tmp = bTMacAdr
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(uUID.size)
        tmp = uUID
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(appNum.size)
        tmp = appNum
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(1)
        tmp[0] = misComPort
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(1)
        tmp[0] = commWithCash
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(supportQPbocExType)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(qpbocExSFI)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(qpbocExAppId.size)
        tmp = qpbocExAppId
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(1)
        tmp[0] = supportSignPad
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(1)
        tmp[0] = supportSignPrn
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(1)
        tmp[0] = supPrnMerSign
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(1)
        tmp[0] = signTimeoutS
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(2)
        tmp = CommonConvert.shortToBytes(signRecNum)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 2
        tmp = ByteArray(1)
        tmp[0] = signMaxNum
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(2)
        tmp = CommonConvert.shortToBytes(signBagMaxLen)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 2
        tmp = ByteArray(1)
        tmp[0] = signBagFlag
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(supportPortionPaid)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(securityPwd.size)
        tmp = securityPwd
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(tradeResendTimes)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(aIDForECCOnly.size)
        tmp = aIDForECCOnly
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(lenOfAIDForECCOnly)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(bSupAuthRepresent)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(agenyCode.size)
        tmp = agenyCode
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(customNum.size)
        tmp = customNum
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(fuyouWeb.size)
        tmp = fuyouWeb
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(1)
        tmp[0] = prtStatus
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(1)
        tmp[0] = bMKeyNeedConfirm
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(sMasterKey.size)
        tmp = sMasterKey
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(1)
        tmp[0] = commuProtocol
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(hostPath.size)
        tmp = hostPath
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(currFlashTimeout)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(flashCardTimeout)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
        tmp = ByteArray(1)
        tmp[0] = bEnableFlushCard
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(1)
        tmp[0] = maxNumFashCardRec
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += tmp.size
        tmp = ByteArray(4)
        tmp = CommonConvert.intToBytes(ifNeedManagerKey)
        System.arraycopy(tmp, 0, msgByte, len, tmp.size)
        len += 4
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
        len += 4
        len += 4
        len += 4
        len += 4
        len += 4
        len += termSNo.size
        len += 4
        len += 4
        len += 4
        len += termSysAdminPwd.size
        len += payOptPwd.size
        len += logonDate.size
        len += managerPwd.size
        len += terminalNo.size
        len += merchantNo.size
        len += finance_card.size
        len += merchantName.size
        len += currencyCode.size
        len += cDTVer.size
        len += version.size
        len += 4
        len += 4
        len += 4
        len += 4
        len += 4
        len += 4
        len += 4
        len += 4
        len += 4
        len += 4
        len += 4
        len += 4
        len += 4
        len += 4
        len += 4
        len += 4
        len += 4
        len += 4
        len += 4
        len += 4
        len += 4
        len += 4
        len += 4
        len += 4
        len += 4
        len += oper_limit.size
        len += manage_limit.size
        len += settDate.size
        len += capkVer.size
        len += paraVer.size
        len += 4
        len += 4
        len += 4
        len += lowAmt.size
        len += maxAmt.size
        len += maxRefundAmt.size
        len += maxReimgursedAmt.size
        len += 4
        len += 4
        len += 4
        len += 4
        len += 4
        len += 4
        len += 4
        len += 1
        len += 1
        len += 1
        len += 1
        len += 1
        len += 4
        len += 4
        len += 4
        len += 4
        len += 4
        len += 4
        len += installment_Maxnew.size
        len += 1
        len += 4
        len += 4
        len += 4
        len += 4
        len += 4
        len += 2
        len += 4
        len += 4
        len += 4
        len += 4
        len += 1
        len += 4
        len += 4
        len += ticketHead.size
        len += sManageNum.size
        len += 1
        len += 1
        len += transflag.size
        len += 4
        len += 4
        len += emvDownFlag.size
        len += reimCompanyCard.size
        len += 4
        len += terminalTmsVer.size
        len += 4
        len += downTime.size
        len += virtualTermNo.size
        len += appTradeType.size
        len += 1
        len += 1
        len += bTName.size
        len += bTMacAdr.size
        len += uUID.size
        len += appNum.size
        len += 1
        len += 1
        len += 4
        len += 4
        len += qpbocExAppId.size
        len += 1
        len += 1
        len += 1
        len += 1
        len += 2
        len += 1
        len += 2
        len += 1
        len += 4
        len += securityPwd.size
        len += 4
        len += aIDForECCOnly.size
        len += 4
        len += 4
        len += agenyCode.size
        len += customNum.size
        len += fuyouWeb.size
        len += 1
        len += 1
        len += sMasterKey.size
        len += 1
        len += hostPath.size
        len += 4
        len += 4
        len += 1
        len += 1
        len += 4
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
        pinpad_type = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        aKeyIndes = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        mainKeyIdx = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        pinKeyIndes = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        macKeyIndes = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        magKeyIndes = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        lTraceNo = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(termSNo.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        termSNo = tmp
        len += tmp.size
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        beepForInput = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        oprTimeoutValue = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        tradeTimeoutValue = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(termSysAdminPwd.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        termSysAdminPwd = tmp
        len += tmp.size
        tmp = ByteArray(payOptPwd.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        payOptPwd = tmp
        len += tmp.size
        tmp = ByteArray(logonDate.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        logonDate = tmp
        len += tmp.size
        tmp = ByteArray(managerPwd.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        managerPwd = tmp
        len += tmp.size
        tmp = ByteArray(terminalNo.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        terminalNo = tmp
        len += tmp.size
        tmp = ByteArray(merchantNo.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        merchantNo = tmp
        len += tmp.size
        tmp = ByteArray(finance_card.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        finance_card = tmp
        len += tmp.size
        tmp = ByteArray(merchantName.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        merchantName = tmp
        len += tmp.size
        tmp = ByteArray(currencyCode.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        currencyCode = tmp
        len += tmp.size
        tmp = ByteArray(cDTVer.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        cDTVer = tmp
        len += tmp.size
        tmp = ByteArray(version.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        version = tmp
        len += tmp.size
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        printErrReport = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        maskForVoidTransPwd = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        maskForTransUseCard = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        defaultTrade = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        handInput = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        desType = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        completeMode = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        preDial = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        promptICC = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        shieldPAN = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        dCC_stage = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        preauth_flag = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        icbccard_flag = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        pboc_flag = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        nwk_flag = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        offline_flag = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        settle_flag = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        comm_type = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        tickets_num = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        card_table = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        pos_type = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        manual_flag = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        terminalType = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        iTransNum = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        lNowBatchNum = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(oper_limit.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        oper_limit = tmp
        len += tmp.size
        tmp = ByteArray(manage_limit.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        manage_limit = tmp
        len += tmp.size
        tmp = ByteArray(settDate.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        settDate = tmp
        len += tmp.size
        tmp = ByteArray(capkVer.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        capkVer = tmp
        len += tmp.size
        tmp = ByteArray(paraVer.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        paraVer = tmp
        len += tmp.size
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        appType = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        forceOnline = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        differential = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(lowAmt.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        lowAmt = tmp
        len += tmp.size
        tmp = ByteArray(maxAmt.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        maxAmt = tmp
        len += tmp.size
        tmp = ByteArray(maxRefundAmt.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        maxRefundAmt = tmp
        len += tmp.size
        tmp = ByteArray(maxReimgursedAmt.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        maxReimgursedAmt = tmp
        len += tmp.size
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        paramIsDown = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        mainKeyDown = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        supportICC = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        supportDCC = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        supportCNPC = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        supportECC = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        supportFallBack = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(1)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        ifSupportPICC = tmp[0]
        len += tmp.size
        tmp = ByteArray(1)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        ifSaleSupportPICC = tmp[0]
        len += tmp.size
        tmp = ByteArray(1)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        supportPICC = tmp[0]
        len += tmp.size
        tmp = ByteArray(1)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        pICCDelayTime = tmp[0]
        len += tmp.size
        tmp = ByteArray(1)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        offlineTranFlag = tmp[0]
        len += tmp.size
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        ucBatchStatus = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        ucClearLog = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        ucAutoLogoff = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        ucLogonFlag = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        installment_flag = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        installment_BigAmt = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(installment_Maxnew.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        installment_Maxnew = tmp
        len += tmp.size
        tmp = ByteArray(1)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        qpbocChannel = tmp[0]
        len += tmp.size
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        paypass = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        auth_flag = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        tip_flag = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        tip_rate = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        maxTradeNum = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(2)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        maxSignNum = CommonConvert.bytesToShort(tmp)
        len += 2
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        reverseTimes = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        offlineTimes = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        ifPrnDetail = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        ifPrnEnglish = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(1)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        prntTicketType = tmp[0]
        len += tmp.size
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        ticketStype = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        useDefTicketHead = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(ticketHead.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        ticketHead = tmp
        len += tmp.size
        tmp = ByteArray(sManageNum.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        sManageNum = tmp
        len += tmp.size
        tmp = ByteArray(1)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        commSelf = tmp[0]
        len += tmp.size
        tmp = ByteArray(1)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        isMis = tmp[0]
        len += tmp.size
        tmp = ByteArray(transflag.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        transflag = tmp
        len += tmp.size
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        flagCentreReq = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        flagReserve = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(emvDownFlag.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        emvDownFlag = tmp
        len += tmp.size
        tmp = ByteArray(reimCompanyCard.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        reimCompanyCard = tmp
        len += tmp.size
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        nComBps = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(terminalTmsVer.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        terminalTmsVer = tmp
        len += tmp.size
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        downFlag = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(downTime.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        downTime = tmp
        len += tmp.size
        tmp = ByteArray(virtualTermNo.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        virtualTermNo = tmp
        len += tmp.size
        tmp = ByteArray(appTradeType.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        appTradeType = tmp
        len += tmp.size
        tmp = ByteArray(1)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        prnENRecvBankId = tmp[0]
        len += tmp.size
        tmp = ByteArray(1)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        isCData = tmp[0]
        len += tmp.size
        tmp = ByteArray(bTName.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        bTName = tmp
        len += tmp.size
        tmp = ByteArray(bTMacAdr.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        bTMacAdr = tmp
        len += tmp.size
        tmp = ByteArray(uUID.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        uUID = tmp
        len += tmp.size
        tmp = ByteArray(appNum.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        appNum = tmp
        len += tmp.size
        tmp = ByteArray(1)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        misComPort = tmp[0]
        len += tmp.size
        tmp = ByteArray(1)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        commWithCash = tmp[0]
        len += tmp.size
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        supportQPbocExType = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        qpbocExSFI = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(qpbocExAppId.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        qpbocExAppId = tmp
        len += tmp.size
        tmp = ByteArray(1)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        supportSignPad = tmp[0]
        len += tmp.size
        tmp = ByteArray(1)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        supportSignPrn = tmp[0]
        len += tmp.size
        tmp = ByteArray(1)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        supPrnMerSign = tmp[0]
        len += tmp.size
        tmp = ByteArray(1)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        signTimeoutS = tmp[0]
        len += tmp.size
        tmp = ByteArray(2)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        signRecNum = CommonConvert.bytesToShort(tmp)
        len += 2
        tmp = ByteArray(1)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        signMaxNum = tmp[0]
        len += tmp.size
        tmp = ByteArray(2)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        signBagMaxLen = CommonConvert.bytesToShort(tmp)
        len += 2
        tmp = ByteArray(1)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        signBagFlag = tmp[0]
        len += tmp.size
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        supportPortionPaid = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(securityPwd.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        securityPwd = tmp
        len += tmp.size
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        tradeResendTimes = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(aIDForECCOnly.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        aIDForECCOnly = tmp
        len += tmp.size
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        lenOfAIDForECCOnly = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        bSupAuthRepresent = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(agenyCode.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        agenyCode = tmp
        len += tmp.size
        tmp = ByteArray(customNum.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        customNum = tmp
        len += tmp.size
        tmp = ByteArray(fuyouWeb.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        fuyouWeb = tmp
        len += tmp.size
        tmp = ByteArray(1)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        prtStatus = tmp[0]
        len += tmp.size
        tmp = ByteArray(1)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        bMKeyNeedConfirm = tmp[0]
        len += tmp.size
        tmp = ByteArray(sMasterKey.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        sMasterKey = tmp
        len += tmp.size
        tmp = ByteArray(1)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        commuProtocol = tmp[0]
        len += tmp.size
        tmp = ByteArray(hostPath.size)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        hostPath = tmp
        len += tmp.size
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        currFlashTimeout = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        flashCardTimeout = CommonConvert.bytesToInt(tmp)
        len += 4
        tmp = ByteArray(1)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        bEnableFlushCard = tmp[0]
        len += tmp.size
        tmp = ByteArray(1)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        maxNumFashCardRec = tmp[0]
        len += tmp.size
        tmp = ByteArray(4)
        System.arraycopy(buf, len, tmp, 0, tmp.size)
        ifNeedManagerKey = CommonConvert.bytesToInt(tmp)
        len += 4
    }
}
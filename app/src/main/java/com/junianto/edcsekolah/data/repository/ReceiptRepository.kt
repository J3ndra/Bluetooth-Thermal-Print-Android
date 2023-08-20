package com.junianto.edcsekolah.data.repository

import androidx.lifecycle.LiveData
import com.junianto.edcsekolah.data.dao.ReceiptDao
import com.junianto.edcsekolah.data.model.Receipt
import javax.inject.Inject

class ReceiptRepository @Inject constructor(private val receiptDao: ReceiptDao) {
    fun getAllReceipts(): List<Receipt> {
        return receiptDao.getAllReceipts()
    }

    fun insertReceipt(receipt: Receipt): Long {
        return receiptDao.insertReceipt(receipt)
    }

    suspend fun deleteAllReceiptsAndResetId() {
        receiptDao.deleteAllReceipts()
        receiptDao.resetIdCounter()
    }

    fun searchReceiptById(id: Int): Receipt {
        return receiptDao.searchReceiptById(id)
    }

    fun didReceiptExist(id: Int): LiveData<Boolean> {
        return receiptDao.didReceiptExist(id)
    }

    fun deleteReceiptById(id: Int) {
        receiptDao.deleteReceiptById(id)
    }
}
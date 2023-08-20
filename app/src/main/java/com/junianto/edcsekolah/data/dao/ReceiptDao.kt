package com.junianto.edcsekolah.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.junianto.edcsekolah.data.model.Receipt

@Dao
interface ReceiptDao {
    @Query("SELECT * FROM receipts")
    fun getAllReceipts(): List<Receipt>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReceipt(receipt: Receipt): Long

    @Query("DELETE FROM receipts")
    fun deleteAllReceipts()

    @Query("SELECT * FROM receipts WHERE id = :id")
    fun searchReceiptById(id: Int): Receipt

    @Query("DELETE FROM receipts WHERE id = :id")
    fun deleteReceiptById(id: Int)

    @Query("SELECT EXISTS(SELECT * FROM receipts WHERE id = :id)")
    fun didReceiptExist(id: Int): LiveData<Boolean>

    @Query("DELETE FROM sqlite_sequence WHERE name = 'receipts'")
    fun resetIdCounter()
}
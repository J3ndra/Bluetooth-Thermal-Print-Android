package com.junianto.edcsekolah.data.dao

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

    @Query("DELETE FROM sqlite_sequence WHERE name = 'receipts'")
    fun resetIdCounter()
}
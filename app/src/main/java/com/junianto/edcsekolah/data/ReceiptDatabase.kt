package com.junianto.edcsekolah.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.junianto.edcsekolah.data.dao.ReceiptDao
import com.junianto.edcsekolah.data.model.Receipt

@Database(entities = [Receipt::class], version = 1, exportSchema = false)
abstract class ReceiptDatabase : RoomDatabase() {
    abstract fun receiptDao(): ReceiptDao
}
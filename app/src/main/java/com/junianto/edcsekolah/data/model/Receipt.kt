package com.junianto.edcsekolah.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "receipts")
data class Receipt(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "amount")
    val amount: Int,

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "card_id")
    val cardId: String,

    @ColumnInfo(name = "status")
    val status: Boolean,
)
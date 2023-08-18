package com.junianto.edcsekolah.menu.settlement.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.junianto.edcsekolah.data.repository.ReceiptRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SettlementViewModel @Inject constructor(private val repository: ReceiptRepository) : ViewModel() {
    val allReceipts = liveData(Dispatchers.IO) {
        val receipts = repository.getAllReceipts()
        emit(receipts)
    }

    suspend fun deleteAllReceiptsAndResetId() {
        withContext(Dispatchers.IO) {
            repository.deleteAllReceiptsAndResetId()
        }
    }
}
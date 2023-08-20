package com.junianto.edcsekolah.menu.reprint.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.junianto.edcsekolah.data.repository.ReceiptRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class ReprintViewModel @Inject constructor(private val repository: ReceiptRepository) : ViewModel() {
    val allReceipts = liveData(Dispatchers.IO) {
        val receipts = repository.getAllReceipts()
        emit(receipts)
    }
}
package com.junianto.edcsekolah.menu.sale.viewmodel

import androidx.lifecycle.ViewModel
import com.junianto.edcsekolah.data.model.Receipt
import com.junianto.edcsekolah.data.repository.ReceiptRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaleViewModel @Inject constructor(private val repository: ReceiptRepository) : ViewModel() {

    fun insertReceipt(receipt: Receipt, onReceiptInserted: (Long) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val insertedId = repository.insertReceipt(receipt)
            onReceiptInserted(insertedId)
        }
    }
}
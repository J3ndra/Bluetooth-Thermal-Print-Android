package com.junianto.edcsekolah.menu.delete.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junianto.edcsekolah.data.model.Receipt
import com.junianto.edcsekolah.data.repository.ReceiptRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class VoidViewModel @Inject constructor(private val repository: ReceiptRepository) : ViewModel() {
    private val _receiptDetail = MutableLiveData<Receipt>()
    val receiptDetail: LiveData<Receipt> = _receiptDetail

    fun searchReceiptById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) { // Use Dispatchers.IO here
            val receipt = repository.searchReceiptById(id)
            withContext(Dispatchers.Main) { // Switch back to the main thread for updating LiveData
                _receiptDetail.value = receipt
            }
        }
    }

    fun didReceiptExist(id: Int): LiveData<Boolean> {
        return repository.didReceiptExist(id)
    }
}
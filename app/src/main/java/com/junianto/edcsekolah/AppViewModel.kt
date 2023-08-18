package com.junianto.edcsekolah

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.junianto.edcsekolah.data.model.AppSetup
import com.junianto.edcsekolah.data.repository.AppSetupRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val appSetupRepository: AppSetupRepository) : ViewModel() {

    val appSetup: LiveData<AppSetup> = appSetupRepository.getAppSetup().asLiveData()

    fun updateAppSetup(schoolName: String, schoolAddress: String, majorName: String) {
        viewModelScope.launch {
            appSetupRepository.updateAppSetup(schoolName, schoolAddress, majorName)
        }
    }
}
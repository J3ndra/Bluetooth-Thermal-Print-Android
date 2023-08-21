package com.junianto.edcsekolah.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.junianto.edcsekolah.data.model.AppSetup
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

interface AppSetupRepository {
    fun getAppSetup(): Flow<AppSetup>
    suspend fun updateAppSetup(school_name: String, school_address: String, major_name: String, school_logo: String)
}

@Singleton
class DataStoreAppSetupRepository @Inject constructor(context: Context) : AppSetupRepository {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "APP_SETUP")
    private val dataStore = context.dataStore

    override fun getAppSetup(): Flow<AppSetup> {
        return dataStore.data.map { preferences ->
            val schoolName = preferences[APP_SCHOOL_NAME_KEY] ?: "SMK Negeri 1 Kediri"
            val schoolAddress = preferences[APP_SCHOOL_ADDRESS_KEY] ?: "Jl. Raya Kediri - Pare, Kec. Mojoroto, Kota Kediri, Jawa Timur 64118"
            val majorName = preferences[APP_MAJOR_NAME_KEY] ?: "Rekayasa Perangkat Lunak"
            val schoolLogo = preferences[APP_SCHOOL_LOGO] ?: ""

            AppSetup(schoolName, schoolAddress, majorName, schoolLogo)
        }
    }

    override suspend fun updateAppSetup(school_name: String, school_address: String, major_name: String, school_logo: String) {
        dataStore.edit { preferences ->
            preferences[APP_SCHOOL_NAME_KEY] = school_name
            preferences[APP_SCHOOL_ADDRESS_KEY] = school_address
            preferences[APP_MAJOR_NAME_KEY] = major_name
            preferences[APP_SCHOOL_LOGO] = school_logo
        }
    }

    companion object {
        private val APP_SCHOOL_NAME_KEY = stringPreferencesKey("school_name")
        private val APP_SCHOOL_ADDRESS_KEY = stringPreferencesKey("school_address")
        private val APP_MAJOR_NAME_KEY = stringPreferencesKey("major_name")
        private val APP_SCHOOL_LOGO = stringPreferencesKey("school_logo")
    }
}
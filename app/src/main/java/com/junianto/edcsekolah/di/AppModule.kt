package com.junianto.edcsekolah.di

import android.app.Application
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.nfc.NfcAdapter
import androidx.room.Room
import com.junianto.edcsekolah.data.ReceiptDatabase
import com.junianto.edcsekolah.data.dao.ReceiptDao
import com.junianto.edcsekolah.data.repository.AppSetupRepository
import com.junianto.edcsekolah.data.repository.DataStoreAppSetupRepository
import com.junianto.edcsekolah.util.BluetoothManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideBluetoothAdapter(context: Context): BluetoothAdapter {
        val bluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as android.bluetooth.BluetoothManager
        return bluetoothManager.adapter
    }

    @Provides
    @Singleton
    fun provideBluetoothManager(bluetoothAdapter: BluetoothAdapter): BluetoothManager {
        return BluetoothManager(bluetoothAdapter)
    }

    @Provides
    @Singleton
    fun provideAppSetupRepository(repository: DataStoreAppSetupRepository): AppSetupRepository = repository

    @Provides
    @Singleton
    fun provideNfcAdapter(context: Context): NfcAdapter {
        return NfcAdapter.getDefaultAdapter(context)
    }

    // ROOM DATABASE
    @Provides
    @Singleton
    fun provideReceiptDatabase(@ApplicationContext context: Context): ReceiptDatabase {
        return Room.databaseBuilder(
            context,
            ReceiptDatabase::class.java,
            "receipt_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideReceiptDao(receiptDatabase: ReceiptDatabase): ReceiptDao {
        return receiptDatabase.receiptDao()
    }
}
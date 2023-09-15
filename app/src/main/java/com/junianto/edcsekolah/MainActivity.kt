package com.junianto.edcsekolah

import android.content.pm.PackageManager
import android.nfc.NfcAdapter
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.junianto.edcsekolah.menu.emoney.NfcTapFragment
import com.junianto.edcsekolah.util.ByteArrayToHexString
import com.junianto.edcsekolah.util.DeviceInfo
import com.vanstone.appsdk.client.ISdkStatue
import com.vanstone.l2.Common
import com.vanstone.l2.CommonCB
import com.vanstone.trans.api.CommApi
import com.vanstone.trans.api.SystemApi
import com.vanstone.utils.ByteUtils
import com.vanstone.utils.CommonConvert
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private val BLUETOOTH_PERMISSIONS_REQUEST_CODE = 1

    private val bluetoothPermissions = arrayOf(
        "android.permission.BLUETOOTH",
        "android.permission.BLUETOOTH_ADMIN",
        "android.permission.BLUETOOTH_SCAN",
        "android.permission.BLUETOOTH_ADVERTISE",
        "android.permission.BLUETOOTH_CONNECT",
        "android.permission.ACCESS_FINE_LOCATION",
        "android.permission.ACCESS_COARSE_LOCATION",
        "android.permission.READ_MEDIA_IMAGES",
        "android.permission.READ_EXTERNAL_STORAGE",
        "android.permission.WRITE_EXTERNAL_STORAGE",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cpuArchitecture = DeviceInfo.getCpuArchitecture()
        Timber.i("CPU Architecture: $cpuArchitecture")

        when (cpuArchitecture) {
            "armeabi" -> {
                initA90Sdk()
            }
            "armeabi-v7a" -> {
                initA90Sdk()
            }
            else -> {
                Toast.makeText(this, "Unsupported CPU Architecture", Toast.LENGTH_LONG).show()
            }
        }

        Timber.i("MainActivity created")

        // Check if permissions are granted
        if (!areBluetoothPermissionsGranted()) {
            requestBluetoothPermissions()
        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun areBluetoothPermissionsGranted(): Boolean {
        for (permission in bluetoothPermissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    private fun requestBluetoothPermissions() {
        ActivityCompat.requestPermissions(this, bluetoothPermissions, BLUETOOTH_PERMISSIONS_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            BLUETOOTH_PERMISSIONS_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                    // TODO: Do something
                } else {
                    // TODO: Do something
                }
            }
        }
    }

    private fun initA90Sdk() {
        val curAppDir = applicationContext.filesDir.absolutePath
        SystemApi.SystemInit_Api(0, CommonConvert.StringToBytes("$curAppDir/\u0000"), this, object : ISdkStatue {
            override fun sdkInitSuccessed() {
                CommApi.InitComm_Api(applicationContext)
                Common.Init_Api()
                Common.DbgEN_Api(1)

                Common.setCallback(ccb)

                Timber.i("SDK init successed")
            }

            override fun sdkInitFailed() {
                Timber.e("SDK init failed")
            }
        })
    }

    private val ccb = object : CommonCB {
        override fun GetDateTime(bytes: ByteArray): Int {
            val dataTimeTemp = ByteArray(10)
            SystemApi.GetSysTime_Api(dataTimeTemp)
            ByteUtils.memcpy(bytes, 0, dataTimeTemp, 1, 6)
            return 0
        }

        override fun ReadSN(bytes: ByteArray): Int {

            return 0
        }

        override fun GetUnknowTLV(i: Int, bytes: ByteArray, i1: Int): Int {
            return -1
        }
    }
}
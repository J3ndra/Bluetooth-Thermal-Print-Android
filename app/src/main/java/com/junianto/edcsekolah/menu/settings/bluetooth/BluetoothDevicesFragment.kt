package com.junianto.edcsekolah.menu.settings.bluetooth

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.junianto.edcsekolah.R
import com.junianto.edcsekolah.util.BluetoothManager
import com.mazenrashed.printooth.Printooth
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class BluetoothDevicesFragment : Fragment() {

    @Inject
    lateinit var bluetoothManager: BluetoothManager

    private lateinit var tvBluetoothDevice: TextView
    private lateinit var btConnectBluetooth: Button

    private var bluetoothName = ""
    private var bluetoothAddress = ""

    private var isBluetoothConnected = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_bluetooth_devices, container, false)

        tvBluetoothDevice = rootView.findViewById(R.id.tv_current_bluetooth_device)
        btConnectBluetooth = rootView.findViewById(R.id.btn_bluetooth)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvBluetoothDevices: RecyclerView = view.findViewById(R.id.rv_paired_bluetooth)

        val pairedDevices: Set<BluetoothDevice> = bluetoothManager.getPairedDevices()

        val adapter = BluetoothAdapter(pairedDevices)
        rvBluetoothDevices.layoutManager = LinearLayoutManager(requireContext())
        rvBluetoothDevices.adapter = adapter

        Printooth.getPairedPrinter()?.let {
            tvBluetoothDevice.text = buildString {
                append(it.name)
                append("(${it.address})")
            }

            isBluetoothConnected = true

            btConnectBluetooth.text = getString(R.string.text_bluetooth_disconnect)
        }

        adapter.setListener(object : BluetoothAdapterListener {

            @SuppressLint("MissingPermission")
            override fun onDeviceSelected(device: BluetoothDevice) {
                // Handle the selected Bluetooth device
                Timber.i("Selected device: ${device.name} | ${device.address} | ${device.uuids} | ${device.bondState} | ${device.type} | ${device.bluetoothClass} | ${device.bluetoothClass.deviceClass} | ${device.bluetoothClass.majorDeviceClass}")

                bluetoothName = device.name
                bluetoothAddress = device.address

                tvBluetoothDevice.text = buildString {
                    append(device.name)
                    append("(${device.address})")
                }

                btConnectBluetooth.text = getString(R.string.text_bluetooth_connect)
                isBluetoothConnected = false
            }
        })

        btConnectBluetooth.setOnClickListener {
            if (isBluetoothConnected) {
                Printooth.removeCurrentPrinter()
                isBluetoothConnected = false
                tvBluetoothDevice.setText(R.string.text_no_bluetooth_connected)
                btConnectBluetooth.text = getString(R.string.text_bluetooth_connect)
            } else {
                Printooth.setPrinter(bluetoothName, bluetoothAddress)
                isBluetoothConnected = true
                btConnectBluetooth.text = getString(R.string.text_bluetooth_disconnect)
            }
        }
    }
}
package com.junianto.edcsekolah.menu.settings.bluetooth

import android.bluetooth.BluetoothDevice
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.junianto.edcsekolah.R
import com.junianto.edcsekolah.util.BluetoothManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BluetoothDevicesFragment : Fragment() {

    @Inject
    lateinit var bluetoothManager: BluetoothManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bluetooth_devices, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvBluetoothDevices: RecyclerView = view.findViewById(R.id.rv_paired_bluetooth)

        val pairedDevices: Set<BluetoothDevice> = bluetoothManager.getPairedDevices()

        val adapter = BluetoothAdapter(pairedDevices)
        rvBluetoothDevices.layoutManager = LinearLayoutManager(requireContext())
        rvBluetoothDevices.adapter = adapter
    }
}
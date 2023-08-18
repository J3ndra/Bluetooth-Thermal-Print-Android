package com.junianto.edcsekolah.menu.settings.bluetooth

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.junianto.edcsekolah.R

class BluetoothAdapter(private val devices: Set<BluetoothDevice>) : RecyclerView.Adapter<BluetoothAdapter.DeviceViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_bluetooth_device, parent, false)
        return DeviceViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return devices.size
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        val device = devices.elementAt(position)
        return holder.bind(device)
    }

    class DeviceViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val tvDeviceName: TextView = itemView.findViewById(R.id.tv_device_name)
        private val tvDeviceAddress: TextView = itemView.findViewById(R.id.tv_device_address)
        @SuppressLint("MissingPermission")
        fun bind(device: BluetoothDevice) {
            tvDeviceName.text = device.name
            tvDeviceAddress.text = device.address
        }
    }
}
package com.junianto.edcsekolah.menu.settings

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.junianto.edcsekolah.R
import com.junianto.edcsekolah.menu.settings.model.SettingsMenu
import com.junianto.edcsekolah.util.SpaceItemDecoration

class SettingsFragment : Fragment() {

    lateinit var adapter: SettingsAdapter
    lateinit var rvMenuSettings: RecyclerView
    lateinit var rvMenuSettingsList: ArrayList<SettingsMenu>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvMenuSettings = view.findViewById(R.id.rv_menu_settings)
        rvMenuSettingsList = ArrayList()

        val layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = SettingsAdapter(rvMenuSettingsList, object : SettingsItemClickListener {
            override fun onSettingsItemClick(menuTitle: String) {
                when (menuTitle) {
                    "BLUETOOTH" -> {
                        findNavController().navigate(R.id.action_settingsFragment_to_bluetoothDevicesFragment)
                    }
                    "SETUP" -> {
                        findNavController().navigate(R.id.action_settingsFragment_to_setupFragment)
                    }
                }
            }
        })

        val spaceInPixels = resources.getDimensionPixelSize(R.dimen.item_margin)
        val itemDecoration = SpaceItemDecoration(spaceInPixels)
        rvMenuSettings.addItemDecoration(itemDecoration)

        rvMenuSettings.layoutManager = layoutManager
        rvMenuSettings.adapter = adapter

        rvMenuSettingsList.add(SettingsMenu(R.drawable.bluetooth_icon, "BLUETOOTH", "Perangkat bluetooth yang terhubung"))
        rvMenuSettingsList.add(SettingsMenu(R.drawable.setup_icon, "SETUP", "Setup aplikasi EDC"))

        adapter.notifyDataSetChanged()
    }
}
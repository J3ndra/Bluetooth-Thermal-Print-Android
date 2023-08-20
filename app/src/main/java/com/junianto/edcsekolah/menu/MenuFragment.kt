package com.junianto.edcsekolah.menu

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.junianto.edcsekolah.R
import com.junianto.edcsekolah.menu.settings.SettingsAdapter
import com.junianto.edcsekolah.menu.settings.SettingsItemClickListener
import com.junianto.edcsekolah.menu.settings.model.SettingsMenu
import com.junianto.edcsekolah.util.SpaceItemDecoration

class MenuFragment : Fragment() {

    lateinit var adapter: SettingsAdapter
    lateinit var rvMenuSettings: RecyclerView
    lateinit var rvMenuSettingsList: ArrayList<SettingsMenu>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvMenuSettings = view.findViewById(R.id.rv_menu)
        rvMenuSettingsList = ArrayList()

        val layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = SettingsAdapter(rvMenuSettingsList, object : SettingsItemClickListener {
            override fun onSettingsItemClick(menuTitle: String) {
                when (menuTitle) {
                    "SETTINGS" -> {
                        findNavController().navigate(R.id.action_menuFragment_to_settingsFragment)
                    }
                    "E-MONEY" -> {
                        findNavController().navigate(R.id.action_menuFragment_to_EMoneyEnterPriceFragment)
                    }
                    "SETTLEMENTS" -> {
                        findNavController().navigate(R.id.action_menuFragment_to_settlementPinFragment)
                    }
                    "REPRINT" -> {
                        findNavController().navigate(R.id.action_menuFragment_to_reprintFragment)
                    }
                    "VOID" -> {
                        findNavController().navigate(R.id.action_menuFragment_to_searchReceiptFragment)
                    }
                    "ABOUT" -> {
                        AlertDialog.Builder(requireContext())
                            .setTitle("About")
                            .setMessage("EDC Sekolah v1.0.0 by Junianto")
                            .setPositiveButton("OK") { dialog, _ ->
                                dialog.dismiss()
                            }
                            .show()
                    }
                }
            }
        })

        rvMenuSettings.layoutManager = layoutManager
        rvMenuSettings.adapter = adapter

        rvMenuSettingsList.add(SettingsMenu(R.drawable.sale_icon, "SALE", "Transaksi kartu atm"))
        rvMenuSettingsList.add(SettingsMenu(R.drawable.settlement_icon, "SETTLEMENTS", "Melihat semua transaksi"))
        rvMenuSettingsList.add(SettingsMenu(R.drawable.about_icon, "VOID", "Menghapus data transaksi"))
        rvMenuSettingsList.add(SettingsMenu(R.drawable.reprint_icon, "REPRINT", "Menampilkan ulang struk"))
        rvMenuSettingsList.add(SettingsMenu(R.drawable.qris_icon, "QRIS", "Quick Response Code Indonesia Standard"))
        rvMenuSettingsList.add(SettingsMenu(R.drawable.nfc_icon, "E-MONEY", "Transaksi kartu e-money"))
        rvMenuSettingsList.add(SettingsMenu(R.drawable.settings_icon, "SETTINGS", "Setting printer dan lainnya"))
        rvMenuSettingsList.add(SettingsMenu(R.drawable.about_icon, "ABOUT", "Tentang aplikasi"))

        adapter.notifyDataSetChanged()
    }
}
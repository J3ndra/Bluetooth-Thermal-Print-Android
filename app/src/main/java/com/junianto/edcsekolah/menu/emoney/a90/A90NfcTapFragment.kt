package com.junianto.edcsekolah.menu.emoney.a90

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.junianto.edcsekolah.R
import com.junianto.edcsekolah.a90.printer.A90ContactlessManager

class A90NfcTapFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        A90ContactlessManager.readContactlessCard()

        return inflater.inflate(R.layout.fragment_a90_nfc_tap, container, false)
    }
}
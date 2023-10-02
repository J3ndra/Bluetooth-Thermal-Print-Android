package com.junianto.edcsekolah.menu.qris

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.junianto.edcsekolah.AppViewModel
import com.junianto.edcsekolah.R

class QrisSuccessFragment : Fragment() {

    private val appViewModel: AppViewModel by viewModels()

    private lateinit var amount: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_qris_success, container, false)
    }
}
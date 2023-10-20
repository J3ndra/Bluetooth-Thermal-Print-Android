package com.junianto.edcsekolah.menu.sale

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.junianto.edcsekolah.AppViewModel
import com.junianto.edcsekolah.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SaleFragment : Fragment() {

    private val appViewModel: AppViewModel by viewModels()

    private lateinit var cvCash: CardView
    private lateinit var cvInsertCard: CardView
    private lateinit var cvMagneticCard: CardView

    private var isSdkInitialized = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_sale, container, false)

        cvCash = rootView.findViewById(R.id.cv_cash)
        cvInsertCard = rootView.findViewById(R.id.cv_insert_card)
        cvMagneticCard = rootView.findViewById(R.id.cv_magnetic_card)

        appViewModel.appSetup.observe(viewLifecycleOwner) {
            isSdkInitialized = it.is_sdk_initialized
        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cvCash.setOnClickListener {
            findNavController().navigate(R.id.action_saleFragment_to_cashEnterAmountFragment, Bundle().apply {
                putString("paymentType", "CASH")
            })
        }

        cvInsertCard.setOnClickListener {
            if (isSdkInitialized) {
                findNavController().navigate(R.id.action_saleFragment_to_cashEnterAmountFragment, Bundle().apply {
                    putString("paymentType", "ICCARD")
                })
            } else {
                Toast.makeText(requireContext(), R.string.perangkat_tidak_didukung, Toast.LENGTH_SHORT).show()
            }
        }

        cvMagneticCard.setOnClickListener {
            if (isSdkInitialized) {
                findNavController().navigate(R.id.action_saleFragment_to_cashEnterAmountFragment, Bundle().apply {
                    putString("paymentType", "MAGCARD")
                })
            } else {
                Toast.makeText(requireContext(), R.string.perangkat_tidak_didukung, Toast.LENGTH_SHORT).show()
            }
        }

//        cvCashless.setOnClickListener {
//            AlertDialog.Builder(requireContext())
//                .setTitle("Coming Soon")
//                .setMessage("Fitur ini akan segera hadir")
//                .setPositiveButton("OK") { dialog, _ ->
//                    dialog.dismiss()
//                }
//                .show()
//        }
    }
}
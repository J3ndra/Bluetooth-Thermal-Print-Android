package com.junianto.edcsekolah.menu.sale

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import com.junianto.edcsekolah.R

class SaleFragment : Fragment() {

    private lateinit var cvCash: CardView
    private lateinit var cvInsertCard: CardView
    private lateinit var cvMagneticCard: CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_sale, container, false)

        cvCash = rootView.findViewById(R.id.cv_cash)
        cvInsertCard = rootView.findViewById(R.id.cv_insert_card)
        cvMagneticCard = rootView.findViewById(R.id.cv_magnetic_card)

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
            findNavController().navigate(R.id.action_saleFragment_to_cashEnterAmountFragment, Bundle().apply {
                putString("paymentType", "ICCARD")
            })
        }

        cvMagneticCard.setOnClickListener {
            findNavController().navigate(R.id.action_saleFragment_to_cashEnterAmountFragment, Bundle().apply {
                putString("paymentType", "MAGCARD")
            })
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
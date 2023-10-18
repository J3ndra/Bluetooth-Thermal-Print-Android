package com.junianto.edcsekolah.menu.sale.magnetic

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.junianto.edcsekolah.R
import com.junianto.edcsekolah.a90.A90MagneticManager
import com.junianto.edcsekolah.util.formatAmount
import com.vanstone.trans.api.MagCardApi
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MagneticCardFragment : Fragment() {

    private lateinit var progressBar: ProgressBar
    private lateinit var progressText: TextView
    private lateinit var tvNfcStatus: TextView
    private lateinit var tvNfcAmount: TextView

    private var timer = 0;

    private val handler = Handler(Looper.getMainLooper())

    private lateinit var amount: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_magnetic_card, container, false)

        A90MagneticManager.openMagneticCard()

        val bundle = arguments
        if (bundle != null) {
            amount = bundle.getString("amount", "") ?: ""
        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize view
        progressBar = view.findViewById(R.id.progress_bar)
        progressText = view.findViewById(R.id.progress_text)

        tvNfcStatus = view.findViewById(R.id.tv_nfc_status)
        tvNfcStatus.text = getString(R.string.silahkan_tempel_kartu_nfc_anda_pada_mesin)

        tvNfcAmount = view.findViewById(R.id.tv_nfc_tap_amount)
        tvNfcAmount.text = buildString {
            append("Total yang harus dibayar adalah ")
            append(formatAmount(amount))
        }

        // Start update progress bar
        handler.postDelayed(updateRunnable, 1000)

        // Schedule the PiccCheck_Api to run periodically
        handler.postDelayed(piccCheckRunnable, 0)
    }

    private val updateRunnable = object : Runnable {
        override fun run() {
            if (timer <= 20) {
                progressText.text = timer.toString()
                progressBar.progress = timer * 5
                timer++
                handler.postDelayed(this, 1000)
            } else {
                handler.removeCallbacks(this)
                findNavController().navigate(R.id.action_a90NfcTapFragment_to_menuFragment)
            }
        }
    }

    private val piccCheckRunnable = object : Runnable {
        override fun run() {
            val aret = MagCardApi.MagSwiped_Api()
            Timber.i("PiccCheck_Api: $aret")

            if (aret == 0) {
                activity?.runOnUiThread {
                    Toast.makeText(requireContext(), "Kartu terdeteksi", Toast.LENGTH_SHORT).show()

                    findNavController().navigate(R.id.action_icCardFragment_to_cashEnterPinFragment, Bundle().apply {
                        putString("cardId", "1234567890")
                        putString("amount", amount)
                        putString("paymentType", "MAGCARD")
                    })
                }
            }

            // Repeat the task every 1000 milliseconds (1 second)
            handler.postDelayed(this, 1000)
        }
    }

    override fun onStart() {
        super.onStart()

        timer = 0
    }

    override fun onDestroy() {
        super.onDestroy()

        A90MagneticManager.closeMagneticCard()
        handler.removeCallbacksAndMessages(null)
    }

    override fun onPause() {
        super.onPause()

        A90MagneticManager.closeMagneticCard()
        handler.removeCallbacksAndMessages(null)
    }
}
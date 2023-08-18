package com.junianto.edcsekolah.menu.emoney

import android.app.AlertDialog
import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.junianto.edcsekolah.R
import com.junianto.edcsekolah.util.ByteArrayToHexString
import com.junianto.edcsekolah.util.formatAmount
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.NumberFormat
import javax.inject.Inject

class NfcTapFragment : Fragment(), NfcAdapter.ReaderCallback {
    private lateinit var progressBar: ProgressBar
    private lateinit var progressText: TextView
    private lateinit var tvNfcStatus: TextView
    private lateinit var tvNfcAmount: TextView

    @Inject
    lateinit var nfcAdapter: NfcAdapter

    private var i = 0

    private val handler = Handler(Looper.getMainLooper())

    private lateinit var amount: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        nfcAdapter = NfcAdapter.getDefaultAdapter(requireContext())

        // Retrieve the bundle from arguments
        val bundle = arguments
        if (bundle != null) {
            amount = bundle.getString("amount", "") ?: ""
        }

        return inflater.inflate(R.layout.fragment_nfc_tap, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize view
        progressBar = view.findViewById(R.id.progress_bar)
        progressText = view.findViewById(R.id.progress_text)
        tvNfcStatus = view.findViewById(R.id.tv_nfc_status)
        tvNfcAmount = view.findViewById(R.id.tv_nfc_tap_amount)

        // Set amount
        tvNfcAmount.text = buildString {
            append("Total yang harus dibayar: ")
            append(formatAmount(amount))
        }

        // Check if NFC is available on the device
        val nfcAdapter: NfcAdapter? = NfcAdapter.getDefaultAdapter(requireContext())
        if (nfcAdapter == null) {
            tvNfcStatus.text = getString(R.string.nfc_not_available_on_this_device)
            AlertDialog.Builder(requireContext())
                .setTitle("NFC Not Available")
                .setMessage("NFC is not available on this device")
                .setPositiveButton("OK") { _, _ ->
                    findNavController().navigate(R.id.action_nfcTapFragment_to_menuFragment)
                }
                .setOnDismissListener {
                    findNavController().navigate(R.id.action_nfcTapFragment_to_menuFragment)
                }
                .setCancelable(false)
                .show()
        } else {
            // Check if NFC is enabled
            if (!nfcAdapter.isEnabled) {
                tvNfcStatus.text = getString(R.string.nfc_is_disabled_please_enable_nfc_in_settings)
            } else {
                // Check NFC permission
                if (nfcAdapter.isEnabled && !hasNfcPermission()) {
                    tvNfcStatus.text = getString(R.string.nfc_permission_not_granted_grant_permission_in_settings)
                } else {
                    // NFC is enabled and permission is granted
                    nfcAdapter.enableReaderMode(requireActivity(), this, NfcAdapter.FLAG_READER_NFC_A or NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK, null)
                    tvNfcStatus.text = getString(R.string.nfc_is_enabled_and_permission_is_granted)
                    // Start your NFC logic here
                    handler.postDelayed(updateRunnable, 1000)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()

        i = 0
    }

    override fun onDestroy() {
        super.onDestroy()

        nfcAdapter.disableReaderMode(requireActivity())
        handler.removeCallbacksAndMessages(null)
    }

    override fun onPause() {
        super.onPause()

        nfcAdapter.disableReaderMode(requireActivity())
        handler.removeCallbacksAndMessages(null)
    }

    // Define the Runnable for updating the progress
    private val updateRunnable = object : Runnable {
        override fun run() {
            if (i <= 20) {
                progressText.text = i.toString()
                progressBar.progress = i * 5
                i++
                handler.postDelayed(this, 1000)
            } else {
                nfcAdapter.disableReaderMode(requireActivity())
                handler.removeCallbacks(this)
                findNavController().navigate(R.id.action_nfcTapFragment_to_menuFragment)
            }
        }
    }

    private fun hasNfcPermission(): Boolean {
        val nfcAdapter: NfcAdapter? = NfcAdapter.getDefaultAdapter(requireContext())
        return nfcAdapter?.isEnabled == true
    }

    private fun openNfcSettings() {
        val settingsIntent = Intent(Settings.ACTION_NFC_SETTINGS)
        startActivity(settingsIntent)
    }

    override fun onTagDiscovered(tag: Tag?) {
        activity?.runOnUiThread {
            if (tag != null) {
                val tagId = ByteArrayToHexString(tag.id)
                Toast.makeText(requireContext(), "Tag Discovered: $tagId", Toast.LENGTH_SHORT).show()

                findNavController().navigate(R.id.action_nfcTapFragment_to_EMoneyPinFragment, Bundle().apply {
                    putString("cardId", tagId)
                    putString("amount", amount)
                })
            }
        }
    }

}
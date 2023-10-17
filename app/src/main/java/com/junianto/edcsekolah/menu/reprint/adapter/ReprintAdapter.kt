package com.junianto.edcsekolah.menu.reprint.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.junianto.edcsekolah.R
import com.junianto.edcsekolah.data.model.Receipt
import com.junianto.edcsekolah.util.formatAmount
import timber.log.Timber
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.Locale

interface ReprintButtonClickListener {
    fun onReprintButtonClick(receipt: Receipt)
}

class ReprintAdapter : RecyclerView.Adapter<ReprintAdapter.ReprintViewHolder>() {
    private var receipts: List<Receipt> = emptyList()
    private var reprintButtonClickListener: ReprintButtonClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReprintViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_reprint, parent, false)
        return ReprintViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return receipts.size
    }

    override fun onBindViewHolder(holder: ReprintViewHolder, position: Int) {
        val transaction = receipts[position]
        holder.bind(transaction)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setReceipts(transactions: List<Receipt>) {
        this.receipts = transactions
        notifyDataSetChanged()
    }

    inner class ReprintViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val transactionIdTextView: TextView = itemView.findViewById(R.id.tv_id)
        private val priceTextView: TextView = itemView.findViewById(R.id.tv_amount)
        private val transactionDateTextView: TextView = itemView.findViewById(R.id.tv_date)
        private val paymentTypeTextView: TextView = itemView.findViewById(R.id.tv_payment_type)
        private val reprintButton: TextView = itemView.findViewById(R.id.btn_reprint)

        @SuppressLint("SetTextI18n")
        fun bind(receipt: Receipt) {
            transactionIdTextView.text = "No. ${receipt.id}"
            priceTextView.text = formatAmount(receipt.amount.toString())
            transactionDateTextView.text = receipt.date

            val paymentType = when (receipt.paymentType) {
                1 -> "Transaksi Tunai"
                2 -> "Transaksi NFC"
                3 -> "Transaksi QRIS"
                4 -> "Transaksi IC Card"
                5 -> "Transaksi Magnetic"
                else -> "Transaksi Tunai"
            }

            paymentTypeTextView.text = paymentType

            reprintButton.setOnClickListener {
                reprintButtonClickListener?.onReprintButtonClick(receipt)
            }
        }
    }

    fun reprintButtonClickListener(reprintButtonClickListener: ReprintButtonClickListener) {
        this.reprintButtonClickListener = reprintButtonClickListener
    }
}
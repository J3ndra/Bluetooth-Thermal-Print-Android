package com.junianto.edcsekolah.menu.settlement.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.junianto.edcsekolah.R
import com.junianto.edcsekolah.data.model.Receipt
import com.junianto.edcsekolah.util.formatAmount

class SettlementAdapter : RecyclerView.Adapter<SettlementAdapter.ViewHolder>() {
    private var receipt: List<Receipt> = emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SettlementAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_receipt, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SettlementAdapter.ViewHolder, position: Int) {
        val receipt = receipt[position]
        holder.bind(receipt)
    }

    override fun getItemCount(): Int {
        return receipt.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setReceipt(receipt: List<Receipt>) {
        this.receipt = receipt
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvReceiptId = itemView.findViewById<TextView>(R.id.tv_receipt_id)
        private val tvReceiptDate = itemView.findViewById<TextView>(R.id.tv_receipt_date)
        private val tvReceiptAmount = itemView.findViewById<TextView>(R.id.tv_receipt_amount)
        private val tvReceiptStatus = itemView.findViewById<TextView>(R.id.tv_receipt_status)

        @SuppressLint("SetTextI18n")
        fun bind(receipt: Receipt) {
            var typeText = ""
            when (receipt.paymentType) {
                1 -> typeText = "CASH"
                2 -> typeText = "NFC"
                3 -> typeText = "QR"
                4 -> typeText = "IC"
                5 -> typeText = "MAGNETIC"
            }

            tvReceiptId.text = "TRACE NO : ${receipt.id}"
            tvReceiptStatus.text = "STATUS : $typeText"
            tvReceiptAmount.text = formatAmount(receipt.amount.toString())
            tvReceiptDate.text = receipt.date
        }

    }
}
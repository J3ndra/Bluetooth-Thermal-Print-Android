package com.junianto.edcsekolah.menu.settings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.junianto.edcsekolah.R
import com.junianto.edcsekolah.menu.settings.model.SettingsMenu

interface SettingsItemClickListener {
    fun onSettingsItemClick(menuTitle: String)
}

class SettingsAdapter(
    private val buttons: List<SettingsMenu>,
    private val itemClickListener: SettingsItemClickListener
) : RecyclerView.Adapter<SettingsAdapter.ButtonViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)
        return ButtonViewHolder(view)
    }

    override fun getItemCount(): Int {
        return buttons.size
    }

    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
        val button = buttons[position]
        holder.bind(button)
    }

    inner class ButtonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageIcon: ImageView = itemView.findViewById(R.id.iv_icon)
        private val textTitle: TextView = itemView.findViewById(R.id.tv_menu_settings_title)
        private val textSubtitle: TextView = itemView.findViewById(R.id.tv_menu_settings_description)

        fun bind(button: SettingsMenu) {
            imageIcon.setImageResource(button.icon)
            textTitle.text = button.title
            textSubtitle.text = button.desc

            itemView.setOnClickListener{
                itemClickListener.onSettingsItemClick(button.title)
            }
        }
    }
}
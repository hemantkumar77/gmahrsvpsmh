package com.app.abasiccommondiary.ui.pot

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.app.abasiccommondiary.R
import com.app.abasiccommondiary.databinding.FragmentPotBinding

internal class PotAdapter(private var potsList: List<PotModel>) :
    RecyclerView.Adapter<PotAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var binding: FragmentPotBinding? = null

        private lateinit var textViewMonthDate: TextView

        var pot_name: TextView = view.findViewById(R.id.pot_time)
        val pot_type: TextView = view.findViewById(R.id.pot_no)
        var pot_amount: TextView = view.findViewById(R.id.pot_name)
        var pot_source: TextView = view.findViewById(R.id.pot_source)
        var pot_via: TextView = view.findViewById(R.id.pot_via)
        var pot_destination: TextView = view.findViewById(R.id.pot_destination)
    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_row_pot, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val pot = potsList[position]
        //holder.pot_time.text = pot.getPottime()
        //holder.pot_no.text = pot.getPotno()
        holder.pot_name.text = pot.getPotname()
        holder.pot_source.text = pot.getPottype()
        holder.pot_via.text = pot.getPotamount()
        //holder.pot_destination.text = pot.getPotdestination()
    }
    override fun getItemCount(): Int {
        return potsList.size
    }
}
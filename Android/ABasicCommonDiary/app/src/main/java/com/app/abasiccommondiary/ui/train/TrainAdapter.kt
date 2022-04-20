package com.app.abasiccommondiary.ui.train

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.app.abasiccommondiary.R
import com.app.abasiccommondiary.databinding.FragmentTrainBinding

internal class TrainAdapter(private var trainsList: List<TrainModel>) :
    RecyclerView.Adapter<TrainAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var binding: FragmentTrainBinding? = null

        private lateinit var textViewMonthDate: TextView

        var train_time: TextView = view.findViewById(R.id.train_time)
        val train_no: TextView = view.findViewById(R.id.train_no)
        var train_name: TextView = view.findViewById(R.id.train_name)
        var train_source: TextView = view.findViewById(R.id.train_source)
        var train_via: TextView = view.findViewById(R.id.train_via)
        var train_destination: TextView = view.findViewById(R.id.train_destination)
    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_row_train, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val train = trainsList[position]
        holder.train_time.text = train.getTraintime()
        holder.train_no.text = train.getTrainno()
        holder.train_name.text = train.getTrainname()
        holder.train_source.text = train.getTrainsource()
        holder.train_via.text = train.getTrainvia()
        holder.train_destination.text = train.getTraindestination()
    }
    override fun getItemCount(): Int {
        return trainsList.size
    }
}
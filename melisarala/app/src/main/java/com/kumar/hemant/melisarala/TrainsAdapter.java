package com.kumar.hemant.melisarala;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

public class TrainsAdapter extends RecyclerView.Adapter<TrainsAdapter.MyViewHolder> {

    private List<Train> trainsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvUpStart, tvUpMiddle, tvUpDestination, tvDownStart, tvDownMiddle, tvDownDestination;

        public MyViewHolder(View view) {
            super(view);
            tvUpStart = (TextView) view.findViewById(R.id.upStart);
            tvUpMiddle = (TextView) view.findViewById(R.id.upMiddle);
            tvUpDestination = (TextView) view.findViewById(R.id.upDestination);
            tvDownStart = (TextView) view.findViewById(R.id.downStart);
            tvDownMiddle = (TextView) view.findViewById(R.id.downMiddle);
            tvDownDestination = (TextView) view.findViewById(R.id.downDestination);
        }
    }

    public TrainsAdapter(List<Train> trainsList) {
        this.trainsList = trainsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.train_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Train train = trainsList.get(position);
        holder.tvUpStart.setText(train.getUpStart());
        holder.tvUpMiddle.setText(train.getUpMiddle());
        holder.tvUpDestination.setText(train.getUpDestination());
        holder.tvDownStart.setText(train.getDownStart());
        holder.tvDownMiddle.setText(train.getDownMiddle());
        holder.tvDownDestination.setText(train.getDownDestination());
    }

    @Override
    public int getItemCount() {
        return trainsList.size();
    }
}
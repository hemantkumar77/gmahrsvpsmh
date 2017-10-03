package com.kumar.hemant.travelguide.Train;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kumar.hemant.travelguide.R;

import java.util.List;

public class TrainsAdapter extends RecyclerView.Adapter<TrainsAdapter.MyViewHolder> {

    private List<Train> trainsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTrainNo, tvTrainName, tvArrival, tvFromStation, tvFromStationTime, tvToStation, tvToStationTime;

        public MyViewHolder(View view) {
            super(view);
            tvTrainNo = (TextView) view.findViewById(R.id.tv_train_no);
            tvTrainName = (TextView) view.findViewById(R.id.tv_train_name);
            tvFromStation = (TextView) view.findViewById(R.id.tv_from_station);
            tvFromStationTime = (TextView) view.findViewById(R.id.tv_from_station_time);
            tvToStation = (TextView) view.findViewById(R.id.tv_to_station);
            tvToStationTime = (TextView) view.findViewById(R.id.tv_to_station_time);
            tvArrival = (TextView) view.findViewById(R.id.tv_arrival);
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
        holder.tvTrainNo.setText(train.getTrainNo());
        holder.tvTrainName.setText(train.getTrainName());
        holder.tvFromStation.setText(train.getFromStation());
        holder.tvFromStationTime.setText(train.getFromStationTime());
        holder.tvToStation.setText(train.getToStation());
        holder.tvToStationTime.setText(train.getToStationTime());
        holder.tvArrival.setText(train.getArrival());
    }

    @Override
    public int getItemCount() {
        return trainsList.size();
    }
}
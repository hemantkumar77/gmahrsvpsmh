package com.kumar.hemant.travelguide.Train;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class TrainController
{
    private TrainModel model;
    private List<String> trains;
    private List<String> stations;
    private List<String> trainstations;
    private List<String> traintimes;
    public TrainController(Context app_context)
    {
        trains = new ArrayList<String>();
        stations = new ArrayList<String>();
        trainstations = new ArrayList<String>();
        traintimes = new ArrayList<String>();
        model = new TrainModel(app_context);
    }
    public void addTrain(final String train_no, final String train_name, final String source, final String destination, final String days_of_week)
    {
        final ContentValues data = new ContentValues();
        data.put("train_no", train_no);
        data.put("train_name", train_name);
        data.put("source", source);
        data.put("destination", destination);
        data.put("days_of_week", days_of_week);
        model.addTrain(data);
    }
    public void addStation(final String station_code, final String station_name)
    {
        final ContentValues data_station = new ContentValues();
        data_station.put("station_code", station_code);
        data_station.put("station_name", station_name);
        model.addStation(data_station);
    }
    public void addTrainStation(final String train_no, final String station_code, final String time_arrival, final String time_departure)
    {
        final ContentValues data_train_station = new ContentValues();
        data_train_station.put("train_no", train_no);
        data_train_station.put("station_code", station_code);
        data_train_station.put("time_arrival", time_arrival);
        data_train_station.put("time_departure", time_departure);
        model.addTrainStation(data_train_station);
    }
    public void updateTrainTime(final String train_no, final String station_code, final String time_arrival, final String time_departure)
    {
        final ContentValues data_train_station = new ContentValues();
        data_train_station.put("train_no", train_no);
        data_train_station.put("station_code", station_code);
        data_train_station.put("time_arrival", time_arrival);
        data_train_station.put("time_departure", time_departure);
        model.updateTrainTime(train_no, station_code, time_arrival, time_departure);
    }
    public void deleteTrain(final String train_no) {
        Log.v("DDDDDDDDATE","...146: "+train_no.substring(0,3));
        model.deleteTrain("train_no='" + train_no.substring(0,5) + "'");
    }
    public void deleteStation(final String station_code) {
        Log.v("DDDDDDDDATE","...146: "+ station_code.substring(0,3));
        model.deleteStation("station_code='" + station_code.substring(0,3) + "'");
    }
    public void deleteTrainStation(final String train_code) {
        Log.v("DDDDDDDDATE","...146: "+ train_code.substring(0,3));
        model.deleteTrainStation(train_code);
    }
/*
    public void deleteTask(final long id) { model.deleteTask("id='" + id + "'");
    }
*/
    public void deleteAllTask() { model.deleteTrain(null);
    }
    public void deleteAllStation() { model.deleteStation(null);
    }
    public void deleteAllTrainStation() { model.deleteTrainStation(null);
    }
    public List<String> getTrains()
    {
        Cursor c = model.loadAllTrains();
        trains.clear();
        if (c != null) { c.moveToFirst();
            while (c.isAfterLast() == false)
            {
                trains.add(c.getString(0)+"-"+c.getString(1)+"-"+c.getString(2)+"-"+c.getString(3)+"-"+c.getString(4));
                Log.v("DDDDDDDDATE","...144: "+c.getString(0));
                c.moveToNext();
            }
            c.close();
        } return trains;
    }
    public List<String> getStations()
    {
        Cursor c = model.loadAllStations();
        stations.clear();
        if (c != null) { c.moveToFirst();
            while (c.isAfterLast() == false)
            {
                stations.add(c.getString(0)+"-"+c.getString(1));
                Log.v("DDDDDDDDATE","...144: "+c.getString(0));
                c.moveToNext();
            }
            c.close();
        } return stations;
    }

    public List<String> getOneStations(String station_code)
    {
        Cursor c = model.loadOneStations(station_code);
        stations.clear();
        if (c != null) { c.moveToFirst();
            while (c.isAfterLast() == false)
            {
                stations.add(c.getString(0)+"-"+c.getString(1));
                Log.v("DDDDDDDDATE","...144: "+c.getString(0));
                c.moveToNext();
            }
            c.close();
        } return stations;
    }

    public List<String> getTrainStations()
    {
        Cursor c = model.loadAllTrainStations();
        trainstations.clear();
        if (c != null) { c.moveToFirst();
            while (c.isAfterLast() == false)
            {
                trainstations.add(c.getString(0)+"-"+c.getString(1)+"-"+c.getString(2)+"-"+c.getString(3));
                Log.v("DDDDDDDDATE","...156Hem: "+c.getString(0));
                c.moveToNext();
            }
            c.close();
        } return trainstations;
    }
    public List<String> getTrainStations(String trainNo)
    {
        Cursor c = model.loadTrainStations(trainNo);
        trainstations.clear();
        if (c != null) { c.moveToFirst();
            while (c.isAfterLast() == false)
            {
                trainstations.add(c.getString(0)+"-"+c.getString(1)+"-"+c.getString(2)+"-"+c.getString(3));
                Log.v("DDDDDDDDATE","...156Hem: "+c.getString(0));
                c.moveToNext();
            }
            c.close();
        } return trainstations;
    }

    public List<String> getTrainList()
    {
        Cursor c = model.loadAllTrains();
        String TrainNo[];
        trains.clear();
        if (c != null) { c.moveToFirst();
            while (c.isAfterLast() == false)
            {
                trains.add(c.getString(0)+"-"+c.getString(1)+"-"+c.getString(2)+"-"+c.getString(3));
                Log.v("DDDDDDDDATE","...144: "+c.getString(0));
                c.moveToNext();
            }
            c.close();
        } return trains;
    }
    public List<String> getStationList()
    {
        Cursor c = model.loadAllStations();
        stations.clear();
        if (c != null) { c.moveToFirst();
            while (c.isAfterLast() == false)
            {
                stations.add(c.getString(0)+"-"+c.getString(1));
                c.moveToNext();
            }
            c.close();
        } return stations;
    }
    public List<String> getTrainTimes(String station_selected)
    {
        Cursor c = model.loadAllTrainTime(station_selected);
        traintimes.clear();
        //traintimes.add("Arrival Time - Departure Time");
        if (c != null) { c.moveToFirst();
            while (c.isAfterLast() == false)
            {
                traintimes.add(c.getString(0)+"-"+c.getString(1)+"-"+c.getString(2)+"-"+c.getString(3)+"-"+c.getString(4)+"-"+c.getString(5)+"-"+c.getString(6)+"-"+c.getString(7)+"-"+c.getString(8)+"-"+c.getString(9)+"-"+c.getString(10));
                c.moveToNext();
            }
            c.close();
        } return traintimes;
    }
}
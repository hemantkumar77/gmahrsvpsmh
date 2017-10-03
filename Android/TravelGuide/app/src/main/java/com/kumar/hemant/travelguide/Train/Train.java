package com.kumar.hemant.travelguide.Train;
public class Train {
    private String TrainNo, TrainName, Arrival, FromStation, FromStationTime, ToStation, ToStationTime;

    public Train() {
    }

    public Train(String TrainNo, String TrainName, String FromStation, String FromStationTime, String ToStation, String ToStationTime, String Arrival) {
        this.TrainNo = TrainNo;
        this.TrainName = TrainName;
        this.Arrival = Arrival;
        this.FromStation = FromStation;
        this.FromStationTime = FromStationTime;
        this.ToStation = ToStation;
        this.ToStationTime = ToStationTime;
    }
    public String getTrainNo() {
        return TrainNo;
    }
    public void setTrainNo(String TrainNo) {
        this.TrainNo = TrainNo;
    }
    public String getTrainName() { return TrainName;}
    public void setTrainName(String TrainName) {
        this.TrainName = TrainName;
    }
    public String getArrival() {
        return Arrival;
    }
    public void setArrival(String Arrival) { this.Arrival = Arrival;}
    public String getFromStation() {
        return FromStation;
    }
    public void setFromStation(String FromStation) {this.FromStation = FromStation;}
    public String getFromStationTime() {
        return FromStationTime;
    }
    public void setFromStationTime(String FromStationTime) { this.FromStationTime = FromStationTime;}
    public String getToStation() {
        return ToStation;
    }
    public void setToStation(String ToStation) {
        this.ToStation = ToStation;
    }
    public String getToStationTime() {
        return ToStationTime;
    }
    public void setToStationTime(String ToStationTime) {
        this.ToStationTime = ToStationTime;
    }
}

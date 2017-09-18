package com.kumar.hemant.melisarala.Train;
public class TrainMVC {
    private String upStart, upMiddle, upDestination, downStart, downMiddle, downDestination;

    public TrainMVC() {
    }

    public TrainMVC(String upStart, String upMiddle, String upDestination, String downStart, String downMiddle, String downDestination) {
        this.upStart = upStart;
        this.upMiddle = upMiddle;
        this.upDestination = upDestination;
        this.downStart = downStart;
        this.downMiddle = downMiddle;
        this.downDestination = downDestination;
    }
    public String getUpStart() {
        return upStart;
    }
    public void setUpStart(String upStart) {
        this.upStart = upStart;
    }
    public String getUpMiddle() {
        return upMiddle;
    }
    public void setUpMiddle(String upMiddle) {
        this.upMiddle = upMiddle;
    }
    public String getUpDestination() {
        return upDestination;
    }
    public void setUpDestination(String upDestination) { this.upDestination = upDestination;}
    public String getDownStart() {
        return downStart;
    }
    public void setDownStart(String downStart) {this.downStart = downStart;}
    public String getDownMiddle() {
        return downMiddle;
    }
    public void setDownMiddle(String downMiddle) {
        this.upStart = downMiddle;
    }
    public String getDownDestination() {
        return downDestination;
    }
    public void setDownDestination(String downDestination) {
        this.upStart = downDestination;
    }
}

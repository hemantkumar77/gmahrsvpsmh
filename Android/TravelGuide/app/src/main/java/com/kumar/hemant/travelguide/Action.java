package com.kumar.hemant.travelguide;
public class Action {
    private long id;
    private String action;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return action;
    }
}
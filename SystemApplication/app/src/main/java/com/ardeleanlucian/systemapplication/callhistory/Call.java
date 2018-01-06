package com.ardeleanlucian.systemapplication.callhistory;

/**
 * Created by Ardelean Lucian on 1/4/2018.
 */

public class Call {

    private String number;
    private int type;
    private int date;
    private int duration;
    private String callDirection;

    /**
     * Constructor method
     */
    public Call() {

    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getCallDirection() {
        return callDirection;
    }

    public void setCallDirection(String callDirection) {
        this.callDirection = callDirection;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}

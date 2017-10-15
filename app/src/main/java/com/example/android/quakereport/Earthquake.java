package com.example.android.quakereport;

/**
 * Created by sapayth on 10/13/17.
 */

public class Earthquake {
    private String quakMeasure;
    private String location;
    private long unixtime;

    public Earthquake(String quakMeasure, String location, long unixtime) {
        this.quakMeasure = quakMeasure;
        this.location = location;
        this.unixtime = unixtime;
    }

    public String getQuakMeasure() {
        return quakMeasure;
    }

    public String getLocation() {
        return location;
    }

    public long getUnixtime() { return unixtime; }



}

package com.example.android.quakereport;

import java.util.Date;

/**
 * Created by up22 on 28/06/2017.
 */

class Earthquake {

    //Store Magnitude, Location and Date
    private Double magnitude;
    private String location;
    private Date eventDate;


    Earthquake(Double magnitude, String location, Date event_date) {
        this.magnitude = magnitude;
        this.location = location;
        this.eventDate = event_date;
    }

    Double getMagnitude() {
        return magnitude;
    }

    String getLocation() {
        return location;
    }

    Date getEventDate() { return eventDate; }
}

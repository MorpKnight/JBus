package com.GiovanChristoffelSihombingJBusRS.controller.model;

/**
 * The BaseStation class represents a base station with a station name, city, and address.
 */
public class BaseStation {
    public String stationName, city, address;

    public BaseStation(String stationName, String city, String address){
        this.stationName = stationName;
        this.city = city;
        this.address = address;
    }
}

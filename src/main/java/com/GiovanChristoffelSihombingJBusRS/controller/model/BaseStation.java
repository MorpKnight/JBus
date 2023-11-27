package com.GiovanChristoffelSihombingJBusRS.controller.model;

public class BaseStation {
    public String stationName, city, address;

    public BaseStation(String stationName, String city, String address){
        this.stationName = stationName;
        this.city = city;
        this.address = address;
    }
}

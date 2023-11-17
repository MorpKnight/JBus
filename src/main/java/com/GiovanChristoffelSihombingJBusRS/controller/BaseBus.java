package com.GiovanChristoffelSihombingJBusRS.controller;

import com.GiovanChristoffelSihombingJBusRS.BusType;
import com.GiovanChristoffelSihombingJBusRS.Facility;

import java.util.List;

public class BaseBus {
    public String name, time;
    public int accountId, capacity, price, stationDepartureId, stationArrivalId, busId;
    public List<Facility> facility;
    public BusType busType;

    public BaseBus(String name, String time, int accountId, int capacity, int price, int stationDepartureId, int stationArrivalId, List<Facility> facility, BusType busType) {
        this.name = name;
        this.time = time;
        this.accountId = accountId;
        this.capacity = capacity;
        this.price = price;
        this.stationDepartureId = stationDepartureId;
        this.stationArrivalId = stationArrivalId;
        this.facility = facility;
        this.busType = busType;
    }
}

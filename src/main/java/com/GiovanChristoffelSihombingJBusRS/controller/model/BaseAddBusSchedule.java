package com.GiovanChristoffelSihombingJBusRS.controller.model;

/**
 * The BaseAddBusSchedule class represents a bus schedule entry with a time and bus ID.
 */
public class BaseAddBusSchedule {
    public String time;
    public int busId;

    public BaseAddBusSchedule(String time, int busId) {
        this.time = time;
        this.busId = busId;
    }
}

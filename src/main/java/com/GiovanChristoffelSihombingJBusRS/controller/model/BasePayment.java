package com.GiovanChristoffelSihombingJBusRS.controller.model;

import java.util.List;

/**
 * The BasePayment class represents a payment made by a buyer to rent a bus with specified seats and
 * departure date.
 */
public class BasePayment {
    public int buyerId, renterId, busId;
    public List<String> busSeats;
    public String departureDate;

    public BasePayment(int buyerId, int renterId, int busId, List<String> busSeats, String departureDate){
        this.busId = buyerId;
        this.renterId = renterId;
        this.busId = busId;
        this.busSeats = busSeats;
        this.departureDate = departureDate;
    }
}

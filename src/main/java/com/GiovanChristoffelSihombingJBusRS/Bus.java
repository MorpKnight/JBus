package com.GiovanChristoffelSihombingJBusRS;

import com.GiovanChristoffelSihombingJBusRS.dbjson.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
// import java.text.SimpleDateFormat;

/**
 * The Bus class represents a bus with various properties such as capacity, facilities, name, price,
 * departure and arrival stations, bus type, schedules, and account ID.
 */
public class Bus extends Serializable {
    public int capacity;
    public List<Facility> facilities;
    public String name;
    public Price price;
    public Station departure, arrival;
    public BusType busType;
    public List<Schedule> schedules;
    public int accountId;

    public Bus(String name, List<Facility> facilities, Price price, int capacity, BusType busType,
            Station departure, Station arrival, int accountId) {
        super();
        this.accountId = accountId;
        this.name = name;
        this.capacity = capacity;
        this.facilities = facilities;
        this.price = price;
        this.departure = departure;
        this.arrival = arrival;
        this.busType = busType;
        this.schedules = new ArrayList<Schedule>();
    }

    /**
     * The toString() function returns a string representation of a Bus object, including its id, name,
     * capacity, facilities, price, departure and arrival cities, and bus type.
     * 
     * @return The toString() method is returning a string representation of a bus object. It includes
     * information such as the bus id, name, capacity, facilities, price, departure city, arrival city,
     * and bus type.
     */
    public String toString() {
        return ("Bus Id: " + this.id + "\nname: " + this.name + "\ncapacity: " + this.capacity + "\nfacility: "
                + this.facilities + "\n" + this.price + "\ndeparture: "
                + this.departure.city + "\narrival: " + this.arrival.city + "\nbusType: " + this.busType + "\n");
    }

    /**
     * The function `addSchedule` adds a new schedule to a list of schedules, checking for duplicates
     * before adding.
     * 
     * @param calendar The parameter "calendar" is of type "Timestamp" and represents the departure
     * schedule that needs to be added to the existing schedules.
     */
    public void addSchedule(Timestamp calendar){
        try {
            for(Schedule existSchedule: this.schedules){
                if(existSchedule.departureSchedule.equals(calendar)){
                    System.out.println("Jadwal duplikat");
                    return;
                }
            }
            this.schedules.add(new Schedule(calendar, this.capacity));
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}

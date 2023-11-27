package com.GiovanChristoffelSihombingJBusRS;

import com.GiovanChristoffelSihombingJBusRS.dbjson.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
// import java.text.SimpleDateFormat;

/**
 * Write a description of class Bus here.
 *
 * @author (your name)
 * @version (a version number or a date)
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
     * Fungsi toString() mengembalikan string yang merepresentasikan objek bus.
     * 
     * @return toString() methode mengembalikan string yang merepresentasikan objek
     *         bus, termasuk
     *         id bus, nama, kapasitas, fasilitas, harga, kota, stasiun
     *         keberangkatan, stasiun kedatangan, dan tipe bus.
     */
    public String toString() {
        return ("Bus Id: " + this.id + "\nname: " + this.name + "\ncapacity: " + this.capacity + "\nfacility: "
                + this.facilities + "\n" + this.price + "\ndeparture: "
                + this.departure.city + "\narrival: " + this.arrival.city + "\nbusType: " + this.busType + "\n");
    }

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
//        this.schedules.add(new Schedule(calendar, this.capacity));
    }
}

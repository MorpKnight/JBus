package com.GiovanChristoffelSihombingJBusRS;


import com.GiovanChristoffelSihombingJBusRS.dbjson.Serializable;

/**
 * The Station class represents a station with properties such as city, station name, and address.
 */
public class Station extends Serializable
{
    public City city;
    public String stationName;
    public String address;
    
    public Station(String stationName, City city, String address){
        super();
        this.city = city;
        this.stationName = stationName;
        this.address = address;
    }
    
    /**
     * The toString() function returns a string representation of an object's id, city, stationName,
     * and address.
     * 
     * @return The toString method is returning a string representation of an object, which includes
     * the id, city, stationName, and address properties of the object.
     */
    public String toString(){
        return ("id: " + this.id + "\ncity: " + this.city + "\nstationName: " + this.stationName + "\naddress: " + this.address);
    }
}

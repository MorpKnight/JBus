package com.GiovanChristoffelSihombingJBusRS;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * The Payment class represents a payment made for a bus rental, including information such as the
 * buyer ID, renter ID, bus ID, departure date, and bus seat.
 */
public class Payment extends Invoice
{
    private int busId;
    public Timestamp departureDate;
    public List<String> busSeat;

    public Payment(int buyerId, int renterId, int busId, List<String> busSeat, Timestamp departureDate){
        super(buyerId, renterId);
        this.busId = busId;
        this.busSeat = busSeat;
        this.departureDate = new Timestamp(departureDate.getTime() + 172800000);
    }

    public Payment(Account buyer, Renter renter, int busId, List<String> busSeat, Timestamp departureDate){
        super(buyer, renter);
        this.busId = busId;
        this.busSeat = busSeat;
        this.departureDate = new Timestamp(departureDate.getTime() + 172800000);
    }
    
    /**
     * The function returns a string containing information about a payment, buyer, renter, bus,
     * departure date, and bus seat.
     * 
     * @return The method is returning a string that contains information about the payment, buyer,
     * renter, bus, departure date, and bus seat.
     */
    public String getDepartureInfo(){
        return ("Payment Id: " + this.id + " Buyer Id: " + this.buyerId + " Renter Id: " + this.renterId + " busId: " + this.busId + " Departure Date: " + new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss").format(this.departureDate.getTime()) + " Bus Seat: " + this.busSeat);
    }
    
    /**
     * The function returns the bus ID.
     * 
     * @return The method is returning the value of the variable "busId".
     */
    public int getBusId(){
        return this.busId;
    }

    /**
     * The function returns the current time in the format "MMMM dd, yyyy HH:mm:ss".
     * 
     * @return The method is returning a formatted string representation of the current time.
     */
    public String getTIme(){
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss");
        return sdf.format(time);
    }

    /**
     * The function `availableSchedule` returns the first schedule in a given bus that matches the
     * departure schedule and has an available seat.
     * 
     * @param departureSchedule The departure schedule is a timestamp that represents the desired
     * departure time for the bus schedule.
     * @param seat The "seat" parameter is a String that represents the seat number or identifier.
     * @param bus The bus parameter is an object of the Bus class.
     * @return The method is returning a Schedule object.
     */
    public static Schedule availableSchedule(Timestamp departureSchedule, String seat, Bus bus){
        for(Schedule s : bus.schedules){
            if(s.departureSchedule.equals(departureSchedule) && s.isSeatAvailable(seat)){
                return s;
            }
        }
        return null;
    }

    /**
     * The function `availableSchedule` returns the first schedule from a list of schedules that
     * matches the given departure schedule and has available seats.
     * 
     * @param departureSchedule The departure schedule is a timestamp indicating the date and time of
     * the bus departure.
     * @param seats The `seats` parameter is a list of strings representing the seat numbers that you
     * want to check for availability.
     * @param bus The "bus" parameter is an object of the Bus class. It represents a bus and contains
     * information about its schedules and seats.
     * @return The method is returning a Schedule object.
     */
    public static Schedule availableSchedule(Timestamp departureSchedule, List<String> seats, Bus bus){
        for(Schedule s : bus.schedules) {
            if(s.departureSchedule.equals(departureSchedule) && s.isSeatAvailable(seats)) {
                return s;
            }
        }
        return null;
    }

    /**
     * The function `makeBooking` checks if a seat is available on a specific bus schedule and books it
     * if it is.
     * 
     * @param departureSchedule The departure schedule is a Timestamp object that represents the date
     * and time of the bus departure.
     * @param seat The seat parameter is a String that represents the seat number or identifier.
     * @param bus The bus parameter is an object of the Bus class.
     * @return The method is returning a boolean value. It returns true if a booking is successfully
     * made, and false otherwise.
     */
    public static boolean makeBooking(Timestamp departureSchedule, String seat, Bus bus){
        Schedule availableSchedule = availableSchedule(departureSchedule, seat, bus);
        if(availableSchedule!=null){
            availableSchedule.bookSeat(seat);
            return true;
        }
        return false;

        // for(Schedule sch : bus.schedules){
        //     if(sch.departureSchedule.equals(departureSchedule) && sch.isSeatAvailable(seat)){
        //         sch.bookSeat(seat);
        //         return true;
        //     }
        // }

        // return false;
    }

    /**
     * The function "makeBooking" checks if all the seats in a given list can be booked on a bus for a
     * specific departure schedule.
     * 
     * @param departureSchedule The departure schedule is a Timestamp object that represents the date
     * and time of the bus departure.
     * @param seats A list of seat numbers that the user wants to book on the bus.
     * @param bus The bus object represents the bus for which the booking is being made. It contains
     * information about the bus, such as its capacity, current bookings, and availability.
     * @return The method is returning a boolean value.
     */
    public static boolean makeBooking(Timestamp departureSchedule, List<String> seats, Bus bus){
        Schedule availableSchedule = availableSchedule(departureSchedule, seats, bus);
        if (availableSchedule != null) {
            if (availableSchedule.isSeatAvailable(seats)) {
                availableSchedule.bookSeat(seats);
                return true;
            }
        }
        return false;

        // for(String seat : seats){
        //     if(!makeBooking(departureSchedule, seat, bus)){
        //         return false;
        //     }
        // }

        // return true;
    }
}

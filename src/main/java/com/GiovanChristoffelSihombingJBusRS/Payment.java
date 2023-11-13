package com.GiovanChristoffelSihombingJBusRS;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Write a description of class Payment here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Payment extends Invoice
{
    private int busId;
    public Timestamp departureDate;
    public List<String> busSeat;
    
    /*public Payment(int id, int buyerId, int renterId, int busId, String departureDate, String busSeat){
        super(id, buyerId,  renterId);
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeat = busSeat;
    }
    
    public Payment(int id, Account buyer, Renter renter, int busId, String departureDate, String busSeat){
        super(id, buyer, renter);
        this.busId = busId;
        this.departureDate = departureDate;
        this.busSeat = busSeat;
    } */
    
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
     * Fungsi toString() mengembalikan string yang merepresentasikan objek payment.
     * 
     * @return toString() method mengembalikan string yang merepresentasikan objek payment, termasuk
     * id payment, id pembeli, id renter, waktu, id bus, tanggal keberangkatan, dan kursi bus.
     */
    public String getDepartureInfo(){
        return ("Payment Id: " + this.id + " Buyer Id: " + this.buyerId + " Renter Id: " + this.renterId + " busId: " + this.busId + " Departure Date: " + new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss").format(this.departureDate.getTime()) + " Bus Seat: " + this.busSeat);
    }
    
    public int getBusId(){
        return this.busId;
    }

    public String getTIme(){
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss");
        return sdf.format(time);
    }

//    public static boolean isAvailable(Timestamp departureSchedule, String seat, Bus bus){
//        for(Schedule schedule : bus.schedules){
//            if(schedule.isSeatAvailable(seat) && schedule.departureSchedule.equals(departureSchedule)){
//                return true;
//            }
//        }
//
//        return false;
//    }

    public static Schedule availableSchedule(Timestamp departureSchedule, String seat, Bus bus){
        Predicate<Schedule> checkSchedule = (s) -> departureSchedule.equals(s.departureSchedule) && s.isSeatAvailable(seat);

        return Algorithm.find(bus.schedules, checkSchedule);
    }

    public static Schedule availableSchedule(Timestamp departureSchedule, List<String> seats, Bus bus){
        Predicate<Schedule> checkSchedule = (s) -> departureSchedule.equals(s.departureSchedule) && s.isSeatAvailable(seats);

        return Algorithm.find(bus.schedules, checkSchedule);
    }

    public static boolean makeBooking(Timestamp departureSchedule, String seat, Bus bus){
        // if(isAvailable(departureSchedule, seat, bus)){
        //     for(Schedule schedule : bus.schedules){
        //         if(schedule.departureSchedule.equals(departureSchedule) && schedule.isSeatAvailable(seat)){
        //             schedule.bookSeat(seat);
        //             return true;
        //         }
        //     }
        // }

        // return false;
        for(Schedule sch : bus.schedules){
            if(sch.departureSchedule.equals(departureSchedule) && sch.isSeatAvailable(seat)){
                sch.bookSeat(seat);
                return true;
            }
        }

        return false;
    }

    public static boolean makeBooking(Timestamp departureSchedule, List<String> seats, Bus bus){
        for(String seat : seats){
            if(!makeBooking(departureSchedule, seat, bus)){
                return false;
            }
        }

        return true;
    }
}

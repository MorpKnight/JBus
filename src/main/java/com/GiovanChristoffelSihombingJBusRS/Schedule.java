package com.GiovanChristoffelSihombingJBusRS;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The Schedule class represents a schedule for a transportation service, with methods to check seat
 * availability, book seats, and print the schedule.
 */
public class Schedule
{
    public Timestamp departureSchedule;
    public Map<String, Boolean> seatAvailability = new LinkedHashMap<String, Boolean>();

    public Schedule(Timestamp departureSchedule, int numberOfSeats){
        this.departureSchedule = departureSchedule;
        initializeSeatAvailability(numberOfSeats);
    }

    /**
     * The function initializes the seat availability by creating a map of seat numbers and setting
     * their availability to true.
     * 
     * @param seat The parameter "seat" represents the total number of seats available.
     */
    private void initializeSeatAvailability(int seat){
        for (int i = 1; i <= seat; i++) {
            String sn = i < 10 ? "0"+i : ""+i;
            seatAvailability.put("RS" + sn, true);
        }
    }

    /**
     * The function checks if a seat is available based on a given seat name.
     * 
     * @param seat The parameter "seat" is a String that represents the seat number or identifier.
     * @return The method is returning a boolean value.
     */
    public boolean isSeatAvailable(String seat){
        return seatAvailability.containsKey(seat) && seatAvailability.get(seat);
    }

    /**
     * The function checks if any seat in a list of seats is available.
     * 
     * @param seats A list of strings representing seat numbers.
     * @return The method is returning a boolean value.
     */
    public boolean isSeatAvailable(List<String> seats){
        for(String seat: seats){
            isSeatAvailable(seat);
        }

        return false;
    }

    /**
     * The function "bookSeat" updates the availability of a seat to false in a seatAvailability map.
     * 
     * @param seat The seat parameter is a String that represents the seat that needs to be booked.
     */
    public void bookSeat(String seat){
        if(seatAvailability.containsKey(seat)){
            seatAvailability.put(seat, false);
        }
    }

    /**
     * The function books multiple seats by calling the bookSeat function for each seat in the given
     * list.
     * 
     * @param seats The "seats" parameter is a List of Strings that represents a list of seats to be
     * booked.
     */
    public void bookSeat(List<String> seats){
        for(String seat: seats){
            bookSeat(seat);
        }
    }

    /**
     * The function prints the departure schedule and the availability of seats in a formatted manner.
     */
    public void printSchedule(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy HH:mm:ss");
        String formattedDepartureSchedule = dateFormat.format(this.departureSchedule);
        
        System.out.println("Tanggal keberangkatan: " + formattedDepartureSchedule);
        
        System.out.println("Daftar kursi dan ketersediaan kursinya: ");
        
        int maxSeatsPerRow = 4;
        int currentSeat = 1;
        for (String seat : this.seatAvailability.keySet()) {
            String symbol = this.seatAvailability.get(seat) ? "O" : "X";
            System.out.print(seat + " : " + symbol + "\t");
            if (currentSeat % maxSeatsPerRow == 0) {
                System.out.println();
            }
            currentSeat++;
        }
        System.out.println("\n");
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDepartureSchedule = dateFormat.format(this.departureSchedule);
        int totalSeats = this.seatAvailability.size();
        int seatsTaken = 0;
        for (String seat : this.seatAvailability.keySet()) {
            if (!this.seatAvailability.get(seat)) {
                seatsTaken++;
            }
        }
        return "Schedule\t: " + formattedDepartureSchedule + "\n" +
                "Occupied\t: " + seatsTaken + "/" + totalSeats;
    }
}

package GiovanChristoffelSihombingJBusRS;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Write a description of class Schedule here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Schedule
{
    public Timestamp departureSchedule;
    public Map<String, Boolean> seatAvailability = new LinkedHashMap<String, Boolean>();

    public Schedule(Timestamp departureSchedule, int numberOfSeats){
        this.departureSchedule = departureSchedule;
        initializeSeatAvailability(numberOfSeats);
    }

    private void initializeSeatAvailability(int seat){
        for (int i = 1; i <= seat; i++) {
            String sn = i < 10 ? "0"+i : ""+i;
            seatAvailability.put("RS" + sn, true);
        }
    }

    public boolean isSeatAvailable(String seat){
        return seatAvailability.containsKey(seat) && seatAvailability.get(seat);
    }

    public boolean isSeatAvailable(List<String> seats){
        for(String seat: seats){
            isSeatAvailable(seat);
        }

        return false;
    }

    public void bookSeat(String seat){
        if(seatAvailability.containsKey(seat)){
            seatAvailability.put(seat, false);
        }
    }

    public void bookSeat(List<String> seats){
        for(String seat: seats){
            bookSeat(seat);
        }
    }

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

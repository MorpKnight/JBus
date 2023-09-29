package GiovanChristoffelSihombingJBusRS;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Write a description of class Schedule here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Schedule
{
    public Calendar departureSchedule;
    public Map<String, Boolean> seatAvailability;

    public Schedule(Calendar departureSchedule, int numberOfSeats){
        this.departureSchedule = departureSchedule;
        initializeSeatAvailability(numberOfSeats);
    }

    private void initializeSeatAvailability(int seat){
        seatAvailability = new LinkedHashMap<String, Boolean>();
        for(int i = 1; i <= seat; i++){
            seatAvailability.put("RS" + i, true);
        }
    }
}

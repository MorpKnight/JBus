package GiovanChristoffelSihombingJBusRS;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.text.SimpleDateFormat;

/**
 * Write a description of class Bus here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Bus extends Serializable implements FileParser {
    public int capacity;
    public Facility facility;
    public String name;
    public Price price;
    public City city;
    public Station departure, arrival;
    public BusType busType;
    public List<Schedule> schedules;

    public Bus(int id, String name, Facility facility, Price price, int capacity, BusType busType, City city,
            Station departure, Station arrival) {
        super(id);
        this.name = name;
        this.capacity = capacity;
        this.facility = facility;
        this.price = price;
        this.city = city;
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
                + this.facility + "\n" + this.price + "\ncity: " + this.city + "\ndeparture: "
                + this.departure.stationName + "\narrival: " + this.arrival.stationName + "\nbusType: " + this.busType);
    }

    public void addSchedule(Calendar calendar){
        schedules.add(new Schedule(calendar, capacity));
    }

    public void printSchedule(Schedule schedule){
        System.out.println("Tanggal keberangkatan: " + new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss").format(schedule.departureSchedule.getTime()));
        System.out.println("Daftar kursi dan ketersediaannya: " );
        int i = 0;
        for(String seat : schedule.seatAvailability.keySet()){
            System.out.print(seat + " : " + schedule.seatAvailability.get(seat) + " ");
            i += 1;
            if(i == 4){
                System.out.println();
                i = 0;
            }
        }
        
        System.out.println();
    }

    @Override
    public boolean read(String x){
        return true;
    }

    @Override
    public Object write(){
        return null;
    }
}

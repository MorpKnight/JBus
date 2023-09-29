package GiovanChristoffelSihombingJBusRS;

import java.util.Calendar;
import java.text.SimpleDateFormat;

/**
 * Write a description of class Payment here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Payment extends Invoice
{
    private int busId;
    public Calendar departureDate;
    public String busSeat;
    
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
    
    public Payment(int id, int buyerId, int renterId, int busId, String busSeat){
        super(id, buyerId, renterId);
        this.busId = busId;
        this.busSeat = busSeat;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 2);
        this.departureDate = calendar;
    }

    public Payment(int id, Account buyer, Renter renter, int busId, String busSeat){
        super(id, buyer, renter);
        this.busId = busId;
        this.busSeat = busSeat;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 2);
        this.departureDate = calendar;
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
        return sdf.format(this.time.getTime());
    }
}

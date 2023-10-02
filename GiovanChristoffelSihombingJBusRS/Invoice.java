package GiovanChristoffelSihombingJBusRS;

import java.sql.Timestamp;

public class Invoice extends Serializable
{
    public Timestamp time;
    public int buyerId;
    public int renterId;
    public BusRating rating;
    public PaymentStatus status;
    
    public enum BusRating{
        NONE, NEUTRAL, GOOD, BAD
    }
    
    public enum PaymentStatus{
        FAILED, WAITING, SUCCESS
    }
    
    protected Invoice(int id, int buyerId, int renterId){
        super(id);
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.rating = BusRating.NONE;
        this.time = Timestamp.from(java.time.Instant.now());
        this.status = PaymentStatus.WAITING;
    }
    
    public Invoice(int id, Account buyer, Renter renter){
        super(id);
        this.renterId = renter.id;
        this.buyerId = buyer.id;
        this.rating = BusRating.NONE;
        this.time = Timestamp.from(java.time.Instant.now());
        this.status = PaymentStatus.WAITING;
    }
    
    /**
     * Fungsi toString() mengembalikan string yang merepresentasikan objek invoice.
     * 
     * @return toString() method mengembalikan string yang merepresentasikan objek invoice, termasuk
     * id invoice, id pembeli, id renter, waktu, rating, dan status.
     */
    public String toString(){
        return ("Invoice Id: " + this.id + "\nbuyerId: "+ this.buyerId + "\nrenterId: " + this.renterId + "\ntime: " + this.time + "\nrating: " + this.rating + "\nstatus: " + this.status);
    }
}

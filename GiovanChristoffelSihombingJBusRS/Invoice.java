package GiovanChristoffelSihombingJBusRS;


/**
 * Write a description of class Invoice here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Invoice extends Serializable
{
    public String time;
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
    
    protected Invoice(int id, int buyerId, int renterId, String time){
        super(id);
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = time;
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
    }
    
    public Invoice(int id, Account buyer, Renter renter, String time){
        super(id);
        this.renterId = renter.id;
        this.buyerId = buyer.id;
        this.time = time;
        this.rating = BusRating.NONE;
        this.status = PaymentStatus.WAITING;
    }
    
    public String toString(){
        return ("Invoice Id: " + this.id + "\nbuyerId: "+ this.buyerId + "\nrenterId: " + this.renterId + "\ntime: " + this.time + "\nrating: " + this.rating + "\nstatus: " + this.status);
    }
}

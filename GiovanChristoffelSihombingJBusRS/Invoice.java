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
    
    public Invoice(int id, int buyerId, int renterId, String time){
        super(id);
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.time = time;
    }
    
    protected Invoice(int id, Account buyer, Renter renter, String time){
        super(id);
        this.renterId = renter.id;
        this.buyerId = buyer.id;
        this.time = time;
    }
    
    public String print(){
        return ("Invoice Id: " + this.id + "\nbuyerId: "+ this.buyerId + "\nrenterId: " + this.renterId + "\ntime: " + this.time);
    }
}

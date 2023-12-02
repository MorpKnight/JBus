package com.GiovanChristoffelSihombingJBusRS;

import com.GiovanChristoffelSihombingJBusRS.dbjson.Serializable;

import java.sql.Timestamp;

/**
 * The Invoice class represents an invoice with properties such as buyerId, renterId, rating, and
 * status.
 */
public class Invoice extends Serializable
{
    public Timestamp time;
    public int buyerId;
    public int renterId;
    public BusRating rating;
    public PaymentStatus status;
    
    // The `public enum BusRating{}` is declaring an enumeration called `BusRating`. An enumeration is
    // a special type in Java that represents a group of named constants. In this case, `BusRating`
    // represents the possible ratings for a bus in an invoice. The constants in the `BusRating`
    // enumeration are `NONE`, `NEUTRAL`, `GOOD`, and `BAD`.
    public enum BusRating{
        NONE, NEUTRAL, GOOD, BAD
    }
    
    // The `public enum PaymentStatus{}` is declaring an enumeration called `PaymentStatus`. An
    // enumeration is a special type in Java that represents a group of named constants. In this case,
    // `PaymentStatus` represents the possible payment statuses for an invoice. The constants in the
    // `PaymentStatus` enumeration are `FAILED`, `WAITING`, and `SUCCESS`.
    public enum PaymentStatus{
        FAILED, WAITING, SUCCESS
    }
    
    protected Invoice(int buyerId, int renterId){
        super();
        this.buyerId = buyerId;
        this.renterId = renterId;
        this.rating = BusRating.NONE;
        this.time = new Timestamp(System.currentTimeMillis());
        this.status = PaymentStatus.WAITING;
    }
    
    public Invoice(Account buyer, Renter renter){
        super();
        this.renterId = renter.id;
        this.buyerId = buyer.id;
        this.rating = BusRating.NONE;
        this.time = new Timestamp(System.currentTimeMillis());
        this.status = PaymentStatus.WAITING;
    }
    
    /**
     * The toString() function returns a string representation of an invoice object.
     * 
     * @return The toString method is returning a string representation of an invoice object.
     */
    public String toString(){
        return ("Invoice Id: " + this.id + "\nbuyerId: "+ this.buyerId + "\nrenterId: " + this.renterId + "\ntime: " + this.time + "\nrating: " + this.rating + "\nstatus: " + this.status);
    }
}

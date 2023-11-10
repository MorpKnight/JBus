package com.GiovanChristoffelSihombingJBusRS;


import com.GiovanChristoffelSihombingJBusRS.dbjson.Serializable;

/**
 * Write a description of class Review here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Review extends Serializable
{
    public String date;
    public String desc;
    
    public Review(int id, String date, String desc){
        super();
        this.date = date;
        this.desc = desc;
    }
    

    /**
     * The toString() function returns a string representation of the review object, including its id,
     * date, and description.
     * 
     * @return The method is returning a string representation of the review object, which includes the
     * review id, date, and description.
     */
    public String toString(){
        return ("Review id: " + this.id + "\ndate: " + this.date + "\ndesc: " + this.desc);
    }
}

package GiovanChristoffelSihombingJBusRS;


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
        super(id);
        this.date = date;
        this.desc = desc;
    }
    
    public String toString(){
        return ("Review id: " + this.id + "\ndate: " + this.date + "\ndesc: " + this.desc);
    }
}
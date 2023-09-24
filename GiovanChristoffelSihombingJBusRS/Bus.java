package GiovanChristoffelSihombingJBusRS;


/**
 * Write a description of class Bus here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Bus extends Serializable
{
    public int capacity;
    public Facility facility;
    public String name;
    public Price price;
    
    public Bus(int id, String name, Facility facility, Price price, int capacity) {
        super(id);
        this.name = name;
        this.capacity = capacity;
        this.facility = facility;
        this.price = price;
    }
}

package GiovanChristoffelSihombingJBusRS;


/**
 * Write a description of class Bus here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Bus
{
    public int capacity;
    public Facility facility;
    public String name;
    public Price price;
    
    public Bus(String _name, Facility _facility, Price _price, int _capacity){
        this.name = _name;
        this.facility = _facility;
        this.price = _price;
        this.capacity = _capacity;
    }
}

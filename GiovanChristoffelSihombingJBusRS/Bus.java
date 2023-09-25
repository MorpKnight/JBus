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
    public City city;
    public Station departure, arrival;
    public BusType busType;
    
    public Bus(int id, String name, Facility facility, Price price, int capacity, BusType busType, City city, Station departure, Station arrival) {
        super(id);
        this.name = name;
        this.capacity = capacity;
        this.facility = facility;
        this.price = price;
        this.city = city;
        this.departure = departure;
        this.arrival = arrival;
        this.busType = busType;
    }
    
    public String toString(){
        return ("Bus Id: " + this.id + "\nname: " + this.name + "\ncapacity: " + this.capacity + "\nfacility: " + this.facility + "\n" + this.price + "\ncity: " + this.city + "\ndeparture: " + this.departure.stationName + "\narrival: " + this.arrival.stationName + "\nbusType: " + this.busType);
    }
}

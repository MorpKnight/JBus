package GiovanChristoffelSihombingJBusRS;


/**
 * Write a description of class Station here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Station extends Serializable
{
    public City city;
    public String stationName;
    public String address;
    
    public Station(int id, String stationName, City city, String address){
        super(id);
        this.city = city;
        this.stationName = stationName;
        this.address = address;
    }
    
    public String toString(){
        return ("id: " + this.id + "\ncity: " + this.city + "\nstationName: " + this.stationName + "\naddress: " + this.address);
    }
}

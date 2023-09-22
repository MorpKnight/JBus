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
    
    public Station(int id, String stationName, City city){
        super(id);
        this.city = city;
        this.stationName = stationName;
    }
    
    public String print(){
        return ("id: " + this.id + "\ncity: " + this.city + "\nstationName: " + this.stationName);
    }
}

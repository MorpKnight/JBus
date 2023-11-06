package GiovanChristoffelSihombingJBusRS;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Calendar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

/**
 * Write a description of class JBus here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class JBus
{
    public static void main(String[] args) throws InterruptedException {

        try {
            String filePath = "D:\\Programming\\Java\\JBus\\data\\accountDatabase.json";
            JsonTable<Account> tableAccount = new JsonTable<>(Account.class, filePath);
            tableAccount.add(new Account("Giovan", "christoffelsihombing@gmail.com", "YourSluttyDaddy123"));
            tableAccount.writeJson();
            System.out.println(tableAccount.toString());
        } catch (Throwable t){
            t.printStackTrace();
        }

        Bus bus = createBus();
        bus.schedules.forEach(Schedule::printSchedule);
        for(int i = 0; i < 10; i++){
            BookingThread thread = new BookingThread("Thread " + i, bus, Timestamp.valueOf("2023-07-27 19:00:00"));
        }

        Thread.sleep(1000);
        bus.schedules.forEach(Schedule::printSchedule);
    }

    public static List<Bus> filterByDeparture(List<Bus> buses, City departure, int page, int pageSize){
        // return all Bus with departure == departure, and do pagination
        return Algorithm.paginate(buses, page, pageSize, b -> b.departure.city.equals(departure));
    }

    public static List<Bus> filterByPrice(List<Bus> buses, int min, int max){
        return Algorithm.collect(buses, (Predicate<Bus>) b -> b.price.price >= min && b.price.price <= max);
    }

    public static List<Bus> filterBusId(List<Bus> buses, int id){
        return Algorithm.collect(buses, (Predicate<Bus>)  b -> b.id == id);
    }

    public static List<Bus> filterByDepartureAndArrival(List<Bus> buses, City departure, City arrival, int page, int pageSize){
        return Algorithm.paginate(buses, page, pageSize, b -> b.departure.city.equals(departure) && b.arrival.city.equals(arrival));
    }

    public static Bus createBus() {
        Price price = new Price(750000, 5);
        Bus bus = new Bus("Netlab Bus", Facility.LUNCH, price, 25, BusType.REGULER, City.BANDUNG, new Station("Depok Terminal", City.DEPOK, "Jl. Margonda Raya"), new Station("Halter UI", City.JAKARTA, "Universitas Indonesia"));
        Timestamp timestamp = Timestamp.valueOf("2023-07-27 19:00:00");
        bus.addSchedule(timestamp);
        return bus;
    }
}

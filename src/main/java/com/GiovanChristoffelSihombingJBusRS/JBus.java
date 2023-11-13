package com.GiovanChristoffelSihombingJBusRS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.GiovanChristoffelSihombingJBusRS.dbjson.JsonDBEngine;

import java.sql.Timestamp;
import java.util.List;
/**
 * Write a description of class JBus here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

@SpringBootApplication
public class JBus
{
    public static void main(String[] args) throws InterruptedException {
        JsonDBEngine.Run(JBus.class);
        SpringApplication.run(JBus.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> JsonDBEngine.join()));
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

//    public static Bus createBus() {
//        Price price = new Price(750000, 5);
//        Bus bus = new Bus("Netlab Bus", Facility.LUNCH, price, 25, BusType.REGULER, City.BANDUNG, new Station("Depok Terminal", City.DEPOK, "Jl. Margonda Raya"), new Station("Halter UI", City.JAKARTA, "Universitas Indonesia"));
//        Timestamp timestamp = Timestamp.valueOf("2023-07-27 19:00:00");
//        bus.addSchedule(timestamp);
//        return bus;
//    }
}

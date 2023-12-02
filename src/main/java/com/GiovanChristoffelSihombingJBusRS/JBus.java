package com.GiovanChristoffelSihombingJBusRS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.GiovanChristoffelSihombingJBusRS.dbjson.JsonDBEngine;

import java.util.List;

// The `@SpringBootApplication` annotation is used in Spring Boot applications to indicate the main
// class of the application. It is a combination of three annotations: `@Configuration`,
// `@EnableAutoConfiguration`, and `@ComponentScan`.
@SpringBootApplication
public class JBus
{
    // The `public static void main(String[] args) throws InterruptedException` method is the entry
    // point of the Java application. It is the starting point of the program execution.
    public static void main(String[] args) throws InterruptedException {
        JsonDBEngine.Run(JBus.class);
        SpringApplication.run(JBus.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> JsonDBEngine.join()));
    }

    /**
     * The function filters a list of buses based on their departure city and applies pagination.
     * 
     * @param buses A list of Bus objects.
     * @param departure The "departure" parameter is the city from which the buses are departing. It is
     * used to filter the list of buses based on their departure city.
     * @param page The "page" parameter is used to specify which page of results to retrieve. It is an
     * integer value that represents the page number. The first page is typically represented by the
     * number 1.
     * @param pageSize The pageSize parameter determines the number of Bus objects that should be
     * included in each page of the paginated result.
     * @return The method is returning a filtered and paginated list of Bus objects.
     */
    public static List<Bus> filterByDeparture(List<Bus> buses, City departure, int page, int pageSize){
        // return all Bus with departure == departure, and do pagination
        return Algorithm.paginate(buses, page, pageSize, b -> b.departure.city.equals(departure));
    }

    /**
     * The function filters a list of buses based on their price, returning only those with prices
     * within a specified range.
     * 
     * @param buses A list of Bus objects.
     * @param min The minimum price value to filter by.
     * @param max The "max" parameter is an integer that represents the maximum price value for
     * filtering the list of buses.
     * @return The method is returning a filtered list of buses that fall within the specified price
     * range.
     */
    public static List<Bus> filterByPrice(List<Bus> buses, int min, int max){
        return Algorithm.collect(buses, (Predicate<Bus>) b -> b.price.price >= min && b.price.price <= max);
    }

    /**
     * The function filters a list of buses based on their ID.
     * 
     * @param buses A list of Bus objects.
     * @param id The id parameter is an integer value that represents the id of the bus that we want to
     * filter.
     * @return The method is returning a List of Bus objects that have a matching id value.
     */
    public static List<Bus> filterBusId(List<Bus> buses, int id){
        return Algorithm.collect(buses, (Predicate<Bus>)  b -> b.id == id);
    }

    /**
     * The function filters a list of buses based on their departure and arrival cities, and returns a
     * paginated result.
     * 
     * @param buses A list of Bus objects.
     * @param departure The departure parameter is the city from which the buses are departing. It is
     * of type City.
     * @param arrival The "arrival" parameter is the city where the bus is scheduled to arrive.
     * @param page The page parameter is used to specify which page of results to retrieve. It is an
     * integer value that represents the page number. The first page is typically represented by the
     * number 1.
     * @param pageSize The pageSize parameter determines the number of items that will be displayed on
     * each page of the paginated result.
     * @return The method is returning a filtered list of buses that match the specified departure and
     * arrival cities. The list is paginated based on the given page number and page size.
     */
    public static List<Bus> filterByDepartureAndArrival(List<Bus> buses, City departure, City arrival, int page, int pageSize){
        return Algorithm.paginate(buses, page, pageSize, b -> b.departure.city.equals(departure) && b.arrival.city.equals(arrival));
    }
}

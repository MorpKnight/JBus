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
    public static void main(String[] args) {
        // TP MODUL 6
        // String filePath = "D:\\Programming\\Java\\JBus\\data\\station.json";
        // Gson gson = new Gson();

        // try {
        //     BufferedReader buffer = new BufferedReader(new FileReader(filePath));
        //     List<Station> stationjson = gson.fromJson(buffer, new TypeToken<List<Station>>() {}.getType());
        //     stationjson.forEach(e -> System.out.println(e.toString()));
        //     System.out.println();
        //     buffer.close();
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }

        try {
            String filePath = "D:\\Programming\\Java\\JBus\\data\\buses_CS.json";
            JsonTable<Bus> buses = new JsonTable<>(Bus.class, filePath);
//            List<Bus> filteredBus = filterByDeparture(buses, City.JAKARTA, 0, 3);
//            List<Bus> filteredBus = filterByPrice(buses, 100000, 500000);
//            List<Bus> filteredBus = filterBusId(buses, 155);
            List<Bus> filteredBus = filterByDepartureAndArrival(buses, City.JAKARTA, City.SURABAYA, 0, 3);
            filteredBus.forEach(bus -> System.out.println(bus.toString()));
        } catch (Throwable t){
            t.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        // PT Modul 5
//        // Tes Pagination
//        Bus b = createBus();
//        List<Timestamp> listOfSchedules = new ArrayList<>();
//        listOfSchedules.add(Timestamp.valueOf("2023-7-18 15:00:00"));
//        listOfSchedules.add(Timestamp.valueOf("2023-7-20 12:00:00"));
//        listOfSchedules.add(Timestamp.valueOf("2023-7-22 10:00:00"));
//        listOfSchedules.add(Timestamp.valueOf("2023-7-26 12:00:00"));
//
//        listOfSchedules.forEach(b::addSchedule);
//        System.out.println("Page 1");
//        Algorithm.paginate(b.schedules, 0, 3, t -> true).forEach(System.out::println);
//        System.out.println("=====================================================");
//        System.out.println("Page 2");
//        Algorithm.paginate(b.schedules, 1, 3, t -> true).forEach(System.out::println);
//        System.out.println("=====================================================");
//
//        // Tes Booking
//        String msgSuccess = "Booking Success!";
//        String msgFailed = "Booking Failed";
//        // valid date, invalid seat = Booking Failed
//        Timestamp t1 = Timestamp.valueOf("2023-7-19 15:00:00");
//        System.out.println("\nMake booking at July 19, 2023 15:00:00 Seats: RS17 RS18");
//        System.out.println(Payment.makeBooking(t1, List.of("RS17", "RS18"), b)? msgSuccess : msgFailed);
//        // valid date, invalid seat = Booking Failed
//        Timestamp t2 = Timestamp.valueOf("2023-7-18 15:00:00");
//        System.out.println("Make booking at July 18, 2023 15:00:00 Seat RS26");
//        System.out.println(Payment.makeBooking(t2, "RS26", b)? msgSuccess : msgFailed);
//        // valid date, valid seat = Booking Success
//        System.out.println("Make booking at July 18, 2023 15:00:00 Seats: RS07 RS08");
//        System.out.println(Payment.makeBooking(t2, List.of("RS07", "RS08"), b)? msgSuccess : msgFailed);
//        // valid date, valid seat = Booking Success
//        Timestamp t3 = Timestamp.valueOf("2023-7-20 12:00:00");
//        System.out.println("Make booking at July 20, 2023 12:00:00 Seats: RS01 RS02");
//        System.out.println(Payment.makeBooking(t3, List.of("RS01", "RS02"), b)? msgSuccess : msgFailed);
//        // valid date, book the same seat = Booking Failed
//        System.out.println("Make booking at July 20, 2023 12:00:00 Seat RS01");
//        System.out.println(Payment.makeBooking(t3, "RS01", b)? msgSuccess : msgFailed);
//        // check if the data changed
//        System.out.println("\nUpdated Schedule");
//        Algorithm.paginate(b.schedules, 0, 4, t-> true).forEach(System.out::println);
//    }

//    public static void main(String[] args) {
//        Integer[] numbers = {18, 10, 22, 43, 18, 67, 12, 11, 88, 22, 18};
//        System.out.println("Number "+ Arrays.toString(numbers));
//
//        // Tes Algorithm
//        System.out.print("1. ");
//        testCount(numbers);
//        System.out.print("2. ");
//        testFind(numbers);
//        System.out.print("3. ");
//        testExist(numbers);
//        System.out.println("4. Filtering");
//        testCollect(numbers);
//    }
//    private static void testExist(Integer[] t) {
//        int valueToCheck = 67;
//        boolean result3 = Algorithm.exists(t, valueToCheck);
//        if (result3) {
//            System.out.println(valueToCheck + " exist in the array.");
//        } else {
//            System.out.println(valueToCheck + " doesn't exists in the array.");
//        }
//    }
//    public static void testCount(Integer[] t) {
//        int valueToCount = 18;
//        int result = Algorithm.count(t, valueToCount);
//        System.out.println("Number " + valueToCount + " appears " + result + " times");
//    }
//    public static void testFind(Integer[] t) {
//        Integer valueToFind = 69;
//        Integer result2 = Algorithm.find(t, valueToFind);
//        System.out.print("Finding " + valueToFind + " inside the array : ");
//        if (result2 != null) {
//            System.out.println("Found!" + result2);
//        } else {
//            System.out.println("Not Found");
//        }
//    }
//    private static void testCollect(Integer[] t) {
//        Predicate<Integer> below = (val)->val<=22;
//        Predicate<Integer> above = (val)->val>43;
//
//        List<Integer> integerBelow = Algorithm.collect(t, below);
//        List<Integer> integerAbove = Algorithm.collect(t, above);
//
//        System.out.println("Below 22");
//        System.out.println(integerBelow);
//        System.out.println("Above 43");
//        System.out.println(integerAbove);
//    }
//

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
        Bus bus = new Bus("Netlab Bus", Facility.LUNCH, price, 25, BusType.REGULER, City.BANDUNG, new Station("Depok Terminal", City.DEPOK, "Jl. Margonda Raya"), new Station("Halte UI", City.JAKARTA, "Universitas Indonesia"));
        return bus;
    }
    
    /*public static int getBusId(){
        return 0;
        // dah benar
    }
    
    public static String getBusName(){
        return "Bus";
        // dah benar
    }

    public static boolean isDiscount(){
        return true;
        // dah benar
    }
    
    public static float getDiscountPercentage(int beforeDiscount, int afterDiscount){
        if (beforeDiscount > afterDiscount){
            float res = ((float)beforeDiscount - (float)afterDiscount)/(float)beforeDiscount;
            return res*100; 
        } else {
            return 0.0f;
        }
        
        // dah benar
    }
    
    public static int getDiscountedPrice(int price, float discountPercentage){
        if(discountPercentage > 100){
            discountPercentage = 100;
        }
        float res = (float)price * (discountPercentage/100);
        return price - (int)res; 
        
        // dah benar
    }
    
    public static int getOriginalPrice(int discountedPrice, float discountPercentage){
        float res = (float)discountedPrice / (1 - (discountPercentage/100));
        return (int)res;
        
        // dah benar
    }
    
    public static float getAdminFeePercentage(){
        return 0.05f;
        // dah benar
    }
    
    public static int getAdminFee(int price){
        float res = (float)price * getAdminFeePercentage();
        return (int)res;
        
        // dah benar
    }
    
    public static int getTotalPrice(int price, int numberOfSeat){
        int res = price * numberOfSeat;
        int adminFee = getAdminFee(res);
        return res + adminFee;
        
        // dah benar
    }
    */
}

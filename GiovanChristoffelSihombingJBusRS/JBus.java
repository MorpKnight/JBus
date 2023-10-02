package GiovanChristoffelSihombingJBusRS;

// import java.sql.Time;
import java.sql.Timestamp;
// import java.util.Calendar;

/**
 * Write a description of class JBus here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class JBus
{
    public static void main(String args[]){
        Bus b = createBus();
        
        Timestamp schedule1 = Timestamp.valueOf("2023-7-18 15:00:00");
        Timestamp schedule2 = Timestamp.valueOf("2023-7-20 12:00:00");

        b.addSchedule(schedule1);
        b.addSchedule(schedule2);
        b.schedules.forEach(Schedule::printSchedule);
        
        Timestamp t1 = Timestamp.valueOf("2023-7-19 15:00:00");
        System.out.println("Make booking at July 19, 2023 15:00:00 Seat RS12");
        System.out.println(Payment.makeBooking(t1, "RS12", b));
        
        Timestamp t2 = Timestamp.valueOf("2023-7-18 15:00:00");
        System.out.println("\nMake booking at July 18, 2023 15:00:00 Seat RS20");
        System.out.println(Payment.makeBooking(t2, "RS20", b));
        
        System.out.println("\nMake booking at July 18, 2023 15:00:00 Seat RS07");
        System.out.println(Payment.makeBooking(t2, "RS07", b));

        Timestamp t3 = Timestamp.valueOf("2023-7-20 12:00:00");
        System.out.println("\nMake booking at July 20, 2023 12:00:00 Seat RS01");
        System.out.println(Payment.makeBooking(t3, "RS01", b));

        System.out.println("\nMake booking at July 20, 2023 12:00:00 Seat RS01 again");
        System.out.println(Payment.makeBooking(t3, "RS01", b));
        
        System.out.println("\nUpdated Schedule\n");
        b.schedules.forEach(Schedule::printSchedule);
    }
    
    public static Bus createBus(){
        Station testDeparture = new Station(2, "Depok Terminal", City.DEPOK, "Jl. Margonda Raya");
        Station testArrival = new Station(3, "Halte UI", City.JAKARTA, "Universitas Indonesia");
        Price price = new Price(100000, 20000);
        Bus testBus = new Bus(1, "Busway", Facility.AC, price, 12, BusType.REGULER, City.DEPOK, testDeparture, testArrival);
        
        return testBus;
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

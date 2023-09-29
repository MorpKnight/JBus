package GiovanChristoffelSihombingJBusRS;

import java.util.Calendar;

/**
 * Write a description of class JBus here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class JBus
{
    public static void main(String args[]){
        // Review testReview = new Review(1, "23 Agustus 2023", "Bad Quality");
        // Price testPrice = new Price(100000, 20000);
        // Station testDeparture = new Station(2, "Depok Terminal", City.DEPOK, "Jl. Margonda Raya");
        // Station testArrival = new Station(3, "Halte UI", City.JAKARTA, "Universitas Indonesia");
        // Bus testBus = new Bus(1, "Busway", Facility.AC, testPrice, 50, BusType.REGULER, City.DEPOK, testDeparture, testArrival);
        // Account testAccount = new Account(1, "Bob", "bob@gmail.com", "bob");
        // Rating testRating = new Rating();

        // System.out.println(testReview);
        // System.out.println(testBus);
        // System.out.println(testAccount);
        // System.out.println(testPrice);
        // System.out.println(testRating);

        // Price[] unfilteredArray = new Price[5];

        // for(int i = 0; i < unfilteredArray.length; i++){
        //     int j = 5000;
        //     unfilteredArray[i] = new Price((i+1)*j);
        // }

        // System.out.println("Price List");
        // for(Price price: unfilteredArray){
        //     System.out.println(price.price);
        // }

        // System.out.println("Below 12000.0");
        // System.out.println(Validate.filter(unfilteredArray, 12000, true));
        // System.out.println("Above 10000");
        // System.out.println(Validate.filter(unfilteredArray, 10000, false));
    
        Bus testBus = createBus();
        Payment testPayment = new Payment(1, 1, 1, testBus.id, "S1");
        System.out.println(testPayment.getDepartureInfo());
        System.out.println(testPayment.getTIme());

        Calendar schedule1 = Calendar.getInstance();
        testBus.addSchedule(schedule1);
        Calendar schedule2 = Calendar.getInstance();
        schedule2.add(Calendar.DAY_OF_YEAR, 3);
        testBus.addSchedule(schedule2);

        for(Schedule s: testBus.schedules){
            testBus.printSchedule(s);
        }
    }
    
    public static Bus createBus(){
        // Bus bus = new Bus(1, "Bus", Facility.AC, newprice, 50);
        Station testDeparture = new Station(2, "Depok Terminal", City.DEPOK, "Jl. Margonda Raya");
        Station testArrival = new Station(3, "Halte UI", City.JAKARTA, "Universitas Indonesia");
        Price price = new Price(100000, 20000);
        Bus testBus = new Bus(1, "Busway", Facility.AC, price, 25, BusType.REGULER, City.DEPOK, testDeparture, testArrival);
        
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

package GiovanChristoffelSihombingJBusRS;


/**
 * Write a description of class JBus here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class JBus
{
    public static void main(String args[]){
        Bus testbus = createBus();
        System.out.println(testbus.name);
        System.out.println(testbus.facility);
        System.out.println(testbus.price.price);
        System.out.println(testbus.capacity);
    }
    
    public static Bus createBus(){
        Price newprice = new Price((double)750000, (double)10000);
        Bus bus = new Bus("Netlab Bus", Facility.WIFI, newprice, 25);
        
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

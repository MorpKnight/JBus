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
        
    }
    
    public static int getBusId(){
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
            return res; 
        } else {
            return 0.0f;
        }
        
        // dah benar
    }
    
    public static int getDiscountedPrice(int price, float discountPercentage){
        return price - (int)((float)price * discountPercentage);   
        
        // dah benar
    }
    
    public static int getOriginalPrice(int discountedPrice, float discountPercentage){
        float minDiscount = (float)1 - discountPercentage;
        float totalPrice = (float)discountedPrice/minDiscount;
        return (int)totalPrice;
        
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
}

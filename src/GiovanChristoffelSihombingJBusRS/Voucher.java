package GiovanChristoffelSihombingJBusRS;


/**
 * Write a description of class Voucher here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Voucher extends Serializable implements FileParser
{
    public String name;
    private boolean used;
    public double minimum;
    public double cut;
    public int code;
    public Type type;
    
    public Voucher(int id, String _name, int _code, Type _type, double _minimum, double _cut){
        super();
        this.name = _name;
        this.code = _code;
        this.minimum = _minimum;
        this.cut = _cut;
        this.used = false;
        this.type = _type;
    }
    
    /**
     * The function returns a boolean value indicating whether an object has been used or not.
     * 
     * @return The method is returning the value of the boolean variable "used".
     */
    public boolean isUsed(){
        return this.used;
    }

    /**
     * The function checks if a price can be applied based on its value and whether it has been used
     * before.
     * 
     * @param price The parameter "price" is an object of type "Price".
     * @return The method is returning a boolean value.
     */
    public boolean canApply(Price price){
        if(price.price >= this.minimum && !this.used){
            return true;
        }
        return false;
    }

    /**
     * The function applies a discount or rebate to a given price and returns the updated price.
     * 
     * @param price The "price" parameter is an object of the "Price" class. It likely contains
     * information about the original price of an item.
     * @return The method is returning a double value.
     */
    public double apply(Price price){
        if(!canApply(price)){
            return price.price;
        }

        this.used = true;
        if(this.type == Type.DISCOUNT){
            if(this.cut > 100){
                return 0;
            }else{
                return price.price - (price.price * (this.cut / 100));
            }
        } else if(this.type == Type.REBATE){
            return price.price - this.cut;
        }

        return price.price;
    }
    
    @Override
    public boolean read(String x){
        return false;
    }

    @Override
    public Object write(){
        return null;
    }
}

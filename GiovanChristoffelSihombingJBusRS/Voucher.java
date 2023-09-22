package GiovanChristoffelSihombingJBusRS;


/**
 * Write a description of class Voucher here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Voucher extends Serializable
{
    public String name;
    private boolean used;
    public double minimum;
    public double cut;
    public int code;
    public Type type;
    
    public Voucher(int id, String _name, int _code, Type _type, double _minimum, double _cut){
        super(id);
        this.name = _name;
        this.code = _code;
        this.minimum = _minimum;
        this.cut = _cut;
        this.used = false;
        this.type = _type;
    }
    
    public boolean isUsed(){
        return this.used;
    }

    public boolean canApply(Price price){
        if(price.price >= this.minimum && !this.used){
            return true;
        }
        return false;
    }

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
        } else {
            return price.price;
        }
    }
}

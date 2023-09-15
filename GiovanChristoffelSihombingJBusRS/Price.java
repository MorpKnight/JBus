package GiovanChristoffelSihombingJBusRS;

/**
 * Write a description of class Price here.
 *
 * @author Giovan Christoffel Sihombing
 * @version (a version number or a date)
 */
public class Price {
    double rebate;
    double price;
    int discount;
    
    public Price(double _price){
        this.price = _price;
    }

    public Price(double _price, int _discount) {
        this.price = _price;
        this.discount = _discount;
    }

    public Price(double _price, double _rebate){
        this.price = _price;
        this.rebate = _rebate;
    }
    
    private double getDiscountPrice(){
        if(discount > 100){
            discount = 100;
        } else if(discount == 100){
            return 0.0f;
        }
        
        double discountPrice = price * (double)discount / 100;
        double res = price - discountPrice;
        return res;
    }
    
    private double getRebatedPrice(){
        double res = price - rebate;
        if(res < 0){
            res = 0;
        }
        
        return res;
    }
    
    
    
}

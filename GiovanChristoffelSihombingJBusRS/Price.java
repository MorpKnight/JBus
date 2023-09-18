package GiovanChristoffelSihombingJBusRS;

/**
 * Write a description of class Price here.
 *
 * @author Giovan Christoffel Sihombing
 * @version (a version number or a date)
 */
public class Price {
    public double rebate;
    public double price;
    // public int discount;
    
    public Price(double _price){
        this.price = _price;
        // this.discount = 0;
        this.rebate = 0;
    }

    // public Price(double _price, int _discount) {
    //     this.price = _price;
    //     this.discount = _discount;
    //     this.rebate = 0;
    // }

    public Price(double _price, double _rebate){
        this.price = _price;
        this.rebate = _rebate;
        // this.discount = 0;
    }
    
    // private double getDiscountedPrice(){
    //     int tempDiscount = this.discount;
    //     if(this.discount > 100){
    //         tempDiscount = 100;
    //     } else if(this.discount == 100){
    //         return 0.0f;
    //     }
        
    //     double discountPrice = price * (double)tempDiscount / 100;
    //     double res = price - discountPrice;
    //     return res;
    // }
    
    // private double getRebatedPrice(){
    //     if(this.rebate > this.price){
    //         return 0;
    //     }

    //     return this.price - this.rebate;
    // }
}
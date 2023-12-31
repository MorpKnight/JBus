package com.GiovanChristoffelSihombingJBusRS;

/**
 * The Price class represents a price with an optional rebate value.
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

    public Price(double _price, double _rebate){
        this.price = _price;
        this.rebate = _rebate;
        // this.discount = 0;
    }

    /**
     * The toString() function returns a string representation of the price and rebate values.
     * 
     * @return The toString() method is returning a string representation of the object's price and
     * rebate values.
     */
    public String toString(){
        return ("price: " + this.price + "\nrebate: " + this.rebate);
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
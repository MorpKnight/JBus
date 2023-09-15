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

    public static void main(String args[]){
        Price harga = new Price(1000.0, 10);
        System.out.println(harga.getDiscountPrice());
    }
    
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
        price -= discountPrice;
        return price;
    }
    
    private double getRebatedPrice(){
        price -= rebate;
        if(price < 0){
            price = 0;
        }
        
        return price;
    }
    
    
    
}

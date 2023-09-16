package GiovanChristoffelSihombingJBusRS;

public class Rating {
    private long count;
    private long total;
    
    public Rating(){
        this.count = 0;
        this.total = 0;
    }
    
    public void insert(int rating){
        this.total += rating;
        this.count += 1;
    }
    
    public long getTotal(){
        return this.total;
    }
    
    public long getCount(){
        return this.count;
    }
    
    public double getAverage(){
        try {
            double res = (double)(getTotal()/getCount());
            return res;
        } catch (ArithmeticException e){
            return 0.0;
        }
    }
}

package GiovanChristoffelSihombingJBusRS;

public class Rating {
    private long count;
    private long total;
    
    public Rating(long count, long total){
        this.count = count;
        this.total = total;
    }
    
    void insert(int rating){
        this.total += rating;
        this.count += 1;
    }
    
    long getTotal(){
        return this.total;
    }
    
    long getCount(){
        return this.count;
    }
    
    double getAverage(){
        return (double)(getTotal()/getCount());
    }
}

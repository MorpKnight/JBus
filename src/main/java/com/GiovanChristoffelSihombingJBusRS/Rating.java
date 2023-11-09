package com.GiovanChristoffelSihombingJBusRS;

public class Rating {
    private long count;
    private long total;
    
    public Rating(){
        this.count = 0;
        this.total = 0;
    }
    
    /**
     * Fungsi insert digunakan untuk memasukkan rating ke dalam objek Rating.
     * 
     * @param rating parameter rating yang akan dimasukkan ke dalam objek Rating.
     */
    public void insert(int rating){
        this.total += rating;
        this.count += 1;
    }
    
    /**
     * Fungsi getTotal akan mengembalikan nilai dari variabel total.
     * 
     * @return Method ini akan mengembalikan nilai dari variabel total.
     */
    public long getTotal(){
        return this.total;
    }
    
    /**
     * Fungsi getCount akan mengembalikan nilai dari variabel count.
     * 
     * @return Method ini akan mengembalikan nilai dari variabel count.
     */
    public long getCount(){
        return this.count;
    }
    
    /**
     * Fungsi getAverage akan mengembalikan nilai rata-rata dari rating yang telah dimasukkan ke dalam objek Rating.
     * 
     * @return Method ini akan mengembalikan nilai rata-rata dari rating yang telah dimasukkan ke dalam objek Rating.
     */
    public double getAverage(){
        try {
            double res = (double)(getTotal()/getCount());
            return res;
        } catch (ArithmeticException e){
            return 0.0;
        }
    }
    
    /**
     * Fungsi toString akan mengembalikan string yang berisi nilai dari variabel total dan count.
     * 
     * @return Method ini akan mengembalikan string yang berisi nilai dari variabel total dan count.
     */
    public String toString(){
        return ("total: " + this.total + "\ncount: " + this.count);
    }
}

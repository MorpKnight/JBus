package GiovanChristoffelSihombingJBusRS;

import java.sql.Timestamp;

public class BookingThread extends Thread {
    private Bus bus;
    private Timestamp timestamp;

    public BookingThread(String name, Bus bus, Timestamp timestamp){
        super(name);
        this.bus = bus;
        this.timestamp = timestamp;
        this.start();
    }

    public void run(){
        System.out.println(this.getName() + " ID : " + Thread.currentThread().getId() + " is running");
        synchronized (this.bus) {
            boolean booking = Payment.makeBooking(timestamp, "RS01", bus);
            if (booking){
                System.out.println(this.getName() + " berhasil melakukan booking");
            } else {
                System.out.println(this.getName() + " gagal melakukan booking");
            }
        }
    }
}

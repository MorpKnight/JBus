package com.GiovanChristoffelSihombingJBusRS;

import java.sql.Timestamp;

/**
 * The BookingThread class is a Java thread that attempts to make a booking for a bus using a specified
 * timestamp and payment information.
 */
public class BookingThread extends Thread {
    private Bus bus;
    private Timestamp timestamp;

    public BookingThread(String name, Bus bus, Timestamp timestamp){
        super(name);
        this.bus = bus;
        this.timestamp = timestamp;
        this.start();
    }

    /**
     * The run() function prints the name and ID of the current thread, and then attempts to make a
     * booking using the Payment.makeBooking() method.
     */
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

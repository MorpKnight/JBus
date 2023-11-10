package com.GiovanChristoffelSihombingJBusRS;


import com.GiovanChristoffelSihombingJBusRS.dbjson.Serializable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Write a description of class Account here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Account extends Serializable
{
    // instance variables - replace the example below with your own
    public String email;
    public String name;
    public Renter company;
    public double balance;
    public String password;
    public static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9]+@[a-zA-Z_]+?\\.[a-zA-Z.]+[a-zA-Z]+$";

    public Account(String name, String email, String password) {
        super();
        this.email = email;
        this.name = name;
        this.password = password;
        this.balance = 0;
    }
    
    /**
     * The toString() function mengembalikan string yang merepresentasikan objek account.
     * 
     * @return toString() method mengembalikan string yang merepresentasikan objek account, termasuk id akun, email, nama, dan password.
     */
    public String toString(){
        return ("Account Id: " + this.id + "\nemail: " + this.email + "\nname: " + this.name + "\npassword: " + this.password);
    }

    public boolean validate(){
        return this.email.matches(REGEX_EMAIL) && this.password.matches(REGEX_PASSWORD);
    }

}

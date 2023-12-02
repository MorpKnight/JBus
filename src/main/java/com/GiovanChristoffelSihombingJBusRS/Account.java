package com.GiovanChristoffelSihombingJBusRS;


import com.GiovanChristoffelSihombingJBusRS.dbjson.Serializable;

/**
 * Write a description of class Account here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

/**
 * The Account class represents a user account with properties such as email, name, company, balance,
 * and password, along with regular expressions for password and email validation.
 */
public class Account extends Serializable
{
    
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
     * The toString() function returns a string representation of an account object, including its id,
     * email, name, and password.
     * 
     * @return The toString() method is returning a string representation of an account object. It
     * includes the account id, email, name, and password.
     */
    public String toString(){
        return ("Account Id: " + this.id + "\nemail: " + this.email + "\nname: " + this.name + "\npassword: " + this.password);
    }

    /**
     * The function validates if the email and password match the specified regular expressions.
     * 
     * @return The method is returning a boolean value.
     */
    public boolean validate(){
        return this.email.matches(REGEX_EMAIL) && this.password.matches(REGEX_PASSWORD);
    }

}

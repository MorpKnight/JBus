package com.GiovanChristoffelSihombingJBusRS.controller.model;

import com.GiovanChristoffelSihombingJBusRS.dbjson.Serializable;

/**
 * The BaseAccount class represents a basic account with attributes such as name, email, password,
 * companyName, address, phoneNumber, and balance.
 */
public class BaseAccount extends Serializable {
    public String name, email, password, companyName, address, phoneNumber;
    public double balance;

    public BaseAccount(String name, String email, String password, String companyName, String address, String phoneNumber, double balance) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.companyName = companyName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
    }
}


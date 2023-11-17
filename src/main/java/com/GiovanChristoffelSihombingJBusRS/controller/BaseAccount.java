package com.GiovanChristoffelSihombingJBusRS.controller;

public class BaseAccount {
    public String name, email, password, companyName, address, phoneNumber;
    public int id;
    public double amount;

    public BaseAccount(String name, String email, String password, String companyName, String address, String phoneNumber, int id, double amount) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.companyName = companyName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.id = id;
        this.amount = amount;
    }
}

package com.GiovanChristoffelSihombingJBusRS.controller.model;

public class BaseCompany {
    public String companyName, phoneNumber, address;

    public BaseCompany(String companyName, String phoneNumber, String address){
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}

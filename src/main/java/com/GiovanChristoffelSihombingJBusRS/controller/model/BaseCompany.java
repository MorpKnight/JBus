package com.GiovanChristoffelSihombingJBusRS.controller.model;

/**
 * The BaseCompany class represents a company with properties such as companyName, phoneNumber, and
 * address.
 */
public class BaseCompany {
    public String companyName, phoneNumber, address;

    public BaseCompany(String companyName, String phoneNumber, String address){
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}

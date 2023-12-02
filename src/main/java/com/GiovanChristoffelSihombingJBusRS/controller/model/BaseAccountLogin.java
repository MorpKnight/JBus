package com.GiovanChristoffelSihombingJBusRS.controller.model;

import com.GiovanChristoffelSihombingJBusRS.dbjson.Serializable;

/**
 * The BaseAccountLogin class is a serializable class that represents a user's email and password for
 * login purposes.
 */
public class BaseAccountLogin extends Serializable {
    public String email, password;

    public BaseAccountLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }
}

package com.GiovanChristoffelSihombingJBusRS.controller.model;

import com.GiovanChristoffelSihombingJBusRS.dbjson.Serializable;

/**
 * The BaseAccountRegister class is a serializable class that represents a user's account registration
 * information, including their name, email, and password.
 */
public class BaseAccountRegister extends Serializable {
    public String email, name, password;

    public BaseAccountRegister(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }
}

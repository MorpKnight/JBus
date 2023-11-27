package com.GiovanChristoffelSihombingJBusRS.controller;

import com.GiovanChristoffelSihombingJBusRS.dbjson.Serializable;

public class BaseAccountLogin extends Serializable {
    public String email, password;

    public BaseAccountLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }
}

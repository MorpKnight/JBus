package com.GiovanChristoffelSihombingJBusRS.controller.model;

import com.GiovanChristoffelSihombingJBusRS.dbjson.Serializable;

public class BaseAccountRegister extends Serializable {
    public String email, name, password;

    public BaseAccountRegister(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }
}

package com.GiovanChristoffelSihombingJBusRS.controller;

/**
 * The BaseResponse class is a generic class that represents a response with a success flag, a message,
 * and a payload of any type.
 */
public class BaseResponse<T> {
    public boolean success;
    public String message;
    public T payload;

    public BaseResponse(boolean success, String message, T payload) {
        this.success = success;
        this.message = message;
        this.payload = payload;
    }
}

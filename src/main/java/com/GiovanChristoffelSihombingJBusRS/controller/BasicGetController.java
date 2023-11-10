package com.GiovanChristoffelSihombingJBusRS.controller;

import com.GiovanChristoffelSihombingJBusRS.Algorithm;
import com.GiovanChristoffelSihombingJBusRS.dbjson.JsonTable;
import com.GiovanChristoffelSihombingJBusRS.dbjson.Serializable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface BasicGetController <T extends Serializable> {
    JsonTable<T> getJsonTable();

    @GetMapping("/{id}")
    public default T getById(@PathVariable int id){
        return Algorithm.<T>find(getJsonTable(), b -> b.id == id);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public default List<T> getPage(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int pageSize
    ){
        return Algorithm.<T>paginate(getJsonTable(), page, pageSize, b -> true);
    }
}

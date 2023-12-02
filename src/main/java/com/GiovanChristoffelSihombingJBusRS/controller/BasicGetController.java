package com.GiovanChristoffelSihombingJBusRS.controller;

import com.GiovanChristoffelSihombingJBusRS.Algorithm;
import com.GiovanChristoffelSihombingJBusRS.dbjson.JsonTable;
import com.GiovanChristoffelSihombingJBusRS.dbjson.Serializable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// The line `public interface BasicGetController <T extends Serializable> {` is declaring a generic
// interface named `BasicGetController`. The generic type `T` is bounded by the `Serializable`
// interface, which means that any type `T` used with this interface must implement the `Serializable`
// interface.
public interface BasicGetController <T extends Serializable> {
    JsonTable<T> getJsonTable();

    /**
     * The function retrieves an object of type T by its ID from a JSON table.
     * 
     * @param id The "id" parameter is a path variable that represents the unique identifier of the
     * object you want to retrieve.
     * @return The method is returning an object of type T.
     */
    @GetMapping("/{id}")
    public default T getById(@PathVariable int id){
        return Algorithm.<T>find(getJsonTable(), b -> b.id == id);
    }

    /**
     * This function returns a paginated list of objects based on the specified page number and page
     * size.
     * 
     * @param page The "page" parameter is used to specify the page number of the data you want to
     * retrieve. By default, it is set to 0, which means the first page.
     * @param pageSize The `pageSize` parameter is used to specify the number of items to be displayed
     * on each page of the paginated result. By default, it is set to 5, meaning that each page will
     * display 5 items. However, you can change this value by passing a different integer value as the
     * @return The method is returning a List of objects of type T.
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public default List<T> getPage(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int pageSize
    ){
        return Algorithm.<T>paginate(getJsonTable(), page, pageSize, b -> true);
    }
}

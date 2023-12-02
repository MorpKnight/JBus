package com.GiovanChristoffelSihombingJBusRS.controller;

import com.GiovanChristoffelSihombingJBusRS.City;
import com.GiovanChristoffelSihombingJBusRS.Station;
import com.GiovanChristoffelSihombingJBusRS.controller.model.BaseStation;
import com.GiovanChristoffelSihombingJBusRS.dbjson.JsonAutowired;
import com.GiovanChristoffelSihombingJBusRS.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The StationController class is a REST controller that handles requests
 * related to stations,
 * including creating a new station and retrieving all stations.
 */
@RestController
@RequestMapping("/station")
public class StationController implements BasicGetController<Station> {
    public static @JsonAutowired(value = Station.class, filepath = "src/main/java/com/GiovanChristoffelSihombingJBusRS/json/station.json") JsonTable<Station> stationTable;

    /**
     * The function returns a JsonTable object containing Station objects.
     * 
     * @return The method is returning a JsonTable object of type Station.
     */
    @Override
    public JsonTable<Station> getJsonTable() {
        return stationTable;
    }

    /**
     * The `createStation` function in a Java Spring Boot application creates a new station object
     * based on the provided parameters and adds it to a station table.
     * 
     * @param baseStation The `baseStation` parameter is an object of type `BaseStation` which is
     * annotated with `@ModelAttribute`. It is used to bind the request parameters to the properties of
     * the `BaseStation` object.
     * @return The method is returning a `BaseResponse` object.
     */
    @PostMapping("/create")
    public BaseResponse<Station> createStation(
            @ModelAttribute BaseStation baseStation) {
        try {
            if (baseStation.stationName.isBlank() || baseStation.city.isBlank() || baseStation.address.isBlank()) {
                return new BaseResponse<>(false, "Parameter values cannot be blank or null", null);
            }

            City cityEnum = City.valueOf(baseStation.city.toUpperCase());
            Station newStation = new Station(baseStation.stationName, cityEnum, baseStation.address);
            stationTable.add(newStation);
            return new BaseResponse<>(true, "Station added successfully", newStation);
        } catch (IllegalArgumentException e) {
            return new BaseResponse<>(false, "Invalid city value", null);
        } catch (Exception e) {
            return new BaseResponse<>(false, "An error occurred while adding the station", null);
        }
    }

    /**
     * The function returns a list of all stations.
     * 
     * @return The method is returning a List of Station objects.
     */
    @GetMapping("/getAll")
    public List<Station> getAllStation() {
        return getJsonTable();
    }
}
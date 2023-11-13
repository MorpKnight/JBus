package com.GiovanChristoffelSihombingJBusRS.controller;

import com.GiovanChristoffelSihombingJBusRS.*;
import com.GiovanChristoffelSihombingJBusRS.dbjson.JsonAutowired;
import com.GiovanChristoffelSihombingJBusRS.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/bus")
public class BusController implements BasicGetController<Bus> {
    @JsonAutowired(value = Bus.class, filepath = "src/main/java/com/GiovanChristoffelSihombingJBusRS/json/buses_CS.json")
    public static JsonTable<Bus> busTable;

    public JsonTable<Bus> getJsonTable() {
        return busTable;
    }

    @GetMapping
    String index() { return "bus page"; }

    @PostMapping("/create")
    BaseResponse<Bus> create(
            @RequestParam int accountId,
            @RequestParam String name,
            @RequestParam int capacity,
            @RequestParam List<Facility> facility,
            @RequestParam BusType busType,
            @RequestParam int price,
            @RequestParam int stationDepartureId,
            @RequestParam int stationArrivalId
    ) {
        
        try {
            boolean isAccountIdValid = AccountController.accountTable.get(accountId) != null;
            boolean isRenter = AccountController.accountTable.get(accountId).company != null;
            Station dep = Algorithm.<Station>find(StationController.stationTable, s -> s.id == stationDepartureId);
            Station arr = Algorithm.<Station>find(StationController.stationTable, s -> s.id == stationArrivalId);

            if (!isAccountIdValid || !isRenter || dep == null || arr == null) {
                return new BaseResponse<>(false, "Data tidak valid", null);
            }

            Bus bus = new Bus(name, facility, new Price(price), capacity, busType, dep, arr);
            busTable.add(bus);
            return new BaseResponse<>(true, "Berhasil membuat bus", bus);
        } catch (Exception e) {
            return new BaseResponse<>(false, "Gagal membuat bus", null);
        }
    }

    @PostMapping("/addSchedule")
    BaseResponse<Bus> addSchedule(
            @RequestParam int busId, @RequestParam String time
    ) {
        try {
            Bus bus = Algorithm.<Bus>find(busTable, b -> b.id == busId);
            if (bus == null) {
                return new BaseResponse<>(false, "Bus tidak ditemukan", null);
            }

            Timestamp timestamp = Timestamp.valueOf(time);

            try{
                bus.addSchedule(timestamp);
            }catch (Exception e){
                return new BaseResponse<>(false, e.getMessage(), null);
            }

            return new BaseResponse<>(true, "Berhasil menambahkan jadwal", bus);
        } catch (Exception e) {
            return new BaseResponse<>(false, "Gagal melakukan operasi", null);
        }
    }
}

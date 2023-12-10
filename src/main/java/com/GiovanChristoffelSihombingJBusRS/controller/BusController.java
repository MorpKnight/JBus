package com.GiovanChristoffelSihombingJBusRS.controller;

import com.GiovanChristoffelSihombingJBusRS.*;
import com.GiovanChristoffelSihombingJBusRS.Invoice.PaymentStatus;
import com.GiovanChristoffelSihombingJBusRS.controller.model.BaseAddBusSchedule;
import com.GiovanChristoffelSihombingJBusRS.controller.model.BaseBus;
import com.GiovanChristoffelSihombingJBusRS.dbjson.JsonAutowired;
import com.GiovanChristoffelSihombingJBusRS.dbjson.JsonTable;

import retrofit2.http.Body;

import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

/**
 * The BusController class is a REST controller that handles CRUD operations for
 * managing bus data.
 */
@RestController
@RequestMapping("/bus")
public class BusController implements BasicGetController<Bus> {
    @JsonAutowired(value = Bus.class, filepath = "src/main/java/com/GiovanChristoffelSihombingJBusRS/json/buses_CS.json")
    public static JsonTable<Bus> busTable;

    /**
     * The function returns a JSON table of Bus objects.
     * 
     * @return A JsonTable object containing Bus objects is being returned.
     */
    public JsonTable<Bus> getJsonTable() {
        return busTable;
    }

    /**
     * The function returns the string "bus page" when the GET request is made to
     * the index endpoint.
     * 
     * @return The string "bus page" is being returned.
     */
    @GetMapping
    String index() {
        return "bus page";
    }

    /**
     * The above function is a Java POST request handler that creates a new bus
     * object based on the
     * provided data and returns a response indicating the success or failure of the
     * operation.
     * 
     * @param baseBus The `baseBus` parameter is an object of type `BaseBus` which
     *                contains the
     *                following properties:
     * @return The method is returning a `BaseResponse<Bus>`.
     */
    @PostMapping("/create")
    BaseResponse<Bus> create(
            @ModelAttribute BaseBus baseBus) {

        try {
            boolean isAccountIdValid = AccountController.accountTable.get(baseBus.accountId) != null;
            boolean isRenter = AccountController.accountTable.get(baseBus.accountId).company != null;
            Station dep = Algorithm.<Station>find(StationController.stationTable,
                    s -> s.id == baseBus.stationDepartureId);
            Station arr = Algorithm.<Station>find(StationController.stationTable,
                    s -> s.id == baseBus.stationArrivalId);

            if (!isAccountIdValid || !isRenter || dep == null || arr == null) {
                return new BaseResponse<>(false, "Data tidak valid", null);
            }

            Bus bus = new Bus(baseBus.name, baseBus.facility, new Price(baseBus.price), baseBus.capacity,
                    baseBus.busType, dep, arr, baseBus.accountId);
            System.out.println(baseBus.facility);
            busTable.add(bus);
            return new BaseResponse<>(true, "Berhasil membuat bus", bus);
        } catch (Exception e) {
            return new BaseResponse<>(false, "Gagal membuat bus", null);
        }
    }

    /**
     * This function deletes a bus from a bus table and updates the payment status
     * and buyer's balance if
     * necessary.
     * 
     * @param baseBus The `baseBus` parameter is an instance of the `deleteBus`
     *                class, which is used to
     *                specify the bus to be deleted. It contains the following
     *                attributes:
     * @return The method is returning an instance of the `BaseResponse<Bus>` class.
     */
    @PostMapping("/delete")
    BaseResponse<Bus> delete(
            @ModelAttribute deleteBus baseBus) {
        try {
            Bus bus = Algorithm.<Bus>find(busTable, b -> b.id == baseBus.busId && b.accountId == baseBus.accountId);
            if (bus == null) {
                return new BaseResponse<>(false, "Bus tidak ditemukan", null);
            }

            busTable.remove(bus);
            PaymentController.paymentTable.forEach(p -> {
                if (p.renterId == baseBus.accountId) {
                    Account buyer = Algorithm.<Account>find(AccountController.accountTable, a -> a.id == p.buyerId);
                    buyer.balance += bus.price.price * p.busSeat.size();
                    p.status = PaymentStatus.FAILED;
                }
            });

            return new BaseResponse<>(true, "Berhasil menghapus bus", bus);
        } catch (Exception e) {
            return new BaseResponse<>(false, e.getMessage(), null);
        }
    }

    /**
     * The function `addSchedule` adds a new schedule for a bus based on the
     * provided bus ID and time.
     * 
     * @param baseBus The parameter `baseBus` is an object of type
     *                `BaseAddBusSchedule` which is
     *                annotated with `@ModelAttribute`. It is used to bind the
     *                request parameters to the object
     *                properties.
     * @return The method is returning an object of type `BaseResponse<Bus>`.
     */
    @PostMapping("/addSchedule")
    BaseResponse<Bus> addSchedule(
            @ModelAttribute BaseAddBusSchedule baseBus) {
        try {
            Bus bus = Algorithm.<Bus>find(busTable, b -> b.id == baseBus.busId);
            if (bus == null) {
                return new BaseResponse<>(false, "Bus tidak ditemukan", null);
            }
            Timestamp timestamp = new Timestamp(
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(baseBus.time).getTime());
            System.out.println(timestamp);
            try {
                bus.addSchedule(timestamp);
            } catch (Exception e) {
                return new BaseResponse<>(false, e.getMessage(), null);
            }

            return new BaseResponse<>(true, "Berhasil menambahkan jadwal", bus);
        } catch (Exception e) {
            return new BaseResponse<>(false, e.getMessage(), null);
        }
    }

    /**
     * The function `deleteSchedule` is a Java method that removes a specific
     * schedule from a bus
     * object based on the provided bus ID and timestamp.
     * 
     * @param baseBus The baseBus parameter is an instance of the BaseAddBusSchedule
     *                class, which is
     *                used to represent the data for adding a bus schedule. It
     *                contains the following properties:
     * @return The method is returning an instance of the `BaseResponse<Bus>` class.
     */
    @PostMapping("/deleteSchedule")
    BaseResponse<Bus> deleteSchedule(
            @ModelAttribute BaseAddBusSchedule baseBus) {
        try {
            Bus bus = Algorithm.<Bus>find(busTable, b -> b.id == baseBus.busId);
            if (bus == null) {
                return new BaseResponse<>(false, "Bus tidak ditemukan", null);
            }

            Timestamp timestamp = new Timestamp(
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(baseBus.time).getTime());
            for (Schedule schedule : bus.schedules) {
                if (schedule.departureSchedule.equals(timestamp)) {
                    bus.schedules.remove(schedule);
                    return new BaseResponse<>(true, "Berhasil menghapus jadwal", bus);
                }
            }

            return new BaseResponse<>(false, "Jadwal tidak ditemukan", null);
        } catch (Exception e) {
            return new BaseResponse<>(false, e.getMessage(), null);
        }
    }

    /**
     * The function "getMyBus" returns a list of buses based on the provided account
     * ID.
     * 
     * @param accountId The `accountId` parameter is an integer that represents the
     *                  account ID of a
     *                  user.
     * @return The method is returning a list of Bus objects.
     */
    @GetMapping("/getMyBus")
    public List<Bus> getMyBus(
            @RequestParam int accountId) {
        return Algorithm.<Bus>collect(getJsonTable(), b -> b.accountId == accountId);
    }

    /**
     * The function returns a list of all bus objects.
     * 
     * @return The method is returning a List of Bus objects.
     */
    @GetMapping("/getAllBus")
    public List<Bus> getAllBus() {
        return getJsonTable();
    }
}

/**
 * The "deleteBus" class represents a request to delete a specific bus associated with a given account.
 */
class deleteBus {
    public int accountId, busId;

    public deleteBus(int accountId, int busId) {
        this.accountId = accountId;
        this.busId = busId;
    }
}

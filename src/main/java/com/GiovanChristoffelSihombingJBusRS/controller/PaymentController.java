package com.GiovanChristoffelSihombingJBusRS.controller;

import com.GiovanChristoffelSihombingJBusRS.*;
import com.GiovanChristoffelSihombingJBusRS.controller.model.BasePayment;
import com.GiovanChristoffelSihombingJBusRS.dbjson.JsonAutowired;
import com.GiovanChristoffelSihombingJBusRS.dbjson.JsonTable;

import retrofit2.http.Query;

import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

class BaseMakeBooking {
    public Payment payment;
    public Account account;

    public BaseMakeBooking(Payment payment, Account account) {
        this.payment = payment;
        this.account = account;
    }
}

/**
 * The PaymentController class is a Java class that handles payment-related
 * operations such as making a
 * booking, accepting a payment, and canceling a payment.
 */
@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment> {

    @JsonAutowired(value = Payment.class, filepath = "src/main/java/com/GiovanChristoffelSihombingJBusRS/json/payment.json")
    public static JsonTable<Payment> paymentTable;

    /**
     * The function returns a JsonTable object containing Payment objects.
     * 
     * @return A JsonTable object containing Payment objects is being returned.
     */
    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }

    /**
     * The function returns the string "payment page" when the GET request is made
     * to the index
     * endpoint.
     * 
     * @return The string "payment page" is being returned.
     */
    @GetMapping
    String index() {
        return "payment page";
    }

    /**
     * The `makeBooking` function in the Java code snippet is responsible for
     * creating a booking for a
     * bus based on the provided payment details.
     * 
     * @param basePayment The basePayment parameter is an object of type
     *                    BasePayment, which contains
     *                    the following properties:
     * @return The method is returning a `BaseResponse` object with the type
     *         parameter `Payment`.
     */
    @PostMapping("/makeBooking")
    // BaseResponse<Payment> makeBooking(
    BaseResponse<BaseMakeBooking> makeBooking(
            @ModelAttribute BasePayment basePayment) {
        try {
            Account buyer = Algorithm.<Account>find(AccountController.accountTable,
                    acc -> acc.id == basePayment.buyerId);
            Account renter = Algorithm.<Account>find(AccountController.accountTable,
                    acc -> acc.id == basePayment.renterId && acc.company != null);
            boolean isRenterValid = Algorithm.<Account>exists(AccountController.accountTable,
                    acc -> acc.id == basePayment.renterId && acc.company != null);
            boolean isBuyerValid = Algorithm.<Account>exists(AccountController.accountTable,
                    acc -> acc.id == basePayment.buyerId);
            boolean isBusValid = Algorithm.<Bus>exists(BusController.busTable, b -> b.id == basePayment.busId);
            Bus bus = Algorithm.<Bus>find(BusController.busTable, b -> b.id == basePayment.busId);
            boolean isBalanceEnough = Algorithm.<Account>find(AccountController.accountTable,
                    acc -> acc.id == basePayment.buyerId).balance >= bus.price.price * basePayment.busSeats.size();
            boolean isSchedule = Algorithm.<Schedule>exists(bus.schedules,
                    s -> s.departureSchedule.equals(Timestamp.valueOf(basePayment.departureDate)));

            buyer.balance -= bus.price.price * basePayment.busSeats.size();
            if (!isBusValid)
                return new BaseResponse<>(false, "Bus tidak valid", null);
            if (!isBuyerValid)
                return new BaseResponse<>(false, "Pembeli tidak valid", null);
            if (!isBalanceEnough)
                return new BaseResponse<>(false, "Saldo tidak cukup", null);
            if (!isSchedule)
                return new BaseResponse<>(false, "Jadwal tidak tersedia", null);

            boolean bookSeat = Payment.makeBooking(Timestamp.valueOf(basePayment.departureDate), basePayment.busSeats,
                    bus);
            if (!bookSeat)
                return new BaseResponse<>(false, "Booking tidak berhasil", null);

            Payment payment = new Payment(buyer.id, renter.id, basePayment.busId,
                    basePayment.busSeats, Timestamp.valueOf(basePayment.departureDate));
            paymentTable.add(payment);

            return new BaseResponse<>(true, "Berhasil membuat booking", new BaseMakeBooking(payment, buyer));
        } catch (Exception e) {
            return new BaseResponse<>(false, "Gagal membuat booking", null);
        }
    }

    /**
     * The above function accepts a payment by updating its status to "SUCCESS" and
     * returns a response
     * indicating whether the operation was successful or not.
     * 
     * @param id The "id" parameter is an integer that represents the ID of the
     *           payment that needs to
     *           be accepted.
     * @return The method is returning a `BaseResponse` object with the type
     *         parameter `Payment`.
     */
    @PostMapping("/{id}/accept")
    public BaseResponse<Payment> accept(@PathVariable int id) {
        try {
            Payment payment = Algorithm.<Payment>find(paymentTable, p -> p.id == id);
            if (payment == null) {
                return new BaseResponse<>(false, "Payment tidak ditemukan", null);
            }

            payment.status = Invoice.PaymentStatus.SUCCESS;
            return new BaseResponse<>(true, "Payment disetujui", payment);
        } catch (Exception e) {
            return new BaseResponse<Payment>(false, "Gagal melakukan operasi", null);
        }
    }

    /**
     * The above function cancels a payment by updating its status to "FAILED".
     * 
     * @param id The "id" parameter is an integer that represents the ID of the
     *           payment that needs to
     *           be canceled.
     * @return The method is returning a `BaseResponse` object with the type
     *         parameter `Payment`.
     */
    @PostMapping("/{id}/cancel")
    public BaseResponse<Payment> cancel(@PathVariable int id) {
        try {
            Payment payment = Algorithm.<Payment>find(paymentTable, p -> p.id == id);
            if (payment == null) {
                return new BaseResponse<>(false, "Payment tidak ditemukan", null);
            }

            payment.status = Invoice.PaymentStatus.FAILED;
            return new BaseResponse<>(true, "Payment dibatalkan", payment);
        } catch (Exception e) {
            return new BaseResponse<>(false, "Gagal melakukan operasi", null);
        }
    }

    @GetMapping("/getAllPayment")
    public List<Payment> getAllPayment() {
        return getJsonTable();
    }
}

package com.GiovanChristoffelSihombingJBusRS.controller;

import com.GiovanChristoffelSihombingJBusRS.*;
import com.GiovanChristoffelSihombingJBusRS.controller.model.BasePayment;
import com.GiovanChristoffelSihombingJBusRS.dbjson.JsonAutowired;
import com.GiovanChristoffelSihombingJBusRS.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment> {

    @JsonAutowired(value = Payment.class, filepath = "src/main/java/com/GiovanChristoffelSihombingJBusRS/json/payment.json")
    public static JsonTable<Payment> paymentTable;

    public JsonTable<Payment> getJsonTable(){
        return paymentTable;
    }

    @GetMapping
    String index() { return "payment page"; }

    @PostMapping("/makeBooking")
    BaseResponse<Payment> makeBooking(
//            @RequestParam int buyerId,
//            @RequestParam int renterId,
//            @RequestParam int busId,
//            @RequestParam List<String> busSeats,
//            @RequestParam String departureDate
            @ModelAttribute BasePayment basePayment
            ) {
        try {
            boolean isBuyerValid = Algorithm.<Account>exists(AccountController.accountTable, acc -> acc.id == basePayment.buyerId);
            boolean isBusValid = Algorithm.<Bus>exists(BusController.busTable, b -> b.id == basePayment.busId);
            Bus bus = Algorithm.<Bus>find(BusController.busTable, b -> b.id == basePayment.busId);
            boolean isBalanceEnough = Algorithm.<Account>find(AccountController.accountTable, acc -> acc.id == basePayment.buyerId).balance >= bus.price.price * basePayment.busSeats.size();
            boolean isSchedule = Algorithm.<Schedule>exists(bus.schedules, s -> s.departureSchedule.equals(Timestamp.valueOf(basePayment.departureDate)));

            if(!isBusValid) return new BaseResponse<>(false, "Bus tidak valid", null);
            if(!isBuyerValid) return new BaseResponse<>(false, "Pembeli tidak valid", null);
            if(!isBalanceEnough) return new BaseResponse<>(false, "Saldo tidak cukup", null);
            if(!isSchedule) return new BaseResponse<>(false, "Jadwal tidak tersedia", null);
            
            Payment payment = new Payment(basePayment.buyerId, basePayment.renterId, basePayment.busId, basePayment.busSeats, Timestamp.valueOf(basePayment.departureDate));
            paymentTable.add(payment);
            return new BaseResponse<>(true, "Berhasil membuat booking", payment);
        } catch (Exception e) {
            return new BaseResponse<>(false, "Gagal membuat booking", null);
        }
    }

    @PostMapping("/{id}/accept")
    public BaseResponse<Payment> accept(@PathVariable int id){
        try{
            Payment payment = Algorithm.<Payment>find(paymentTable, p -> p.id == id);
            if(payment == null){
                return new BaseResponse<>(false, "Payment tidak ditemukan", null);
            }

            payment.status = Invoice.PaymentStatus.SUCCESS;
            return new BaseResponse<>(true, "Payment disetujui", payment);
        } catch (Exception e){
            return new BaseResponse<Payment>(false, "Gagal melakukan operasi", null);
        }
    }

    @PostMapping("/{id}/cancel")
    public BaseResponse<Payment> cancel (@PathVariable int id){
        try {
            Payment payment = Algorithm.<Payment>find(paymentTable, p -> p.id == id);
            if(payment == null){
                return new BaseResponse<>(false, "Payment tidak ditemukan", null);
            }

            payment.status = Invoice.PaymentStatus.FAILED;
            return new BaseResponse<>(true, "Payment dibatalkan", payment);
        } catch (Exception e){
            return new BaseResponse<>(false, "Gagal melakukan operasi", null);
        }
    }
}

package com.GiovanChristoffelSihombingJBusRS.controller;

import com.GiovanChristoffelSihombingJBusRS.Account;
import com.GiovanChristoffelSihombingJBusRS.Algorithm;
import com.GiovanChristoffelSihombingJBusRS.Renter;
import com.GiovanChristoffelSihombingJBusRS.dbjson.JsonAutowired;
import com.GiovanChristoffelSihombingJBusRS.dbjson.JsonTable;

import java.security.MessageDigest;

import org.springframework.web.bind.annotation.*;





@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account>
{
    @JsonAutowired(value = Account.class, filepath = "src/main/java/com/GiovanChristoffelSihombingJBusRS/json/account.json")
    public static JsonTable<Account> accountTable;

    public JsonTable<Account> getJsonTable(){
        return accountTable;
    }

    public static String hashPassword(String password){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for(byte b : digest){
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping
    String index() { return "account page"; }

    @PostMapping("/register")
    BaseResponse<Account> register
            (
                    @RequestParam String name,
                    @RequestParam String email,
                    @RequestParam String password
            )
    {
        try {
            Account account = new Account(name, email, password);

            if(name.isBlank() || !account.validate() || Algorithm.<Account>exists(accountTable, b -> b.email.equals(email))){
                return new BaseResponse<>(false, "Gagal register", null);
            }
            
            account.password = hashPassword(password);
            accountTable.add(account);
            return new BaseResponse<>(true, "Berhasil register", account);
        } catch (Exception e) {
            return new BaseResponse<>(false, "Gagal register", null);
        }
    }

    @PostMapping("/login")
    BaseResponse<Account> login(
        @RequestParam String email,
        @RequestParam String password
    ) {
        try {
            String hashedPassword = hashPassword(password);
            Account account = Algorithm.<Account>find(accountTable, b -> b.email.equals(email) && b.password.equals(hashedPassword));
            if(account == null){
                return new BaseResponse<>(false, "Akun tidak ditemukan", null);
            }

            return new BaseResponse<>(true, "Berhasil login", account);
        } catch (Exception e) {
            return new BaseResponse<>(false, "Gagal login", null);
        }
    }

    @PostMapping("/{id}/registerRenter")
    BaseResponse<Renter> registerRenter(
        @PathVariable int id,
        @RequestParam String companyName,
        @RequestParam String address,
        @RequestParam String phoneNumber
    ) {
        try {
            Account account = getById(id);
            if(account == null || account.company != null){
                return new BaseResponse<>(false, "Gagal register renter", null);
            }

            Renter renter = new Renter(companyName, address, phoneNumber);
            account.company = renter;
            return new BaseResponse<>(true, "Berhasil register renter", renter);
        } catch (Exception e) {
            return new BaseResponse<>(false, "Gagal register renter", null);
        }
    }

    @PostMapping("/{id}/topUp")
    BaseResponse<Double> topUp(
        @PathVariable int id,
        @RequestParam double amount
    ) {
        try {
            Account account = getById(id);
            if(account == null || amount <= 0){
                return new BaseResponse<>(false, "Gagal top up", account.balance);
            }

            account.balance += amount;
            return new BaseResponse<>(true, "Berhasil top up", account.balance);
        } catch (Exception e) {
            return new BaseResponse<>(false, "Gagal top up", null);
        }
    }
}

package com.GiovanChristoffelSihombingJBusRS.controller;

import com.GiovanChristoffelSihombingJBusRS.Account;
import com.GiovanChristoffelSihombingJBusRS.Algorithm;
import com.GiovanChristoffelSihombingJBusRS.Renter;
import com.GiovanChristoffelSihombingJBusRS.controller.model.BaseAccount;
import com.GiovanChristoffelSihombingJBusRS.controller.model.BaseAccountRegister;
import com.GiovanChristoffelSihombingJBusRS.controller.model.BaseCompany;
import com.GiovanChristoffelSihombingJBusRS.dbjson.JsonAutowired;
import com.GiovanChristoffelSihombingJBusRS.dbjson.JsonTable;

import java.security.MessageDigest;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account> {
    @JsonAutowired(value = Account.class, filepath = "src/main/java/com/GiovanChristoffelSihombingJBusRS/json/account.json")
    public static JsonTable<Account> accountTable;

    public JsonTable<Account> getJsonTable() {
        return accountTable;
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping
    String index() {
        return "account page";
    }

    @PostMapping("/register")
    BaseResponse<Account> register
            (
//                    @RequestParam String name,
//                    @RequestParam String email,
//                    @RequestParam String password
                    @ModelAttribute BaseAccountRegister baseAccount
            ) {
        try {
            Account account = new Account(baseAccount.name, baseAccount.email, baseAccount.password);

            if(baseAccount.name.isBlank() || !account.validate() || Algorithm.<Account>exists(accountTable, b -> b.email.equals(baseAccount.email))){
                return new BaseResponse<>(false, "Gagal register", null);
            }

            account.password = hashPassword(baseAccount.password);
            accountTable.add(account);
            return new BaseResponse<>(true, "Berhasil register", account);
        } catch (Exception e) {
            return new BaseResponse<>(false, "Gagal register", null);
        }
    }

    @PostMapping("/login")
    BaseResponse<Account> login(
//            @RequestParam String email,
//            @RequestParam String password
            @ModelAttribute BaseAccountLogin baseAccount
    ) {
        try {
//            String hashedPassword = hashPassword(baseAccount.password);
            String hashedPassword = hashPassword(baseAccount.password);
            Account account = Algorithm.<Account>find(accountTable, b -> b.email.equals(baseAccount.email) && b.password.equals(hashedPassword));
            if (account == null) {
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
//             @RequestParam String companyName,
//             @RequestParam String address,
//             @RequestParam String phoneNumber
            @ModelAttribute BaseCompany baseCompany
    ) {
        try {
            Account account = getById(id);
            if (account == null || account.company != null) {
                return new BaseResponse<>(false, "Gagal register renter", null);
            }

            Renter renter = new Renter(baseCompany.companyName, baseCompany.phoneNumber, baseCompany.address);
//            Renter renter = new Renter(companyName, phoneNumber,address);
            account.company = renter;

            return new BaseResponse<>(true, "Berhasil register renter", renter);
        } catch (Exception e) {
            return new BaseResponse<>(false, "Gagal register renter", null);
        }
    }

    @PostMapping("/{id}/topUp")
    BaseResponse<Double> topUp(
            @PathVariable int id,
//             @RequestParam double amount
            @ModelAttribute BaseAccount baseAccount
    ) {
        try {
            Account account = getById(id);
            if (account == null || baseAccount.balance <= 0) {
                return new BaseResponse<>(false, "Gagal top up", baseAccount.balance);
            }

            account.balance += baseAccount.balance;
            return new BaseResponse<>(true, "Berhasil top up", account.balance);
        } catch (Exception e) {
            return new BaseResponse<>(false, "Gagal top up", null);
        }
    }
}

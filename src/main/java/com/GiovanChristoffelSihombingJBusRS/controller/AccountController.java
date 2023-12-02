package com.GiovanChristoffelSihombingJBusRS.controller;

import com.GiovanChristoffelSihombingJBusRS.Account;
import com.GiovanChristoffelSihombingJBusRS.Algorithm;
import com.GiovanChristoffelSihombingJBusRS.Renter;
import com.GiovanChristoffelSihombingJBusRS.controller.model.BaseAccount;
import com.GiovanChristoffelSihombingJBusRS.controller.model.BaseAccountLogin;
import com.GiovanChristoffelSihombingJBusRS.controller.model.BaseAccountRegister;
import com.GiovanChristoffelSihombingJBusRS.controller.model.BaseCompany;
import com.GiovanChristoffelSihombingJBusRS.dbjson.JsonAutowired;
import com.GiovanChristoffelSihombingJBusRS.dbjson.JsonTable;

import java.security.MessageDigest;

import org.springframework.web.bind.annotation.*;

// The `@RestController` annotation is used to indicate that the class is a RESTful controller. It
// combines the `@Controller` and `@ResponseBody` annotations, which means that the methods in this
// class will handle HTTP requests and return the response directly as the body of the response,
// instead of relying on a view resolver to render a view.
@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController<Account> {
    @JsonAutowired(value = Account.class, filepath = "src/main/java/com/GiovanChristoffelSihombingJBusRS/json/account.json")
    public static JsonTable<Account> accountTable;

    /**
     * The function returns a JsonTable object containing Account data.
     * 
     * @return A JsonTable object of type Account is being returned.
     */
    public JsonTable<Account> getJsonTable() {
        return accountTable;
    }

    /**
     * The function takes a password as input and returns its MD5 hash value as a
     * string.
     * 
     * @param password The parameter "password" is a String that represents the
     *                 password that needs to
     *                 be hashed.
     * @return The method is returning a hashed version of the input password as a
     *         string.
     */
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

    /**
     * The above function is a GET request handler that returns the string "account page".
     * 
     * @return The string "account page" is being returned.
     */
    @GetMapping
    String index() {
        return "account page";
    }

    /**
     * The function handles the registration process by creating a new Account object, validating the
     * account details, and adding the account to the accountTable.
     * 
     * @param baseAccount The `baseAccount` parameter is an instance of the `BaseAccountRegister`
     * class. It is annotated with `@ModelAttribute`, which means that the values for its properties
     * will be obtained from the request parameters or form data.
     * @return The method is returning a `BaseResponse<Account>`.
     */
    @PostMapping("/register")
    BaseResponse<Account> register(
            @ModelAttribute BaseAccountRegister baseAccount) {
        try {
            Account account = new Account(baseAccount.name, baseAccount.email, baseAccount.password);

            if (baseAccount.name.isBlank() || !account.validate()
                    || Algorithm.<Account>exists(accountTable, b -> b.email.equals(baseAccount.email))) {
                return new BaseResponse<>(false, "Gagal register", null);
            }

            account.password = hashPassword(baseAccount.password);
            accountTable.add(account);
            return new BaseResponse<>(true, "Berhasil register", account);
        } catch (Exception e) {
            return new BaseResponse<>(false, "Gagal register", null);
        }
    }

    /**
     * The function handles the login process by checking the provided email and password against a
     * hashed password in the account table and returns a response indicating whether the login was
     * successful or not.
     * 
     * @param baseAccount The `baseAccount` parameter is an object of type `BaseAccountLogin` which is
     * used to represent the login credentials of a user. It contains the following properties:
     * @return The method is returning a BaseResponse object.
     */
    @PostMapping("/login")
    BaseResponse<Account> login(
            @ModelAttribute BaseAccountLogin baseAccount) {
        try {
            String hashedPassword = hashPassword(baseAccount.password);
            Account account = Algorithm.<Account>find(accountTable,
                    b -> b.email.equals(baseAccount.email) && b.password.equals(hashedPassword));
            if (account == null) {
                return new BaseResponse<>(false, "Akun tidak ditemukan", null);
            }

            return new BaseResponse<>(true, "Berhasil login", account);
        } catch (Exception e) {
            return new BaseResponse<>(false, "Gagal login", null);
        }
    }

    /**
     * This function registers a renter for a specific account by creating a new Renter object and
     * assigning it to the account's company field.
     * 
     * @param id The id parameter is an integer that represents the account id. It is used to identify
     * the account for which the renter is being registered.
     * @param baseCompany The `baseCompany` parameter is an object of type `BaseCompany` which is used
     * to store the details of the company being registered as a renter. It contains the following
     * properties:
     * @return The method is returning a `BaseResponse<Renter>`.
     */
    @PostMapping("/{id}/registerRenter")
    BaseResponse<Renter> registerRenter(
            @PathVariable int id,
            @ModelAttribute BaseCompany baseCompany) {
        try {
            Account account = getById(id);
            if (account == null || account.company != null) {
                return new BaseResponse<>(false, "Gagal register renter", null);
            }

            Renter renter = new Renter(baseCompany.companyName, baseCompany.phoneNumber, baseCompany.address);
            // Renter renter = new Renter(companyName, phoneNumber,address);
            account.company = renter;

            return new BaseResponse<>(true, "Berhasil register renter", renter);
        } catch (Exception e) {
            return new BaseResponse<>(false, "Gagal register renter", null);
        }
    }

    /**
     * The `topUp` function in Java is used to add a specified amount to the balance of an account
     * identified by its ID.
     * 
     * @param id The id parameter is a path variable that represents the account id. It is used to
     * identify the account for which the top-up operation is being performed.
     * @param baseAccount The `baseAccount` parameter is a model attribute that represents the account
     * details for the top-up operation. It contains the following properties:
     * @return The method is returning a `BaseResponse<Double>`.
     */
    @PostMapping("/{id}/topUp")
    BaseResponse<Double> topUp(
            @PathVariable int id,
            // @RequestParam double amount
            @ModelAttribute BaseAccount baseAccount) {
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

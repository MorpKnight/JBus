package GiovanChristoffelSihombingJBusRS;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Write a description of class Account here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Account extends Serializable
{
    // instance variables - replace the example below with your own
    public String email;
    public String name;
    public String password;
    public static final String REGEX_PASSWORD = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])\\S{8,}$";
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,}$";
    
    public Account(String name, String email, String password) {
        super();
        this.email = email;
        this.name = name;
        this.password = password;
    }
    
    /**
     * The toString() function mengembalikan string yang merepresentasikan objek account.
     * 
     * @return toString() method mengembalikan string yang merepresentasikan objek account, termasuk id akun, email, nama, dan password.
     */
    public String toString(){
        return ("Account Id: " + this.id + "\nemail: " + this.email + "\nname: " + this.name + "\npassword: " + this.password);
    }

    public boolean validate(){
        Pattern email_pattern = Pattern.compile(REGEX_EMAIL);
        Pattern password_pattern = Pattern.compile(REGEX_PASSWORD);
        Matcher email_match = email_pattern.matcher(this.email);
        Matcher password_match = password_pattern.matcher(this.password);
        return email_match.find() && password_match.find();
    }
}

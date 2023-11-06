package GiovanChristoffelSihombingJBusRS;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Write a description of class Renter here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Renter extends Serializable
{
    public String address;
    public String companyName; 
    public String phoneNumber;
    // regex phone name
    private static final String REGEX_NAME = "^^[A-Z][a-zA-Z0-9_]{4,20}$";
    private static final String REGEX_PHONE = "^[0-9]{9,12}$";
    
    public Renter(String companyName){
        super();
        this.companyName = companyName;
        this.address = "";
        this.phoneNumber = "";
    }

    public Renter(String companyName, String phoneNumber){
        super();
        this.companyName = companyName;
        this.address = "";
        this.phoneNumber = phoneNumber;
    }
    
    public Renter(String companyName, String phoneNumber, String address){
        super();
        this.companyName = companyName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public boolean validate(){
        Pattern name_pattern = Pattern.compile(REGEX_NAME);
        Pattern phone_pattern = Pattern.compile(REGEX_PHONE);
        Matcher name_match = name_pattern.matcher(this.companyName);
        Matcher phone_match = phone_pattern.matcher(this.phoneNumber);
        boolean name_found = name_match.find();
        boolean phone_found = phone_match.find();

        return name_found && phone_found;
    }
}

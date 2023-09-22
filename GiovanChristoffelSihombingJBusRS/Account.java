package GiovanChristoffelSihombingJBusRS;


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
    
    public Account(int id, String email, String name, String password) {
        super(id);
        this.email = email;
        this.name = name;
        this.password = password;
    }
}

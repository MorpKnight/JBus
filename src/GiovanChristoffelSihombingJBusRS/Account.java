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
    
    public Account(int id, String name, String email, String password) {
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
}

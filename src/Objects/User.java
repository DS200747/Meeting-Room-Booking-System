package Objects;

import java.util.ArrayList;

public class User {

    String name;
    String email;
    String password;
    boolean staff;

    public User(String name, String email, String password, boolean staff) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.staff = staff;
    }

    @Override
    public String toString() {
        return name + ", " + email + ", " + password + ", " + staff;
    }

    // <editor-fold defaultstate="collapsed" desc="Getters">
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isStaff() {
        return staff;
    }

// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Setters">
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStaff(boolean staff) {
        this.staff = staff;
    }
    // </editor-fold>



}

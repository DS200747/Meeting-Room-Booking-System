
package Objects;


public class Staff extends User{
    
    String title;

    public Staff(String title, String name, String email, String password, boolean staff) {
        super(name, email, password, staff);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    
    
}

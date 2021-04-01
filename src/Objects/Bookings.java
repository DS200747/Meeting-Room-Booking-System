package Objects;

public class Bookings {

    String user;
    int room;
    String time;
    String date;
    String order;
    double foodTime;
    String rescources;

    public Bookings(String user, int room, String time, String date, String order, double foodTime, String rescources) {
        this.user = user;
        this.room = room;
        this.time = time;
        this.date = date;
        this.order = order;
        this.foodTime = foodTime;
        this.rescources = rescources;
    }

    @Override
    public String toString() {
        return user + ", " + room + ", " + time + ", " + date + ", " + order + ", " + foodTime + ", " + rescources;
    }
    
    

    // <editor-fold defaultstate="collapsed" desc="Getters">
    public String getUser() {
        return user;
    }

    public int getRoom() {
        return room;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public double getFoodTime() {
        return foodTime;
    }

    public String getRescources() {
        return rescources;
    }

    public String getOrder() {
        return order;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Setters">
    public void setUser(String user) {
        this.user = user;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setFoodTime(double food) {
        this.foodTime = foodTime;
    }

    public void setRescources(String rescources) {
        this.rescources = rescources;
    }

    public void setOrder(String order) {
        this.order = order;
    }
    
    // </editor-fold>

    //-----------------------
}

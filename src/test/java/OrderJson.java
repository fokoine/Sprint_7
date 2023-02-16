import java.util.ArrayList;

public class OrderJson {
    public OrderJson(String firstName, String lastName, String address, int metroStation, String phone, int rentTime, String deliveryDate, String comment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
    }
    public String firstName;
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String lastName;
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String address;
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int metroStation;
    public int getMetroStation() {
        return metroStation;
    }
    public void setMetroStation(int metroStation) {
        this.metroStation = metroStation;
    }
    public String phone;
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public int rentTime;
    public int getRentTime() {
        return rentTime;
    }
    public void setRentTime(int rentTime) {
        this.rentTime = rentTime;
    }
    public String deliveryDate;
    public String getDeliveryDate() {
        return deliveryDate;
    }
    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    public String comment;
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    private ArrayList<String> color;
    public ArrayList<String> getColor() {
        return color;
    }
    public void setColor(ArrayList<String> color, String settingColour) {
        this.color = color;
        color.add(0,settingColour);
    }
}

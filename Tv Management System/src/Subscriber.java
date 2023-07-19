import java.io.Serializable;

public class Subscriber implements Serializable{
    private String FirstName;
    private  String LAstName;
    private String City;
    private int phone;

    @Override
    public String toString() {
        return "Subscriber{" +
                "FirstName='" + FirstName + '\'' +
                ", LAstName='" + LAstName + '\'' +
                ", City='" + City + '\'' +
                ", phone=" + phone +
                '}';
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLAstName() {
        return LAstName;
    }

    public void setLAstName(String LAstName) {
        this.LAstName = LAstName;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public Subscriber(String firstName, String LAstName, String city, int phone) {
        FirstName = firstName;
        this.LAstName = LAstName;
        City = city;
        this.phone = phone;
    }
}

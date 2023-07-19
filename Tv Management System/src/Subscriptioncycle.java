import java.io.Serializable;

public class Subscriptioncycle implements  Serializable{
    private String startDate;
    private String endDate;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Subscriptioncycle{" +
                "startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }

    public Subscriptioncycle(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}

import java.io.Serializable;

public class Subscription implements  Serializable{
    private int installFee;
    private int noTv;
    private Subscriber subscriber;
    private Subscriptioncycle cycle;
    private String datee;
    private int totalFee;

    public Subscription(int noTv, Subscriber subscriber, Subscriptioncycle cycle, String datee) {
        this.noTv = noTv;
        this.subscriber = subscriber;
        this.cycle = cycle;
        this.datee = datee;
        this.installFee=noTv*10;
    }

    public int getNoTv() {
        return noTv;
    }

    public void setNoTv(int noTv) {
        this.noTv = noTv;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public Subscriptioncycle getCycle() {
        return cycle;
    }

    public void setCycle(Subscriptioncycle cycle) {
        this.cycle = cycle;
    }

    public String getDatee() {
        return datee;
    }

    public void setDatee(String datee) {
        this.datee = datee;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "installFee=" + installFee +
                ", noTv=" + noTv +
                ", subscriber=" + subscriber +
                ", cycle=" + cycle +
                ", datee='" + datee +
                '}';
    }

    public int getTotalFee() {
        totalFee=installFee+5;
        return totalFee;
    }


}

import java.time.LocalTime;

public class Cake {

    private final String cakeType;
    private final String customerID;
    private final LocalTime timeOrdered;
    private LocalTime timeDelivered;

    public Cake(String cakeType, String customerID, LocalTime timeOrdered) {
        this.cakeType = cakeType;
        this.customerID = customerID;
        this.timeOrdered = timeOrdered;
		this.timeDelivered = null;
    }

    public String getType() {
        return this.cakeType;
    }

    public String getOwnerID() {
        return this.customerID;
    }

    public LocalTime getTimeOrdered() {
        return this.timeOrdered;
    }

    public LocalTime getTimeDelivered() {
        return this.timeDelivered;
    }

    public Boolean getDelivered() {
        return this.timeDelivered != null;
    }

    public void setTimeDelivered(LocalTime currentTime) {
        this.timeDelivered = currentTime;
    }

    @Override
    public String toString() {
        return "Cake: "
                + getType()
                + ", time ordered: "
                + getTimeOrdered()
                + ", delivered: "
                + getDelivered();
    }

}

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Customer {

    private final String name;
    private static String customerID;
    private final List<Cake> cakeHistory;

    public Customer(String name) {
        this.name = name;
        customerID = UUID.randomUUID().toString();
        this.cakeHistory = new ArrayList<Cake>();
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getName() {
        return this.name;
    }

    public List<Cake> getCakeHistory() {
        return this.cakeHistory;
    }

    public void addCakeToHistory(Cake cake) {
        cakeHistory.add(cake);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (var cake : cakeHistory) {
            string.append(cake.toString());
        }

        return string.toString();
    }
}

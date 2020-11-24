import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class CakeShop {

    private final List<Cake> orderedCakes;
    private final List<Cake> deliveredCakes;
    private final List<Customer> customers;

    public CakeShop() {
        this.orderedCakes = new ArrayList<Cake>();
        this.deliveredCakes = new ArrayList<Cake>();
        this.customers = new ArrayList<Customer>();
    }

    public void registerCustomer(String name) {
        var customer = new Customer(name);
        if (!customers.contains(customer)) {
            customers.add(customer);
        }
    }

    public void orderCake(String customerID, String cakeType, LocalTime localTime) {
        for (var customer : customers) {
            if (customer.getCustomerID().equals(customerID)) {
                var cake = new Cake(customerID, cakeType, localTime);
                orderedCakes.add(cake);
                customer.addCakeToHistory(cake);
            }
        }
    }

    public void deliverCake(String customerID, String cakeType, LocalTime localTime) {
        for (var cake : orderedCakes) {
            if (cake.getOwnerID().equals(customerID) && cake.getType().equals(cakeType)) {
                orderedCakes.remove(cake);
                deliveredCakes.add(cake);
                cake.setTimeDelivered(localTime);
                return;
            }

            throw new IllegalStateException("No cake exists");
        }
    }

    public String getCustomerInfo(String customerID) {
        for (var customer : customers) {
            if (customer.getCustomerID().equals(customerID)) {
                return "Customer name: "
                        + customer.getName()
                        + ", cakes ordered/delivered: "
                        + customer.toString();
            }
        }
        return "Customer could not be found!";
    }


    public List<Customer> getCustomers() {
        return customers.stream()
                .sorted(Comparator.comparing(Customer::getName))
                .collect(Collectors.toList());
    }

    public String getOldestCake() {
        long result = 0;
        for (var cake : deliveredCakes) {
            long diff = cake.getTimeOrdered().until(cake.getTimeDelivered(), ChronoUnit.MILLIS);
            if (diff > result) {
                result = diff;
            }
        }

        return "The oldest cake is this old: "
                + result
                + " Hours old";
    }
}

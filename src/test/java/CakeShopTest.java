import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import java.time.LocalTime;

public class CakeShopTest {

    @Test
    public void testConstructor() {
        new CakeShop();
    }

    @Test
    public void testCake() {
        new Cake("Bløtkake", "", LocalTime.now());

        try {
            new Cake("Bløtkake", "2", LocalTime.now());
            fail("CustomerID needs to be generated!");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        try {
            new Cake("Bløtkake", "", LocalTime.MIDNIGHT);
            fail("Time must be set to now!");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }


        assertThrows(IllegalArgumentException.class,
                () -> new Cake("Bløtkake", "", LocalTime.now()));


        var cake1 = new Cake("Bløtkake", "", LocalTime.now());
        cake1.setTimeDelivered(LocalTime.now());
        assertTrue(cake1.getDelivered());
    }

    @Test
    public void testCustomer() {
        new Customer("Ida");

        try {
            var customer1 = new Customer("Petter");
            customer1.addCakeToHistory(new Cake("Pepper", "", LocalTime.now()));
            fail("Pepper is not a valid cake!");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        assertThrows(IllegalArgumentException.class, () -> new Customer("Petter"));

        var customer1 = new Customer("Bendik");
        customer1.addCakeToHistory(new Cake("Bløtkake", "", LocalTime.MIDNIGHT));
        assertFalse(customer1.getCakeHistory().isEmpty());
    }
}

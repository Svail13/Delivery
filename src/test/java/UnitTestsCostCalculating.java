import jdk.jfr.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UnitTestsCostCalculating {

    @Test
    @Tag("Positive")
    @DisplayName("Checking minimal cost delivery")
    void testMinimalDelivery() {
        Delivery delivery = new Delivery(1, CargoDimension.SMALL, false, ServiceWorkload.NORMAL);
        assertEquals(400, delivery.calculateDeliveryCost());

        Delivery delivery1 = new Delivery(30, CargoDimension.SMALL, false, ServiceWorkload.INCREASED);
        assertEquals(400, delivery1.calculateDeliveryCost());

        Delivery delivery2 = new Delivery(0, CargoDimension.LARGE, true, ServiceWorkload.VERY_HIGH);
        assertEquals(880, delivery2.calculateDeliveryCost());

    }

    @Test
    @Tag("Positive")
    @DisplayName("Checking cost delivery for distance 1-2km")
    void testDistanceDelivery2km() {
        Delivery delivery = new Delivery(2, CargoDimension.LARGE, true, ServiceWorkload.VERY_HIGH);
        assertEquals(880, delivery.calculateDeliveryCost());



    }

    @Test
    @Tag("Positive")
    @DisplayName("Checking cost delivery for distance 3-10km")
    void testDistanceDelivery10km() {
        Delivery delivery = new Delivery(3, CargoDimension.LARGE, true, ServiceWorkload.VERY_HIGH);
        assertEquals(960, delivery.calculateDeliveryCost());

        Delivery delivery1 = new Delivery(10, CargoDimension.LARGE, true, ServiceWorkload.VERY_HIGH);
        assertEquals(960, delivery1.calculateDeliveryCost());
    }

    @Test
    @Tag("Positive")
    @DisplayName("Checking cost delivery for distance 11-30km")
    void testDistanceDelivery30km() {
        Delivery delivery = new Delivery(11, CargoDimension.LARGE, true, ServiceWorkload.NORMAL);
        assertEquals(700, delivery.calculateDeliveryCost());

        Delivery delivery1 = new Delivery(30, CargoDimension.LARGE, true, ServiceWorkload.NORMAL);
        assertEquals(700, delivery1.calculateDeliveryCost());
    }

    @Test
    @Tag("Positive")
    @DisplayName("Checking cost delivery for distance >= 31km")
    void testDistanceDeliveryMoreThan30km() {
        Delivery delivery = new Delivery(31, CargoDimension.LARGE, false, ServiceWorkload.NORMAL);
        assertEquals(500, delivery.calculateDeliveryCost());

    }

    @Test
    @Tag("Negative")
    @DisplayName("Checking delivery for negative distance")
    void testCalculatingDeliveryNegativeDistance() {
        Delivery delivery = new Delivery(-1, CargoDimension.SMALL, true, ServiceWorkload.NORMAL);
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                delivery::calculateDeliveryCost
        );
        assertEquals("destinationDistance should be a positive number!", exception.getMessage());
    }


    @Test
    @Tag("Positive")
    @DisplayName("Checking calculating for small size")
    void testCalculatingDeliverySmallSize() {
        Delivery delivery = new Delivery(31, CargoDimension.SMALL, false, ServiceWorkload.INCREASED);
        assertEquals(480, delivery.calculateDeliveryCost());
    }

    @Test
    @Tag("Positive")
    @DisplayName("Checking calculating for large size")
    void testCalculatingDeliveryLargeSize() {
        Delivery delivery = new Delivery(31, CargoDimension.LARGE, false, ServiceWorkload.NORMAL);
        assertEquals(500, delivery.calculateDeliveryCost());
    }

    @Test
    @Tag("Positive")
    @DisplayName("Checking calculating for fragile package")
    void testCalculatingDeliveryFragilePackage() {
        Delivery delivery = new Delivery(30, CargoDimension.SMALL, true, ServiceWorkload.NORMAL);
        assertEquals(600, delivery.calculateDeliveryCost());
    }

    @Test
    @Tag("Negative")
    @DisplayName("Checking impossibility delivery for fragile package on distance more than 30km")
    void testCalculatingDeliveryFragilePackage31km() {
        Delivery delivery = new Delivery(31, CargoDimension.SMALL, true, ServiceWorkload.NORMAL);
        Throwable exception = assertThrows(
                UnsupportedOperationException.class,
                delivery::calculateDeliveryCost
        );
        assertEquals("Fragile cargo cannot be delivered for the distance more than 30", exception.getMessage());
    }


    @Test
    @Tag("Positive")
    @DisplayName("Checking calculating for normal workload = 1")
    void testCalculatingDeliveryNormalWorkload() {
        Delivery delivery = new Delivery(2, CargoDimension.LARGE, true, ServiceWorkload.NORMAL);
        assertEquals(550, delivery.calculateDeliveryCost());
    }

    @Test
    @Tag("Positive")
    @DisplayName("Checking calculating for increased workload = 1.2")
    void testCalculatingDeliveryIncreasedWorkload() {
        Delivery delivery = new Delivery(2, CargoDimension.LARGE, true, ServiceWorkload.INCREASED);
        assertEquals(660, delivery.calculateDeliveryCost());

    }

    @Test
    @Tag("Positive")
    @DisplayName("Checking calculating for high workload = 1.4")
    void testCalculatingDeliveryHighWorkload() {
        Delivery delivery = new Delivery(2, CargoDimension.LARGE, true, ServiceWorkload.HIGH);
        assertEquals(770, delivery.calculateDeliveryCost());

    }

    @Test
    @Tag("Positive")
    @DisplayName("Checking calculating for very high workload = 1.6")
    void testCalculatingDeliveryVeryHighWorkload() {
        Delivery delivery = new Delivery(2, CargoDimension.LARGE, true, ServiceWorkload.VERY_HIGH);
        assertEquals(880, delivery.calculateDeliveryCost());

    }
}


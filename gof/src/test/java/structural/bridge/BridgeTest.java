package structural.bridge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BridgeTest {

    @Test
    void elitePreferenceAssignesElitePassengersFirst() {
        FlightPrioritizer flightPrioritizer = new FlightPrioritizerElite();
        FlightAssigner flightAssigner = new FlightAssigner(flightPrioritizer);

        assertEquals("Elite", flightAssigner.whoGoesFirst());
    }

    @Test
    void ticketPurchaseTimePreferenceAssignesWhoBoughtFirst() {
        FlightPrioritizer flightPrioritizer = new FlightPrioritizerTicketPurchaseTime();
        FlightAssigner flightAssigner = new FlightAssigner(flightPrioritizer);

        assertEquals("Early Bird", flightAssigner.whoGoesFirst());
    }

    @Test
    void adapterDecidesWhoGoesFirst() {
        FlightAssignerAdapter flightAssignerAdapter = new FlightAssignerAdapter();

        assertEquals("Early Bird", flightAssignerAdapter.whoGoesFirst(FlightAssignerAdapter.TICKET_PURCHASE_TIME));
        assertEquals("Elite", flightAssignerAdapter.whoGoesFirst(FlightAssignerAdapter.ELITE));

    }

}

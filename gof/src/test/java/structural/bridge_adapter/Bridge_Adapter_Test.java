package structural.bridge_adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Bridge_Adapter_Test {

    @Test
    void elitePreferenceAssignesElitePassengersFirst() {
        FlightPrioritizer flightPrioritizer = new FlightPrioritizerElite();
        FlightAssignerBridge flightAssignerBridge = new FlightAssignerBridge(flightPrioritizer);

        assertEquals("Elite", flightAssignerBridge.whoGoesFirst());
    }

    @Test
    void ticketPurchaseTimePreferenceAssignesWhoBoughtFirst() {
        FlightPrioritizer flightPrioritizer = new FlightPrioritizerTicketPurchaseTime();
        FlightAssignerBridge flightAssignerBridge = new FlightAssignerBridge(flightPrioritizer);

        assertEquals("Early Bird", flightAssignerBridge.whoGoesFirst());
    }

    @Test
    void adapterDecidesWhoGoesFirst() {
        FlightAssignerAdapter flightAssignerAdapter = new FlightAssignerAdapter();

        assertEquals("Early Bird", flightAssignerAdapter.whoGoesFirst(FlightAssignerAdapter.TICKET_PURCHASE_TIME));
        assertEquals("Elite", flightAssignerAdapter.whoGoesFirst(FlightAssignerAdapter.ELITE));
    }

    @Test
    void specificAdapterForElite() {
        FlightAssignerAdapterElite flightAssignerAdapter = new FlightAssignerAdapterElite();

        assertEquals("Elite", flightAssignerAdapter.whoGoesFirst());
    }

    @Test
    void specificAdapterForTicketTime() {
        FlightAssignerAdapterTicketTime flightAssignerAdapter = new FlightAssignerAdapterTicketTime();

        assertEquals("Early Bird", flightAssignerAdapter.whoGoesFirst());
    }

}

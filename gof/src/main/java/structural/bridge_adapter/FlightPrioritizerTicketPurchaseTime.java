package structural.bridge_adapter;

public class FlightPrioritizerTicketPurchaseTime implements FlightPrioritizer {
    @Override
    public String whoGoesFirst() {
        return "Early Bird";
    }
}

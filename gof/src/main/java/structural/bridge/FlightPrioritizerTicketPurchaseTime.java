package structural.bridge;

public class FlightPrioritizerTicketPurchaseTime implements FlightPrioritizer {
    @Override
    public String whoGoesFirst() {
        return "Early Bird";
    }
}

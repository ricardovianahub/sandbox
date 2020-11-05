package structural.bridge_adapter;

public class FlightAssignerAdapterTicketTime {

    private FlightPrioritizerTicketPurchaseTime flightPrioritizerTicketPurchaseTime;

    public FlightAssignerAdapterTicketTime() {
        this.flightPrioritizerTicketPurchaseTime = new FlightPrioritizerTicketPurchaseTime();
    }

    public String whoGoesFirst() {
        return this.flightPrioritizerTicketPurchaseTime.whoGoesFirst();
    }
}

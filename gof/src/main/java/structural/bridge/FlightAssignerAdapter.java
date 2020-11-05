package structural.bridge;

import sun.security.krb5.internal.Ticket;

public class FlightAssignerAdapter {
    static final String TICKET_PURCHASE_TIME = "TicketPurchaseTime";
    static final String ELITE = "Elite";

    public String whoGoesFirst(String prioritization) {
        if (TICKET_PURCHASE_TIME.equals(prioritization)) {
            return "Early Bird";
        }
        return "Elite";
    }
}

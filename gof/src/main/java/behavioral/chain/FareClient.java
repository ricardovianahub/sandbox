package behavioral.chain;

import java.util.ArrayList;
import java.util.List;

public class FareClient {
    private List<FareHandler> fareHandlerList;

    public FareClient() {
        this(1);
    }

    public FareClient(double dailyFactor) {
        this.fareHandlerList = new ArrayList<>();
        this.fareHandlerList.add(new FareHandlerFixedDiscount());
        this.fareHandlerList.add(new FareHandlerFlatFee());
        this.fareHandlerList.add(new FareHandlerDailyFactor(dailyFactor));
    }

    public double priceWithDiscountOf(Reservation reservation) {
//        if ("DFW".equals(reservation.getOriginAirport())) {
//            return reservation.getBaseFare() * 0.95;
//        } else if ("LAX".equals(reservation.getOriginAirport())) {
//            return reservation.getBaseFare() + 10;
//        } else {
//            return reservation.getBaseFare();
//        }

        double fare = reservation.getBaseFare();
        for (FareHandler fareHandler : this.fareHandlerList) {
            fare = fareHandler.fareCalculation(reservation.getOriginAirport(), fare);
        }
        return fare;
    }
}

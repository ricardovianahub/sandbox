package behavioral.chain;

public class FareHandlerFixedDiscount implements FareHandler{
    @Override
    public double fareCalculation(String originAirport, double baseFare) {
        return "DFW".equals(originAirport) ? baseFare * 0.95 : baseFare;
    }
}

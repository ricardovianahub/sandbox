package behavioral.chain;

public class FareHandlerFlatFee implements FareHandler {
    @Override
    public double fareCalculation(String originAirport, double baseFare) {
        return "LAX".equals(originAirport) ? baseFare + 10 : baseFare;
    }
}

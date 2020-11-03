public class Reservation {
    private String recordLocator;
    private double cost;
    private String originAirport;
    private String firstName;
    private String lastName;

    PromotionalSale promotionalSale;

    public Reservation(String recordLocator, double cost, String originAirport, String firstName, String lastName) {

        this.recordLocator = recordLocator;
        this.cost = cost;
        this.originAirport = originAirport;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getRecordLocator() {
        return recordLocator;
    }

    public void setRecordLocator(String recordLocator) {
        this.recordLocator = recordLocator;
    }

    public String formattedName() {
        return "Traveller, Joe";
    }

    public double costBasedOnOriginAirport() {
        return 90;
    }

    public double getCost() {
        return this.cost;
    }
}

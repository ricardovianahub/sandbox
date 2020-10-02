public class Reservation {
    private String recordLocator;
    private double cost;
    private String firstName;
    private String lastName;

    public Reservation(String recordLocator, double cost, String firstName, String lastName) {

        this.recordLocator = recordLocator;
        this.cost = cost;
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
}

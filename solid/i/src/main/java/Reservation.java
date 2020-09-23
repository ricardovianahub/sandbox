public class Reservation implements SalesReservation, LoyaltyReservation {
    private String recordLocator;
    private String firstName;
    private String lastName;
    private double baseFare;

    public Reservation(String recordLocator, double baseFare, String firstName, String lastName) {
        this.baseFare = baseFare;
        this.recordLocator = recordLocator;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getRecordLocator() {
        return recordLocator;
    }

    public double getBaseFare() {
        return baseFare;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double fare(String type) {
        if ("C".equals(type)) {
            return this.baseFare * 1.1;
        }
        return this.baseFare * 1.2;
    }

    public String userCode() {
        return this.lastName.toLowerCase()
                + this.firstName.substring(0,1).toLowerCase();
    }
}

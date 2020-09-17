package domain;

public class Customer {
    private String firstName;
    private String lastName;
    private String origin;
    private String destination;
    private String flightNumber;
    private CarBodyType type;

    public Customer(
            String firstName, String lastName,
            String origin, String destination,
            String flightNumber, CarBodyType type
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.origin = origin;
        this.destination = destination;
        this.flightNumber = flightNumber;
        this.type = type;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public CarBodyType getType() {
        return type;
    }
}

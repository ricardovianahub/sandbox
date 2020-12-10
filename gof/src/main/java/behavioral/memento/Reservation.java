package behavioral.memento;

import java.util.Objects;

public class Reservation {
    private String firstName;
    private String lastName;
    private String origin;
    private String destination;
    private double fare;

    public Reservation(String firstName, String lastName, String origin, String destination, double fare) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.origin = origin;
        this.destination = destination;
        this.fare = fare;
    }

    public String getFirstName() {
        return firstName;
    }

    public Reservation setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Reservation setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getOrigin() {
        return origin;
    }

    public Reservation setOrigin(String origin) {
        this.origin = origin;
        return this;
    }

    public String getDestination() {
        return destination;
    }

    public Reservation setDestination(String destination) {
        this.destination = destination;
        return this;
    }

    public double getFare() {
        return fare;
    }

    public Reservation setFare(double fare) {
        this.fare = fare;
        return this;
    }

    public ReservationMemento saveToMemento() {
        return new ReservationMemento(this);
    }

    public void restoreFromMemento(ReservationMemento reservationMemento) {
        this.firstName = reservationMemento.reservation.firstName;
        this.lastName = reservationMemento.reservation.lastName;
        this.origin = reservationMemento.reservation.origin;
        this.destination = reservationMemento.reservation.destination;
        this.fare = reservationMemento.reservation.fare;
    }

    public static class ReservationMemento {
        private final Reservation reservation;

        public ReservationMemento(Reservation reservation) {
            this.reservation = reservation;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Double.compare(that.fare, fare) == 0 &&
                firstName.equals(that.firstName) &&
                lastName.equals(that.lastName) &&
                origin.equals(that.origin) &&
                destination.equals(that.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, origin, destination, fare);
    }
}

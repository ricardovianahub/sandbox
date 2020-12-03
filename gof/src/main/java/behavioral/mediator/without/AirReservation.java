package behavioral.mediator.without;

public class AirReservation {
    private final Hotel hotel;
    private final Voucher voucher;
    private final RentalCar rentalCar;
    private Status status;

    public AirReservation(Hotel hotel, Voucher voucher, RentalCar rentalCar) {
        this.hotel = hotel;
        this.voucher = voucher;
        this.rentalCar = rentalCar;
        this.status = Status.TICKETED;
    }

    public void changeStatus(Status status) {
        if (Status.DELAYED_OVERNIGHT.equals(status)) {
            hotel.createHotelReservation();
            rentalCar.createRentalCarReservation();
        }
        this.status = status;
    }

    public Status getStatus() {
        return this.status;
    }

    public enum Status {TICKETED, DELAYED_OVERNIGHT_WITH_VOUCHER, DELAYED_OVERNIGHT}
}

package behavioral.mediator.without;

public class Hotel {
    private Voucher voucher;
    private AirReservation airReservation;

    private boolean reserved;
    private RentalCar rentalCar;

    public Hotel(Voucher voucher) {
        this.voucher = voucher;
        this.reserved = false;
    }

    public void setAirReservation(AirReservation airReservation) {
        this.airReservation = airReservation;
    }

    public boolean isHotelReserved() {
        return this.reserved;
    }

    public void createHotelReservation() {
        this.reserved = true;
    }

    public void assignRoomUnavailable() {
        this.reserved = false;
        rentalCar.cancelReservation();
        voucher.assignIssued();
        airReservation.changeStatus(AirReservation.Status.DELAYED_OVERNIGHT_WITH_VOUCHER);
    }

    public void setRentalCar(RentalCar rentalCar) {
        this.rentalCar = rentalCar;
    }

}

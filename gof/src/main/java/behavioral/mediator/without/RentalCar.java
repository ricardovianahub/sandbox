package behavioral.mediator.without;

public class RentalCar {
    private final Voucher voucher;
    private boolean reserved;

    public RentalCar(Voucher voucher) {

        this.voucher = voucher;
    }

    public boolean isRentalCarReserved() {
        return reserved;
    }

    public void setRentalCarReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public void createRentalCarReservation() {
        this.reserved = true;
    }

    public void cancelReservation() {
        this.reserved = false;
    }
}

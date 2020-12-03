package behavioral.mediator.without;

public class Voucher {
    private boolean issued;

    public Voucher() {
        this.issued = false;
    }

    public boolean isIssued() {
        return this.issued;
    }

    public void assignIssued() {
        this.issued = true;
    }
}

package behavioral.mediator.with;

public class Voucher {
    private boolean issued;
    private final Mediator mediator;

    public Voucher(Mediator mediator) {
        this.issued = false;
        this.mediator = mediator;
    }

    public boolean isIssued() {
        return this.issued;
    }

    public void assignIssued() {
        this.issued = true;
    }
}

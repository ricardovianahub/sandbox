package behavioral.command;

public class SabrePNRDeletion implements SabreOperation {

    private final SabreReceiver sabreReceiver;

    public SabrePNRDeletion(String record) {
        this.sabreReceiver = new SabreReceiver("deleted [" + record + "]");
    }

    @Override
    public String execute() {
        return this.sabreReceiver.delete();
    }
}

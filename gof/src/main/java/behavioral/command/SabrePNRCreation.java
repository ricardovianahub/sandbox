package behavioral.command;

public class SabrePNRCreation implements SabreOperation {
    private final SabreReceiver sabreReceiver;

    public SabrePNRCreation(String recordToBeCreated) {
        this.sabreReceiver = new SabreReceiver("created [" + recordToBeCreated + "]");
    }

    @Override
    public String execute() {
        return this.sabreReceiver.create();
    }
}

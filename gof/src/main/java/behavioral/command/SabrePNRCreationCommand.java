package behavioral.command;

public class SabrePNRCreationCommand implements SabreCommand {
    private final SabreReceiver sabreReceiver;

    public SabrePNRCreationCommand(String recordToBeCreated) {
        this.sabreReceiver = new SabreReceiver("created [" + recordToBeCreated + "]");
    }

    @Override
    public String execute() {
        return this.sabreReceiver.create();
    }
}

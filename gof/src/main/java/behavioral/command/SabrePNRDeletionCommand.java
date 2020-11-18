package behavioral.command;

public class SabrePNRDeletionCommand implements SabreCommand {

    private final SabreReceiver sabreReceiver;

    public SabrePNRDeletionCommand(String record) {
        this.sabreReceiver = new SabreReceiver("deleted [" + record + "]");
    }

    @Override
    public String execute() {
        return this.sabreReceiver.delete();
    }
}

package behavioral.command;

public class SabrePNRUpdateCommand implements SabreCommand {
    private final SabreReceiver sabreReceiver;

    public SabrePNRUpdateCommand(String original, String changed) {
        this.sabreReceiver = new SabreReceiver("updated [" + original + "] to [" + changed + "]");
    }

    @Override
    public String execute() {
        return this.sabreReceiver.update();
    }
}

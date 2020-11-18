package behavioral.command;

public class SabrePNRUpdate implements SabreOperation {
    private final SabreReceiver sabreReceiver;

    public SabrePNRUpdate(String original, String changed) {
        this.sabreReceiver = new SabreReceiver("updated [" + original + "] to [" + changed + "]");
    }

    @Override
    public String execute() {
        return this.sabreReceiver.update();
    }
}

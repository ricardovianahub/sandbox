package behavioral.command;

public class SabreReceiver {
    // ALl the logic to connect with and contact Sabre

    private final String record;

    public SabreReceiver(String record) {
        this.record = record;
    }

    public String create() {
        return this.record;
    }

    public String update() {
        return this.record;
    }

    public String delete() {
        return this.record;
    }
}

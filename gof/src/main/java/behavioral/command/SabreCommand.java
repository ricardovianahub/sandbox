package behavioral.command;

public class SabreCommand {
    public String executeOperation(SabreOperation sabreOperation) {
        return sabreOperation.execute();
    }
}

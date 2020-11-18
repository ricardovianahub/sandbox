package behavioral.command;

public class SabreInvoker {
    public String executeCommand(SabreCommand sabreCommand) {
        return sabreCommand.execute();
    }
}

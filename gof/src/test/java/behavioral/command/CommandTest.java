package behavioral.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CommandTest {

//    Possible indications:
//
//    History of requests or other logging mechanis
//    Callback functionality managed outside of the Received class
//    Requests handled at different times or orders
//    The invoker should be decoupled from the object handling the invocation.
//    There are common adjustments to the parameters or ways to call the Receiver, but it is a third-party
//          class and we don't own the source code

    @Test
    void sabreCreatePNR() {
        SabreCommand creationCommand = new SabrePNRCreationCommand("one");
        SabreInvoker sabreInvoker = new SabreInvoker();

        assertEquals("created [one]", sabreInvoker.executeCommand(creationCommand));
    }

    @Test
    void sabreUpdatePNR() {
        SabreCommand updateCommand = new SabrePNRUpdateCommand("one", "two");
        SabreInvoker sabreInvoker = new SabreInvoker();

        assertEquals("updated [one] to [two]", sabreInvoker.executeCommand(updateCommand));
    }

    @Test
    void sabreDeletePNR() {
        SabreCommand deletionCommand = new SabrePNRDeletionCommand("one");
        SabreInvoker sabreInvoker = new SabreInvoker();

        assertEquals("deleted [one]", sabreInvoker.executeCommand(deletionCommand));
    }
}

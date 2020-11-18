package behavioral.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CommandTest {

    @Test
    void sabreCreatePNR() {
        SabreOperation sabrePNRCreation = new SabrePNRCreation("one");
        SabreCommand sabreCommand = new SabreCommand();

        assertEquals("created [one]", sabreCommand.executeOperation(sabrePNRCreation));
    }

    @Test
    void sabreUpdatePNR() {
        SabreOperation sabrePNRUpdate = new SabrePNRUpdate("one", "two");
        SabreCommand sabreCommand = new SabreCommand();

        assertEquals("updated [one] to [two]", sabreCommand.executeOperation(sabrePNRUpdate));
    }

    @Test
    void sabreDeletePNR() {
        SabreOperation sabrePNRDeletion = new SabrePNRDeletion("one");
        SabreCommand sabreCommand = new SabreCommand();

        assertEquals("deleted [one]", sabreCommand.executeOperation(sabrePNRDeletion));
    }

}

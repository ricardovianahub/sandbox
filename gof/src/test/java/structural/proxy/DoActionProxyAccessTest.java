package structural.proxy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class DoActionProxyAccessTest {

    @Test
    void doActionDoitReturnsHello() {
        DoAction doAction = new DoAction();

        assertEquals("hello", doAction.doit());
    }

    @Test
    void doActionProxyDoitThrowsExceptionIfNotAuthorized() {
        boolean authorized = false;
        DoActionProxy doActionProxy = new DoActionProxy(authorized);

        assertThrows(IllegalAccessException.class, doActionProxy::doit);
    }

    @Test
    void doActionProxyDoitReturnsHelloIfAuthorized() throws IllegalAccessException {
        boolean authorized = true;
        DoActionProxy doActionProxy = new DoActionProxy(authorized);

        assertEquals("hello", doActionProxy.doit());
    }
}

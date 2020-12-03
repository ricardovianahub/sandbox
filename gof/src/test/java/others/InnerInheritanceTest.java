package others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class InnerInheritanceTest {

    @Test
    void clientOutputsFormattedName() {
        ShopClient shopClient = new ShopClient("John", "Doe");
        assertEquals("This is Doe, John", shopClient.fullName());
    }

    @Test
    void accessToInternalVariablesViaInnerClass() {
        ShopClient.Visitor visitor = new ShopClient.Visitor();
        ShopClient shopClient = new ShopClient("John", "Doe");
        assertEquals("John", visitor.visit(shopClient));
    }

    @Test
    void accessToInternalVariablesViaExternalSubclass() {
        ShopClientVisitor visitor = new ShopClientVisitor();
        ShopClient shopClient = new ShopClient("John", "Doe");
        assertEquals("John", visitor.visit(shopClient));
    }
}

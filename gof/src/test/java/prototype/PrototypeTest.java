package prototype;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class PrototypeTest {

    @Test
    void clonePrototypeReturnsNewObjectWithSameValues() throws CloneNotSupportedException {
        Prototype prototype = new Prototype("one", "two");
        Prototype cloned = prototype.clone();

        assertNotEquals(cloned, prototype);
        assertEquals(cloned.getOne(), prototype.getOne());
        assertEquals(cloned.getTwo(), prototype.getTwo());
    }

}

package creational.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class ComplexObjectBuilderTest {

    @Test
    void instantiateComplexObject() {
        ComplexObject complexObject = ComplexObjectBuilder.build(
                "first", "second", "third"
        );
        assertNotNull(complexObject);
        assertEquals("first", complexObject.getFirst());
        assertEquals("second", complexObject.getSecond());
        assertEquals("third", complexObject.getThird());
    }

}

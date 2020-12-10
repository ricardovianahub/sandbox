package creational.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class ComplexObjectJoshuaBlockTest {

    @Test
    void instantiateComplexObject2() {
        ComplexObjectJoshuaBlock complexObjectJoshuaBlock =
                new ComplexObjectJoshuaBlock.Builder()
                        .withFirst("first")
                        .withSecond("second")
                        .withThird("third")
                        .build();
        assertNotNull(complexObjectJoshuaBlock);
        assertEquals("first", complexObjectJoshuaBlock.getFirst());
        assertEquals("second", complexObjectJoshuaBlock.getSecond());
        assertEquals("third", complexObjectJoshuaBlock.getThird());
    }

}

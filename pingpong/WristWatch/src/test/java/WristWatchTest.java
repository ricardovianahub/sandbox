import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class WristWatchTest {

    @Test
    public void convertToRomansReturnsIfor1() {
        testMatchingValues("I", 1);
    }

    @Test
    public void convertToRomansReturnsIIfor2() {
        testMatchingValues("II", 2);
    }

    @Test
    public void convertToRomansReturnsIIIfor3() {
        testMatchingValues("III", 3);
    }

    @Test
    public void convertToRomansReturnsIVfor4() {
        testMatchingValues("IV", 4);
    }

    @Test
    public void covertToRomansReturnsVfor5(){
        testMatchingValues("V", 5);
    }

    @Test
    public void covertToRomansReturnsXfor10(){
        testMatchingValues("X", 10);
    }

    @Test
    public void covertToRomansReturnsNullForAnythingGreater12(){
        testRanges(13, Integer.MAX_VALUE);
    }

    @Test
    public void covertToRomansReturnsNullForAnythingLessThan1(){
        testRanges(0, Integer.MIN_VALUE);
    }

    private void testRanges(int i, int maxValue) {
        WristWatch wristWatch = new WristWatch();

        assertNull(wristWatch.convertToRomansMap(i));
        assertNull(wristWatch.convertToRomansMap(maxValue));
    }

    private void testMatchingValues(String i, int i2) {
        WristWatch wristWatch = new WristWatch();

        String expected = i;
        String actual = wristWatch.convertToRomansMap(i2);

        assertEquals(expected, actual);
    }
}

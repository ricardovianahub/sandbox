import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.Test;

public class WristWatchTest {

    @Test
    public void convertToRomans() {
        testMatchingValues("I", 1);
        testMatchingValues("II", 2);
        testMatchingValues("III", 3);
        testMatchingValues("IV", 4);
        testMatchingValues("V", 5);
        testMatchingValues("VI", 6);
        testMatchingValues("VII", 7);
        testMatchingValues("VIII", 8);
        testMatchingValues("IX", 9);
        testMatchingValues("X", 10);
        testMatchingValues("XI", 11);
        testMatchingValues("XII", 12);
    }

    private void testMatchingValues(String i, int i2) {
        WristWatch wristWatch = new WristWatch(new LocalServerTime());

        String expected = i;
        String actual = wristWatch.convertToRomans(i2);

        assertEquals(expected, actual);
    }

    @Test
    public void covertToRomansReturnsNullForAnythingGreater12() {
        testRanges(13, Integer.MAX_VALUE);
    }

    @Test
    public void covertToRomansReturnsNullForAnythingLessThan1() {
        testRanges(0, Integer.MIN_VALUE);
    }

    private void testRanges(int i, int maxValue) {
        WristWatch wristWatch = new WristWatch(new LocalServerTime());

        assertNull(wristWatch.convertToRomans(i));
        assertNull(wristWatch.convertToRomans(maxValue));
    }

    @Test
    public void currentSecondsReturnsFormattedString() {
        final int expectedSeconds = 32;
        WristWatch wristWatch = new WristWatch(
                new OfficialTime() {
                    @Override
                    public LocalDateTime current() {
                        return LocalDateTime.of(
                                2021, 7, 9,
                                9, 51, expectedSeconds,
                                0
                        );
                    }
                }
        );
        int seconds = LocalDateTime.now().getSecond();
        String expected;
        if (seconds <= 9) {
            expected = "0" + seconds;
        } else {
            expected = String.valueOf(seconds);
        }

        String actual = wristWatch.currentSeconds();
        assertEquals(2, actual.length());
        assertTrue(
                Integer.parseInt(actual) >= 0
                        && Integer.parseInt(actual) <= 59
        );
        assertEquals(expectedSeconds, Integer.parseInt(actual));
    }

    @Test(expected = IllegalArgumentException.class)
    public void setAlarmThrowExceptionWhenNumberIsOutOfRange1() {
        setAlarmCall(13, 60);

    }

    @Test(expected = IllegalArgumentException.class)
    public void setAlarmThrowExceptionWhenNumberIsOutOfRange2() {
        setAlarmCall(7, 64);
    }

    @Test
    public void setAlarmWhenNumberIsInOfRange() {
        setAlarmCall(12, 59);
        setAlarmCall(1, 0);
        setAlarmCall(7, 34);

    }

    private void setAlarmCall(int hour, int minute) {
        WristWatch wristWatch = new WristWatch(new LocalServerTime());
        wristWatch.setAlarm(hour, minute);
    }

}

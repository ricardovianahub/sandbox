import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class WristWatchTest {

    @Test
    public void resetAlarmReturnsNothingWhenNoAlarmIsSet() {
        WristWatch wristWatch = new WristWatch(new LocalServerTime());
        String beforeResetAlarm = wristWatch.readAlarm();
        wristWatch.resetAlarm();
        assertEquals(beforeResetAlarm, wristWatch.readAlarm());
    }

    @Test
    public void resetAlarmResetsValueTo0_00WhenAlarmIsSet(){
        WristWatch wristWatch = new WristWatch(new LocalServerTime());
        wristWatch.setAlarm(1,23);
        wristWatch.resetAlarm();
        assertNull(wristWatch.readAlarm());
    }

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
    public void convertToRomansReturnsNullForAnythingGreater12() {
        testRanges(13, Integer.MAX_VALUE);
    }

    @Test
    public void convertToRomansReturnsNullForAnythingLessThan1() {
        testRanges(0, Integer.MIN_VALUE);
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
    public void setAlarmThrowExceptionWhenNumberIsOutOfRange3() {
        setAlarmCall(12, -1);

    }

    @Test(expected = IllegalArgumentException.class)
    public void setAlarmThrowExceptionWhenNumberIsOutOfRange4() {
        setAlarmCall(7, 64);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setAlarmThrowExceptionWhenNumberIsOutOfRange2() {
        setAlarmCall(7, 60);
    }

    @Test
    public void setAlarmWhenNumberIsInRange() {
        setAlarmCall(12, 59);
        setAlarmCall(1, 0);
        setAlarmCall(7, 34);

    }

    @Test
    public void readAlarmWhenSetAlarm() {
        WristWatch wristWatch = new WristWatch(new LocalServerTime());
        wristWatch.setAlarm(9, 5);
        String actual = wristWatch.readAlarm();

        assertEquals("0905", actual);

    }

    @Test
    public void readAlarmWhenAlarmIsNotSet(){
        WristWatch wristWatch = new WristWatch(new LocalServerTime());
        String actual = wristWatch.readAlarm();

        assertNull(actual);
    }

    @Test
    public void readAlarmWhenSetAlarmToGreaterThan10HrAndMinutes() {
        WristWatch wristWatch = new WristWatch(new LocalServerTime());
        wristWatch.setAlarm(10, 10);
        String actual = wristWatch.readAlarm();

        assertEquals("1010", actual);

    }

    @Test
    public void readAlarmWhenSetAlarmToGreaterThan10HrAndMinutes2() {
        WristWatch wristWatch = new WristWatch(new LocalServerTime());
        wristWatch.setAlarm(10, 50);
        String actual = wristWatch.readAlarm();

        assertEquals("1050", actual);

    }

    @Test
    public void readAlarmWhenSetAlarmToGreaterThan10HrAndMinutes3() {
        WristWatch wristWatch = new WristWatch(new LocalServerTime());
        wristWatch.setAlarm(12, 50);
        String actual = wristWatch.readAlarm();

        assertEquals("1250", actual);

    }

    @Test
    public void readAlarmWhenSetAlarm9HrAndMinutes00() {
        WristWatch wristWatch = new WristWatch(new LocalServerTime());
        wristWatch.setAlarm(9, 0);
        String actual = wristWatch.readAlarm();

        assertEquals("0900", actual);

    }

    @Test
    public void readAlarmWhenSetAlarm11HrAndMinutes00() {
        WristWatch wristWatch = new WristWatch(new LocalServerTime());
        wristWatch.setAlarm(11, 0);
        String actual = wristWatch.readAlarm();

        assertEquals("1100", actual);

    }

    @Test
    public void readAlarmWhenSetAlarmGreaterThan10HrAndLessThan10Minutes() {
        WristWatch wristWatch = new WristWatch(new LocalServerTime());
        wristWatch.setAlarm(12, 5);
        String actual = wristWatch.readAlarm();

        assertEquals("1205", actual);

    }

    @Test
    public void readAlarmWhenSetAlarmLessThan10HrAndGreaterThan10Minutes() {
        WristWatch wristWatch = new WristWatch(new LocalServerTime());
        wristWatch.setAlarm(6, 25);
        String actual = wristWatch.readAlarm();

        assertEquals("0625", actual);

    }

    private void testRanges(int i, int maxValue) {
        WristWatch wristWatch = new WristWatch(new LocalServerTime());

        assertNull(wristWatch.convertToRomans(i));
        assertNull(wristWatch.convertToRomans(maxValue));
    }

    private void setAlarmCall(int hour, int minute) {
        WristWatch wristWatch = new WristWatch(new LocalServerTime());
        wristWatch.setAlarm(hour, minute);
    }

}

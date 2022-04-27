package com.aa.casting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ByteCharTest {

    @ParameterizedTest
    @CsvSource({
            "0, 0", "127, 127", "-1, 65535", "128, 128"
    })
    void castByteToChar(byte input, int expected) {
        char actual = (char) input;
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({
            "\\u0000"
    })
    void binaryZero(char zero) {
        assertEquals('0', zero);
    }

    @Test
    void binaryZeroComparison() {
        char zeroChar = 0;
        byte zeroByte = (byte) zeroChar;
        char nextZeroChar = (char) zeroByte;
        String zeroString = String.valueOf((char) 0);
        assertEquals(zeroChar, zeroByte);
        assertEquals(zeroChar, nextZeroChar);
        assertEquals(zeroChar, zeroString.charAt(0));
    }

}

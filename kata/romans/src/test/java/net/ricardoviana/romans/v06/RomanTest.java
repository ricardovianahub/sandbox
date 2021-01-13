package net.ricardoviana.romans.v06;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class RomanTest {

    @ParameterizedTest
    @MethodSource("romanValues")
    void romanConversion(String roman, int number) {
        assertEquals(roman, Roman.of(number));
    }

    static Stream<Arguments> romanValues() {
        return Stream.of(
                Arguments.of("I", 1),
                Arguments.of("II", 2),
                Arguments.of("III", 3),
                Arguments.of("IV", 4),
                Arguments.of("V", 5),
                Arguments.of("VI", 6),
                Arguments.of("IX", 9),
                Arguments.of("X", 10),
                Arguments.of("XI", 11),
                Arguments.of("XV", 15),
                Arguments.of("XX", 20),
                Arguments.of("XXX", 30),
                Arguments.of("XL", 40),
                Arguments.of("L", 50),
                Arguments.of("LI", 51),
                Arguments.of("LV", 55),
                Arguments.of("LX", 60),
                Arguments.of("XC", 90),
                Arguments.of("C", 100),
                Arguments.of("CI", 101),
                Arguments.of("CV", 105),
                Arguments.of("CX", 110),
                Arguments.of("CL", 150),
                Arguments.of("CC", 200),
                Arguments.of("CCC", 300),
                Arguments.of("CD", 400),
                Arguments.of("CDXLIV", 444),
                Arguments.of("D", 500),
                Arguments.of("DII", 502),
                Arguments.of("DV", 505),
                Arguments.of("DXX", 520),
                Arguments.of("DL", 550),
                Arguments.of("DC", 600),
                Arguments.of("DCLX", 660),
                Arguments.of("CM", 900),
                Arguments.of("CMXCIX", 999),
                Arguments.of("M", 1000),
                Arguments.of("MIII", 1003),
                Arguments.of("MIV", 1004),
                Arguments.of("MV", 1005),
                Arguments.of("MVI", 1006),
                Arguments.of("MX", 1010),
                Arguments.of("ML", 1050),
                Arguments.of("MXXX", 1030),
                Arguments.of("MCC", 1200),
                Arguments.of("MCCC", 1300),
                Arguments.of("MD", 1500),
                Arguments.of("MDCLXVI", 1666),
                Arguments.of("MDCCCLXXXVIII", 1888),
                Arguments.of("MCMXCIX", 1999),
                Arguments.of("MM", 2000),
                Arguments.of("MMCXCIII", 2193),
                Arguments.of("MMM", 3000),
                Arguments.of("MMMDCLXVI", 3666),
                Arguments.of("MMMCMXLIX", 3949)
        );
    }

}

package com.example.loop.node;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.example.loop.node.exercise.EventHandler;
import com.example.loop.node.exercise.EventHandlerCalc;

public class EventHandlerCalcTest {

    static Stream<Arguments> happyPathData() {
        return Stream.of(
                Arguments.of(new String[]{"2", "x", "4"}, "8"),
                Arguments.of(new String[]{"9", "x", "8"}, "72"),
                Arguments.of(new String[]{"5", "x", "5"}, "25")
        );
    }

    @ParameterizedTest
    @MethodSource("happyPathData")
    void happyPath(String[] argument, String expected) {
        EventHandler calc = new EventHandlerCalc();

        String actual = calc.handleEvent(argument);

        assertEquals(expected, actual);
    }

}

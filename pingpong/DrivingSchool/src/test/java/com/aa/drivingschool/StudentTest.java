package com.aa.drivingschool;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class StudentTest {
    @Test
    void addStudentWithInvalidArgumentsThrowsException() {
        assertThrows(
                IllegalArgumentException.class
                , () -> new Student("", "second")
        );
        assertThrows(
                IllegalArgumentException.class
                , () -> new Student("first", "")
        );
        assertThrows(
                IllegalArgumentException.class
                , () -> new Student(null, "second")
        );
        assertThrows(
                IllegalArgumentException.class
                , () -> new Student("first", null)
        );
    }
}

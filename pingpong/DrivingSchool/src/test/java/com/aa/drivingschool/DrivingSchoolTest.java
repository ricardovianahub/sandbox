package com.aa.drivingschool;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.aa.drivingschool.DrivingSchool.DEFAULT_START_HOURS;
import static org.junit.jupiter.api.Assertions.*;

public class DrivingSchoolTest {


    @Test
    void retrieveCalendarContainsStaticWeekdaysAndTimeSlots() {
        DrivingSchool drivingSchool = new DrivingSchool();

        List<ClassDay> expectedClassDays = new ArrayList<>() {{
            add(new ClassDay("MON", DEFAULT_START_HOURS));
            add(new ClassDay("TUE", DEFAULT_START_HOURS));
            add(new ClassDay("WED", DEFAULT_START_HOURS));
            add(new ClassDay("THU", DEFAULT_START_HOURS));
            add(new ClassDay("FRI", DEFAULT_START_HOURS));
            add(new ClassDay("MON", DEFAULT_START_HOURS));
            add(new ClassDay("TUE", DEFAULT_START_HOURS));
            add(new ClassDay("WED", DEFAULT_START_HOURS));
            add(new ClassDay("THU", DEFAULT_START_HOURS));
            add(new ClassDay("FRI", DEFAULT_START_HOURS));
        }};

        assertArrayEquals(expectedClassDays.toArray(), drivingSchool.retrieveScheduleSheet().toArray());
    }

    @Test
    void addInstructor() {
        DrivingSchool drivingSchool = new DrivingSchool();
        String firstName = "John";
        String lastName = "Doe";
        int instructorId = drivingSchool.addInstructor(firstName, lastName);
        assertEquals(1, instructorId);
    }

    @Test
    void addSecondInstructorAndReceive2() {
        DrivingSchool drivingSchool = new DrivingSchool();
        String firstName = "John";
        String lastName = "Doe";
        drivingSchool.addInstructor(firstName, lastName);
        firstName = "BillyBob";
        lastName = "Smith";
        int instructorId2 = drivingSchool.addInstructor(firstName, lastName);
        assertEquals(2, instructorId2);
    }

    @Test
    void addThirdInstructorWithSameLastNameReceivesDifferentId() {
        DrivingSchool drivingSchool = new DrivingSchool();
        drivingSchool.addInstructor("John", "Doe");
        drivingSchool.addInstructor("Bill", "Smith");
        int instructorId3 = drivingSchool.addInstructor("John", "Smith");
        assertEquals(3, instructorId3);
    }

    @Test
    void addInstructorWithSameLastAndFirstNameThrowException() {
        DrivingSchool drivingSchool = new DrivingSchool();
        drivingSchool.addInstructor("John", "Doe");
        assertThrows(IllegalStateException.class, () -> drivingSchool.addInstructor("John", "Doe"));
    }

    @Test
    void addMultipleInstructorWithSameLastAndFirstNameThrowException() {
        DrivingSchool drivingSchool = new DrivingSchool();
        drivingSchool.addInstructor("John", "Doe");
        drivingSchool.addInstructor("John", "Smith");
        assertThrows(IllegalStateException.class, () -> drivingSchool.addInstructor("John", "Doe"));
    }

    @Test
    void createScheduleSheet() {
        DrivingSchool drivingSchool = new DrivingSchool();
        int instructorID = drivingSchool.addInstructor("John", "Doe");

        drivingSchool.createScheduleSheet(instructorID);
        int actual = drivingSchool.amountOfScheduleSheets();

        assertEquals(1, actual);
    }

    @Test
    void create0ScheduleSheets() {
        DrivingSchool drivingSchool = new DrivingSchool();
        int actual = drivingSchool.amountOfScheduleSheets();

        assertEquals(0, actual);
    }

    @Test
    void create0ScheduleSheets2() {
        DrivingSchool drivingSchool = new DrivingSchool();
        drivingSchool.addInstructor("John", "Doe");

        int actual = drivingSchool.amountOfScheduleSheets();

        assertEquals(0, actual);
    }

    @Test
    void addStudent() {
        DrivingSchool drivingSchool = new DrivingSchool();
        int studentID = drivingSchool.addStudent("Tom", "Jerry");

        assertEquals(1, studentID);
    }

    @Test
    void addTwoStudents() {
        DrivingSchool drivingSchool = new DrivingSchool();
        drivingSchool.addStudent("Tom", "Jerry");
        int studentID = drivingSchool.addStudent("Tom", "Jerry");

        assertEquals(2, studentID);
    }

    @Test
    void addStudentWithInvalidArgumentsThrowsException() {
        DrivingSchool drivingSchool = new DrivingSchool();

        assertThrows(
                IllegalArgumentException.class
                , () -> drivingSchool.addStudent("", "second")
        );
        assertThrows(
                IllegalArgumentException.class
                , () -> drivingSchool.addStudent("first", "")
        );
        assertThrows(
                IllegalArgumentException.class
                , () -> drivingSchool.addStudent(null, "second")
        );
        assertThrows(
                IllegalArgumentException.class
                , () -> drivingSchool.addStudent("first", null)
        );

    }

    @Test
    void retrieveScheduleSheetWithInstructorParam() {
        DrivingSchool drivingSchool = new DrivingSchool();

        assertThrows(IllegalArgumentException.class
                , () -> drivingSchool.retrieveScheduleSheetByInstructor(1));
    }


}

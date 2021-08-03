package com.aa.drivingschool;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.aa.drivingschool.DrivingSchool.DEFAULT_START_HOURS;
import static org.junit.jupiter.api.Assertions.*;

public class DrivingSchoolTest {


    @Test
    void retrieveCalendarContainsStaticWeekdaysAndTimeSlots() {
        DrivingSchool drivingSchool = new DrivingSchool();

        ScheduleGrid expectedScheduleGrid = new ScheduleGrid();

        expectedScheduleGrid.add("MON", DEFAULT_START_HOURS);
        expectedScheduleGrid.add("TUE", DEFAULT_START_HOURS);
        expectedScheduleGrid.add("WED", DEFAULT_START_HOURS);
        expectedScheduleGrid.add("THU", DEFAULT_START_HOURS);
        expectedScheduleGrid.add("FRI", DEFAULT_START_HOURS);

        assertEquals(5, expectedScheduleGrid.numberOfClassDays());

        expectedScheduleGrid.add("MON", DEFAULT_START_HOURS);
        expectedScheduleGrid.add("TUE", DEFAULT_START_HOURS);
        expectedScheduleGrid.add("WED", DEFAULT_START_HOURS);
        expectedScheduleGrid.add("THU", DEFAULT_START_HOURS);
        expectedScheduleGrid.add("FRI", DEFAULT_START_HOURS);

        assertEquals(10, expectedScheduleGrid.numberOfClassDays());

        assertEquals(
                expectedScheduleGrid,
                drivingSchool.retrieveDefaultScheduleGrid()
        );
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

    @Test
    void retrieveScheduleSheetWithInstructorParamWhenPresent() {
        DrivingSchool drivingSchool = new DrivingSchool();

        int instructorID = drivingSchool.addInstructor("John", "Doe");

        List<ClassDay> actual = drivingSchool.retrieveScheduleSheetByInstructor(instructorID);

        assertEquals(10, actual.size());
        for (ClassDay classDay : actual) {
            assertEquals(instructorID, classDay.getInstructorID());
        }
    }

    @Test
    void retrieveScheduleSheetWithInstructorParamWhen2InstructorsPresent() {
        DrivingSchool drivingSchool = new DrivingSchool();

        int instructorID = drivingSchool.addInstructor("John", "Doe");
        drivingSchool.addInstructor("Jane", "Doe");

        List<ClassDay> actual = drivingSchool.retrieveScheduleSheetByInstructor(instructorID);

        assertEquals(10, actual.size());
        for (ClassDay classDay : actual) {
            assertEquals(instructorID, classDay.getInstructorID());
        }
    }

}

package com.aa.drivingschool;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static com.aa.drivingschool.DrivingSchool.DEFAULT_START_HOURS;
import static org.junit.jupiter.api.Assertions.*;

public class DrivingSchoolTest {

    private DrivingSchool drivingSchool;

    @BeforeEach
    void beforeEach() {
        drivingSchool = new DrivingSchool();
    }

    @Test
    void retrieveCalendarContainsStaticWeekdaysAndTimeSlots() {

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
        String firstName = "John";
        String lastName = "Doe";
        int instructorId = drivingSchool.addInstructor(firstName, lastName);
        assertEquals(1, instructorId);
    }

    @Test
    void addSecondInstructorAndReceive2() {
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
        drivingSchool.addInstructor("John", "Doe");
        drivingSchool.addInstructor("Bill", "Smith");
        int instructorId3 = drivingSchool.addInstructor("John", "Smith");
        assertEquals(3, instructorId3);
    }

    @Test
    void addInstructorWithSameLastAndFirstNameThrowException() {
        drivingSchool.addInstructor("John", "Doe");
        assertThrows(IllegalStateException.class, () -> drivingSchool.addInstructor("John", "Doe"));
    }

    @Test
    void addMultipleInstructorWithSameLastAndFirstNameThrowException() {
        drivingSchool.addInstructor("John", "Doe");
        drivingSchool.addInstructor("John", "Smith");
        assertThrows(IllegalStateException.class, () -> drivingSchool.addInstructor("John", "Doe"));
    }

    @Test
    void createScheduleSheet() {
        int instructorID = drivingSchool.addInstructor("John", "Doe");

        ScheduleSheet scheduleSheet = drivingSchool.retrieveScheduleSheet(instructorID);
        int actual = drivingSchool.amountOfScheduleSheets();

        assertEquals(1, actual);
        assertEquals(instructorID, scheduleSheet.getInstructorId());
        assertEquals(10, scheduleSheet.getNumberAvailableDays());
    }

    @Test
    void createMultipleScheduleSheetWithTwoInstructors() {
        int instructorID = drivingSchool.addInstructor("John", "Doe");
        int instructor2ID = drivingSchool.addInstructor("Jane", "Doe");

        ScheduleSheet scheduleSheetInstructor1 = drivingSchool.retrieveScheduleSheet(instructorID);
        ScheduleSheet scheduleSheetInstructor2 = drivingSchool.retrieveScheduleSheet(instructor2ID);
        int actual = drivingSchool.amountOfScheduleSheets();

        assertEquals(2, actual);
        assertEquals(instructorID, scheduleSheetInstructor1.getInstructorId());
        assertEquals(instructor2ID, scheduleSheetInstructor2.getInstructorId());
    }

    @Test
    void create0ScheduleSheets() {
        int actual = drivingSchool.amountOfScheduleSheets();

        assertEquals(0, actual);
    }

    @Test
    void create0ScheduleSheets2() {
        drivingSchool.addInstructor("John", "Doe");

        int actual = drivingSchool.amountOfScheduleSheets();

        assertEquals(0, actual);
    }

    @Test
    void addStudent() {
        int studentID = drivingSchool.addStudent("Tom", "Jerry");

        assertEquals(1, studentID);
    }

    @Test
    void addTwoStudents() {
        drivingSchool.addStudent("Tom", "Jerry");
        int studentID = drivingSchool.addStudent("Tom", "Jerry");

        assertEquals(2, studentID);
    }

    @Test
    void addStudentWithInvalidArgumentsThrowsException() {

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

        assertThrows(IllegalArgumentException.class
                , () -> drivingSchool.retrieveScheduleSheetByInstructor(1));
    }

    @Test
    void retrieveScheduleSheetWithInstructorParamWhenPresent() {

        int instructorID = drivingSchool.addInstructor("John", "Doe");

        List<ClassDay> actual = drivingSchool.retrieveScheduleSheetByInstructor(instructorID);

        assertEquals(10, actual.size());
        for (ClassDay classDay : actual) {
            assertEquals(instructorID, classDay.getInstructorID());
        }
    }

    @Test
    void retrieveScheduleSheetWithInstructorParamWhen2InstructorsPresent() {

        int instructorID = drivingSchool.addInstructor("John", "Doe");
        drivingSchool.addInstructor("Jane", "Doe");

        List<ClassDay> actual = drivingSchool.retrieveScheduleSheetByInstructor(instructorID);

        assertEquals(10, actual.size());
        for (ClassDay classDay : actual) {
            assertEquals(instructorID, classDay.getInstructorID());
        }
    }

    @ParameterizedTest
    @CsvSource({
            "4,THURSDAY,5",
            "6,MONDAY,9",
            "7,MONDAY,9",
            "12,FRIDAY,13",
            "26,FRIDAY,27",
    })
    void retrieveInstructorsEarliestTime(
            int dayOfMonth, DayOfWeek expectedDayOfWeek, int expectedDayOfMonth
    ) {
        // setup
        drivingSchool.addInstructor("John", "Doe");
        int instructorID = drivingSchool.addInstructor("Jane", "Doe");
        drivingSchool.addInstructor("Alan", "Smithee");
        ScheduleSheet scheduleSheet = drivingSchool.retrieveScheduleSheet(instructorID);

        scheduleSheet.setCurrentTime(() -> LocalDateTime.of(
                2021, 8, dayOfMonth, 12, 0, 0, 0 // Friday
        ));

        // execution
        LocalDateTime earliestAvailableTime = scheduleSheet.earliestAvailableTime();

        // Assert
        assertEquals(expectedDayOfWeek, earliestAvailableTime.getDayOfWeek());
        assertEquals(expectedDayOfMonth, earliestAvailableTime.getDayOfMonth());
        assertEquals(Month.AUGUST, earliestAvailableTime.getMonth());
    }

    @Test
    void assignStudent() {
        // setup
        int studentID = drivingSchool.addStudent("Alan", "Jones");
        int instructorID = drivingSchool.addInstructor("James", "Doe");
        ScheduleSheet beforeScheduleSheet = drivingSchool.retrieveScheduleSheet(instructorID);
//        beforeScheduleSheet.setCurrentTime(() -> LocalDateTime.of(
//                2021, 8, 5, 12, 0, 0, 0 // Friday
//        ));

        // execution
        drivingSchool.assignInstructor(instructorID, studentID);
        ScheduleSheet afterScheduleSheet = drivingSchool.retrieveScheduleSheet(instructorID);
//        afterScheduleSheet.setCurrentTime(() -> LocalDateTime.of(
//                2021, 8, 5, 12, 0, 0, 0 // Friday
//        ));

        // assertion
        assertNotEquals(beforeScheduleSheet.earliestAvailableTime(), afterScheduleSheet.earliestAvailableTime());
    }

//    @Test
//    void assignMoreThan4StudentsPerInstructorFails() {
//        // setup
//        int instructorID = drivingSchool.addInstructor("James", "Doe");
//        ScheduleSheet beforeScheduleSheet = drivingSchool.createScheduleSheet(instructorID);
//        beforeScheduleSheet.setCurrentTime(() -> LocalDateTime.of(
//                2021, 8, 5, 12, 0, 0, 0 // Friday
//        ));
//
//        int studentID1 = drivingSchool.addStudent("Alan", "Jones");
//        int studentID2 = drivingSchool.addStudent("Ellen", "Jones");
//        int studentID3 = drivingSchool.addStudent("Janet", "Jones");
//        int studentID4 = drivingSchool.addStudent("Alexander", "Jones");
//        int studentID5 = drivingSchool.addStudent("Douglas", "Jones");
//
//        // execution & assertion
//        assertTrue(drivingSchool.assignInstructor(instructorID, studentID1));
//        assertTrue(drivingSchool.assignInstructor(instructorID, studentID2));
//        assertTrue(drivingSchool.assignInstructor(instructorID, studentID3));
//        assertTrue(drivingSchool.assignInstructor(instructorID, studentID4));
//        assertFalse(drivingSchool.assignInstructor(instructorID, studentID5));
//    }

}

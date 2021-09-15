package com.aa.drivingschool;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

public class DrivingSchoolTest {

    private DrivingSchool drivingSchool;

    @BeforeEach
    void beforeEach() {
        drivingSchool = new DrivingSchool();
    }

    @Test
    void retrieveCalendarContainsStaticWeekdaysAndTimeSlots() {

        ScheduleGrid expectedScheduleGrid = new ScheduleGrid();

        expectedScheduleGrid.add("MON", drivingSchool.getStartHours());
        expectedScheduleGrid.add("TUE", drivingSchool.getStartHours());
        expectedScheduleGrid.add("WED", drivingSchool.getStartHours());
        expectedScheduleGrid.add("THU", drivingSchool.getStartHours());
        expectedScheduleGrid.add("FRI", drivingSchool.getStartHours());

        assertEquals(5, expectedScheduleGrid.numberOfClassDays());

        expectedScheduleGrid.add("MON", drivingSchool.getStartHours());
        expectedScheduleGrid.add("TUE", drivingSchool.getStartHours());
        expectedScheduleGrid.add("WED", drivingSchool.getStartHours());
        expectedScheduleGrid.add("THU", drivingSchool.getStartHours());
        expectedScheduleGrid.add("FRI", drivingSchool.getStartHours());

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

        InstructorSchedule instructorSchedule = drivingSchool.retrieveScheduleSheet(instructorID);
        int actual = drivingSchool.amountOfInstructorSchedules();

        assertEquals(1, actual);
        assertEquals(instructorID, instructorSchedule.getInstructorId());
        assertEquals(10, instructorSchedule.getNumberAvailableDays());
    }

    @Test
    void createMultipleScheduleSheetWithTwoInstructors() {
        int instructorID = drivingSchool.addInstructor("John", "Doe");
        int instructor2ID = drivingSchool.addInstructor("Jane", "Doe");

        InstructorSchedule instructorScheduleInstructor1 = drivingSchool.retrieveScheduleSheet(instructorID);
        InstructorSchedule instructorScheduleInstructor2 = drivingSchool.retrieveScheduleSheet(instructor2ID);
        int actual = drivingSchool.amountOfInstructorSchedules();

        assertEquals(2, actual);
        assertEquals(instructorID, instructorScheduleInstructor1.getInstructorId());
        assertEquals(instructor2ID, instructorScheduleInstructor2.getInstructorId());
    }

    @Test
    void create0ScheduleSheets() {
        int actual = drivingSchool.amountOfInstructorSchedules();

        assertEquals(0, actual);
    }

    @Test
    void create0ScheduleSheets2() {
        drivingSchool.addInstructor("John", "Doe");

        int actual = drivingSchool.amountOfInstructorSchedules();

        assertEquals(1, actual);
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
                , () -> drivingSchool.retrieveInstructorSchedule(1));
    }

    @Test
    void retrieveScheduleSheetWithInstructorParamWhenPresent() {

        int instructorID = drivingSchool.addInstructor("John", "Doe");

        InstructorSchedule instructorSchedule = drivingSchool.retrieveInstructorSchedule(instructorID);

        assertEquals(10, instructorSchedule.getClassDays().size());
        for (ClassDay classDay : instructorSchedule.getClassDays()) {
            assertEquals(instructorID, classDay.getInstructorID());
        }
    }

    @Test
        // revise
    void retrieveScheduleSheetWithInstructorParamWhen2InstructorsPresent() {

        int instructorID = drivingSchool.addInstructor("John", "Doe");
        drivingSchool.addInstructor("Jane", "Doe");

        InstructorSchedule actual = drivingSchool.retrieveInstructorSchedule(instructorID);

        assertEquals(10, actual.getClassDays().size());
        for (ClassDay classDay : actual.getClassDays()) {
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
        InstructorSchedule instructorSchedule = drivingSchool.retrieveScheduleSheet(instructorID);

        instructorSchedule.setCurrentTime(() -> LocalDateTime.of(
                2021, 8, dayOfMonth, 12, 0, 0, 0 // Friday
        ));

        // execution
        LocalDateTime earliestAvailableTime = instructorSchedule.earliestAvailableTime();

        // Assert
        assertEquals(expectedDayOfWeek, earliestAvailableTime.getDayOfWeek());
        assertEquals(expectedDayOfMonth, earliestAvailableTime.getDayOfMonth());
        assertEquals(Month.AUGUST, earliestAvailableTime.getMonth());
    }

    @ParameterizedTest
    @CsvSource({
            "12", // Thursday
            "13", // Friday
            "14", // Saturday
    })
    void assignStudent(int dayOfMonth) {
        // setup
        int studentID = drivingSchool.addStudent("Alan", "Jones");
        int instructorID = drivingSchool.addInstructor("James", "Doe");
        InstructorSchedule instructorSchedule = drivingSchool.retrieveScheduleSheet(instructorID);
        LocalDateTime before = LocalDateTime.from(instructorSchedule.earliestAvailableTime());

        instructorSchedule.setCurrentTime(() -> LocalDateTime.of(
                2021, 8, dayOfMonth, 12, 0, 0, 0 // Friday
        ));

        // execution
        drivingSchool.assignInstructor(instructorID, studentID);
        LocalDateTime after = LocalDateTime.from(instructorSchedule.earliestAvailableTime());

        // assertion
        assertNotEquals(before, after);
        assertEquals(drivingSchool.getStartHours()[0], before.getHour());
        assertEquals(drivingSchool.getStartHours()[1], after.getHour());
    }

    @Test
    void assignMoreThan4StudentsPerInstructorPerDayAssignsToTheFollowingDay() {
        // setup
        int instructorID = drivingSchool.addInstructor("James", "Doe");
        InstructorSchedule instructorSchedule = drivingSchool.retrieveScheduleSheet(instructorID);
        instructorSchedule.setCurrentTime(() -> LocalDateTime.of(
                2021, 8, 6, 12, 0, 0, 0 // Friday
        ));

        int studentID1 = drivingSchool.addStudent("Alan", "Jones");
        int studentID2 = drivingSchool.addStudent("Ellen", "Jones");
        int studentID3 = drivingSchool.addStudent("Janet", "Jones");
        int studentID4 = drivingSchool.addStudent("Alexander", "Jones");
        int studentID5 = drivingSchool.addStudent("Douglas", "Jones");
        drivingSchool.assignInstructor(instructorID, studentID1);
        drivingSchool.assignInstructor(instructorID, studentID2);
        drivingSchool.assignInstructor(instructorID, studentID3);
        drivingSchool.assignInstructor(instructorID, studentID4);
        drivingSchool.assignInstructor(instructorID, studentID5);

        // execution & assertion
        assertEquals(studentID1, instructorSchedule.getStudentIdDayHour(1, MONDAY, 9));
        assertEquals(studentID4, instructorSchedule.getStudentIdDayHour(1, MONDAY, 13));
        assertEquals(studentID5, instructorSchedule.getStudentIdDayHour(1, TUESDAY, 9));
    }

    @ParameterizedTest
    @CsvSource({
            "1,MONDAY,9",
            "2,MONDAY,10",
            "3,MONDAY,11",
            "4,MONDAY,13",
            "5,TUESDAY,9",
            "6,TUESDAY,10",
            "7,TUESDAY,11",
            "8,TUESDAY,13",
            "10,WEDNESDAY,10",
            "20,FRIDAY,13"
    })
    void assignMoreThan4StudentsPerInstructorFailsForMultipleInstructors(
            int lastStudent, DayOfWeek dayOfWeek, int hour
    ) {
        // setup
        int instructorId = drivingSchool.addInstructor("Zane", "Doe");
        InstructorSchedule instructorSchedule = drivingSchool.retrieveScheduleSheet(instructorId);
        instructorSchedule.setCurrentTime(() -> LocalDateTime.of(
                2021, 8, 6, 12, 0, 0, 0 // Friday
        ));

        for (int i = 1; i <= lastStudent; i++) {
            drivingSchool.assignInstructor(
                    instructorId, drivingSchool.addStudent("Alan" + i, "Jones")
            );
        }

        // execution & assertion

        assertEquals(instructorId, instructorSchedule.getInstructorId());
        assertEquals(lastStudent,
                instructorSchedule.getStudentIdDayHour(1, dayOfWeek, hour)
        );
    }

    static Stream<Arguments> retrieveStudentIDBasedOnInstructorIDAndDateData() {
        return Stream.of(
                Arguments.of(
                        new Assignment[]{
                                new Assignment(3, 1, MONDAY, 9)
                        },
                        new int[]{9}
                ),
                Arguments.of(
                        new Assignment[]{
                                new Assignment(4, 1, MONDAY, 10),
                                new Assignment(3, 1, MONDAY, 9)
                        },
                        new int[]{10, 9}
                ),
                Arguments.of(
                        new Assignment[]{
                                new Assignment(4, 1, MONDAY, 10),
                                new Assignment(5, 1, MONDAY, 11),
                                new Assignment(3, 1, MONDAY, 9)
                        },
                        new int[]{10, 11, 9}
                ),
                Arguments.of(
                        new Assignment[]{
                                new Assignment(3, 1, MONDAY, 9),
                                new Assignment(4, 1, MONDAY, 10),
                                new Assignment(5, 1, TUESDAY, 9)
                        },
                        new int[]{9, 10}
                ),
                Arguments.of(
                        new Assignment[]{
                                new Assignment(3, 1, MONDAY, 11),
                                new Assignment(4, 1, TUESDAY, 11),
                                new Assignment(5, 1, WEDNESDAY, 11),
                                new Assignment(6, 1, THURSDAY, 11),
                                new Assignment(7, 1, FRIDAY, 11)
                        },
                        new int[]{11}
                ),
                // [3][4][5][6][7] [3][4][5][6][7] [3][4][5][6][7] [3][4][5][6][7] [3][4][5][6][7]
                // [8][9][][][]
                Arguments.of(
                        new Assignment[]{
                                new Assignment(3, 1, MONDAY, 11),
                                new Assignment(4, 1, TUESDAY, 11),
                                new Assignment(5, 1, WEDNESDAY, 11),
                                new Assignment(6, 1, THURSDAY, 11),
                                new Assignment(7, 1, FRIDAY, 11),
                                new Assignment(8, 6, MONDAY, 11),
                                new Assignment(9, 6, TUESDAY, 11)
                        },
                        new int[]{11}
                )
        );
    }

    @ParameterizedTest(name = "[{index}] number of students - {0}")
    @MethodSource("retrieveStudentIDBasedOnInstructorIDAndDateData")
    void retrieveStudentIDBasedOnInstructorIDAndDate(
            Assignment[] assignments, int[] startHours
    ) {
        // setup
        DrivingSchool drivingSchool = new DrivingSchool(startHours);
        int instructorID = drivingSchool.addInstructor("Sherman", "Doe");
        InstructorSchedule instructorSchedule = drivingSchool.retrieveScheduleSheet(instructorID);
        instructorSchedule.setCurrentTime(() -> LocalDateTime.of(
                2021, 9, 12, 12, 0, 0, 0 // Friday
        ));

        int studentID;
        drivingSchool.addStudent("Buffer", "Bufferson");
        drivingSchool.addStudent("Buffer", "Bufferson Jr");
        for (int i = 0; i < assignments.length; i++) {
            drivingSchool.addStudent("Student" + i, "Smith");
            instructorSchedule.assignStudentID(assignments[i].getAssignedStudent());
        }
        drivingSchool.addStudent("Joe", "Bufferson");
        drivingSchool.addStudent("Joe", "Bufferson Jr");

        // execution
        for (int i = 0; i < assignments.length; i++) {
            int studentActual = instructorSchedule.getStudentIdDayHour(
                    assignments[i].getWeekIndex(),
                    assignments[i].getDayOfWeek(),
                    assignments[i].getHour()
            );

            // execution & assertion
            assertEquals(assignments[i].getAssignedStudent(),
                    studentActual,
                    String.format(
                            "Loop index = %d --> weekIndex %d - Day of Week %s - hour %d",
                            i, assignments[i].getWeekIndex(),
                            assignments[i].getDayOfWeek(),
                            assignments[i].getHour()
                    )
            );
        }
    }

    @Test
    void retrieveStudentIDBasedOnInstructorIDAndDateException() {
        // setup
        int instructorID = drivingSchool.addInstructor("Sherman", "Doe");
        InstructorSchedule instructorSchedule = drivingSchool.retrieveScheduleSheet(instructorID);
        instructorSchedule.setCurrentTime(() -> LocalDateTime.of(
                2021, 8, 6, 12, 0, 0, 0 // Friday
        ));

        assertThrows(IllegalStateException.class,
                () -> instructorSchedule.getStudentIdDayHour(1, MONDAY, 8)
        );
    }
}

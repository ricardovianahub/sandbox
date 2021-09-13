package com.aa.drivingschool;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InstructorSchedule {
    public static final int MAX_NUMBER_STUDENTS_PER_DAY = 4;
    public static final int NUMBER_OF_COURSE_WEEKS = 5;
    private final int instructorID;
    private CurrentTime currentTime;
    final int[] defaultStartHours;

    private final Map<String, Integer> assignedHours = new HashMap<>();
    private final List<ClassDay> classDays;

    public InstructorSchedule(int instructorID, int[] defaultStartHours) {
        this(instructorID, defaultStartHours, new ArrayList<>());
    }

    public InstructorSchedule(int instructorID, int[] defaultStartHours, List<ClassDay> classDays) {
        this.instructorID = instructorID;
        this.currentTime = new CurrentServerTime();
        this.defaultStartHours = defaultStartHours;
        this.classDays = classDays;
    }

    public int getInstructorId() {
        return instructorID;
    }

    public int getNumberAvailableDays() {
        return 10;
    }

    public LocalDateTime earliestAvailableTime() {
        return this.currentTime.now()
                .plusDays(addDaysPerWeekday(this.currentTime.now().getDayOfWeek()))
                .withHour(this.defaultStartHours[nextAvailableHour()]
                );
    }

    private int addDaysPerWeekday(DayOfWeek dayOfWeek) {
        int numberOfAdditionalDays = daysAddedBasedOnMaxStudents();
        switch (dayOfWeek) {
            case FRIDAY:
                return 3 + numberOfAdditionalDays + (nextAvailableHour() == 0 ? 31 : 0);
            case SATURDAY:
                return 2 + numberOfAdditionalDays;
            default:
                return 1 + numberOfAdditionalDays;
        }
    }

    // [1][2][3][4][5] - [1][][][][] - [1][][][][] - [1][][][][] - [1][][][][]
    // [6][7][][][10] - [6][7][][][10] - [6][7][][][10] - [6][7][][][10] - [6][7][][][10]
    // [11][][][][] - [][][][][] - [][][][][] - [][][][][] - [][][][][]
    // [][][][][] - [][][][][] - [][][][][] - [][][][][] - [][][][][]
    // [][][][][] - [][][][][] - [][][][][] - [][][][][] - [][][][][]
    // [][][][][] - [][][][][] - [][][][][] - [][][][][] - [][][][][]

    private int daysAddedBasedOnMaxStudents() {
        return (this.assignedHours.size() / NUMBER_OF_COURSE_WEEKS) /
                Math.min(MAX_NUMBER_STUDENTS_PER_DAY, defaultStartHours.length);
    }

    private int nextAvailableHour() {
        return (this.assignedHours.size() / NUMBER_OF_COURSE_WEEKS) %
                Math.min(MAX_NUMBER_STUDENTS_PER_DAY, defaultStartHours.length);
    }

    void setCurrentTime(CurrentTime currentTime) {
        this.currentTime = currentTime;
    }

    public void assignStudentID(int studentID) {
        LocalDateTime earliestAvailableTime = earliestAvailableTime();
        String key;
        for (int i = 1; i <= NUMBER_OF_COURSE_WEEKS; i++) {
            key = assignedHoursKey(
                    i,
                    earliestAvailableTime.getDayOfWeek(),
                    earliestAvailableTime.getHour());
            assignedHours.put(key, studentID);
        }
    }

    public int retrieveStudentForInstructorAndTime(
            int weekIndex, DayOfWeek dow, int hour
    ) {
        Integer result = assignedHours.get(assignedHoursKey(weekIndex, dow, hour));
        if (result == null) {
            throw new IllegalStateException("Key: " + assignedHoursKey(weekIndex, dow, hour));
        }
        return result;
    }

    public int getStudentIdDayHour(int weekIndex, DayOfWeek dayOfWeek, int hour) {
        Integer result = assignedHours.get(assignedHoursKey(weekIndex, dayOfWeek, hour));
        if (result == null) {
            throw new IllegalStateException();
        }
        return result;
    }

    private String assignedHoursKey(int weekIndex, DayOfWeek dow, int hour) {
        return String.format("%d-%d-%d", weekIndex, dow.getValue(), hour);
    }

    public List<ClassDay> getClassDays() {
        return Collections.unmodifiableList(this.classDays);
    }
}

// Signs that a TDD-created class is not done:
//
// - You discover that there are repetitions/duplications between the classes that
//   your main class (class under test) has as attributes (usually these classes are
//   created based on Unit Tests focused on the main class)
// - The freedom to refactor classes under control of the main class implies that
//   you shouldn't be exposing those "composed classes" to the clients, or any
//   refactoring will break bw compatibility and impact the client
// - Your Production classes still have a lot of "magic numbers" spread around. Either generalize
//   the code around them or convert them into true Constants to declare to the next person that these
//   numbers are intentionally constant
// - Delegation may represent more typing at first, but it's a insurance for
//   future refactorings. > 90% of the life of any class is maintenance. So, we should
//   invest on maintainable code, not to rush stuff out the door. The way to be faster
//   is to ease the load (small batches) *not* to rush

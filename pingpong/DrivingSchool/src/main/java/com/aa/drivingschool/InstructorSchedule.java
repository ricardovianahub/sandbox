package com.aa.drivingschool;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.SATURDAY;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class InstructorSchedule {
    public static final int MAX_NUMBER_STUDENTS_PER_DAY = 4;
    public static final int WEEKS_OF_CLASS = 5;
    public static final int WORKDAYS_IN_A_WEEK = 5;
    public static final int WEEK_CALENDAR_DAYS = 7;
    private final int instructorID;
    private CurrentTime currentTime;
    final int[] defaultTimeSlots;

    Map<DayOfWeek, Integer> addedDaysPerWeekday = new HashMap<>() {{
        put(FRIDAY, 3);
        put(SATURDAY, 2);
    }};

    private final Map<String, Integer> assignedStudentsByHour = new LinkedHashMap<>();
    private final List<ClassDay> classDays;

    public InstructorSchedule(int instructorID, int[] defaultTimeSlots) {
        this(instructorID, defaultTimeSlots, new ArrayList<>());
    }

    public InstructorSchedule(int instructorID, int[] defaultTimeSlots, List<ClassDay> classDays) {
        this.instructorID = instructorID;
        this.currentTime = new CurrentServerTime();
        this.defaultTimeSlots = defaultTimeSlots;
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
                .withHour(this.defaultTimeSlots[nextAvailableHour()]
                );
    }

    private int addDaysPerWeekday(DayOfWeek dayOfWeek) {
        int baselineWithStudentAmount =
                (int) (
                        Math.floor(this.assignedStudentsByHour.size() / weeklyAvailabilityBlock())
                                * (WEEKS_OF_CLASS + 1) * WEEK_CALENDAR_DAYS
                )
                        + daysAddedBasedOnMaxStudents();
        return baselineWithStudentAmount
                + addedDaysPerWeekday.getOrDefault(dayOfWeek, 1);
    }

    private int daysAddedBasedOnMaxStudents() {
        return numberOfStudentsThisWeek() / maxStudentsPerDay();
    }

    private int nextAvailableHour() {
        return numberOfStudentsThisWeek() % maxStudentsPerDay();
    }

    private int numberOfStudentsThisWeek() {
        return this.assignedStudentsByHour.size() % weeklyAvailabilityBlock() / WEEKS_OF_CLASS;
    }

    private int weeklyAvailabilityBlock() {
        return WORKDAYS_IN_A_WEEK * WEEKS_OF_CLASS * this.defaultTimeSlots.length;
    }

    private int maxStudentsPerDay() {
        return Math.min(MAX_NUMBER_STUDENTS_PER_DAY, defaultTimeSlots.length);
    }

    void setCurrentTime(CurrentTime currentTime) {
        this.currentTime = currentTime;
    }

    public void assignStudentID(int studentID) {
        if (assignedStudentsByHour.values().contains(studentID)) {
            throw new IllegalStateException("Duplicated student found: " + studentID);
        }
        LocalDateTime earliestAvailableTime = earliestAvailableTime();
        String key;
        int startWeekIndex = (int)
                Math.floor(this.assignedStudentsByHour.size() / weeklyAvailabilityBlock()) * 5 + 1;
        for (int i = 0; i < WEEKS_OF_CLASS; i++) {
            key = assignedHoursKey(
                    startWeekIndex + i,
                    earliestAvailableTime.getDayOfWeek(),
                    earliestAvailableTime.getHour());
            assignedStudentsByHour.put(key, studentID);
        }
    }

    public int getStudentIdDayHour(int weekIndex, DayOfWeek dayOfWeek, int hour) {
        Integer result = assignedStudentsByHour.get(assignedHoursKey(weekIndex, dayOfWeek, hour));
        guardResult(weekIndex, dayOfWeek, hour, result);
        return result;
    }

    private void guardResult(int weekIndex, DayOfWeek dayOfWeek, int hour, Integer result) {
        if (result == null) {
            throw new IllegalStateException("Key not found: " + assignedHoursKey(weekIndex, dayOfWeek, hour));
        }
    }

    private String assignedHoursKey(int weekIndex, DayOfWeek dow, int hour) {
        return String.format("%d-%d-%d", weekIndex, dow.getValue(), hour);
    }

    public List<ClassDay> getClassDays() {
        return Collections.unmodifiableList(this.classDays);
    }

    public boolean containsStudent(int studentID) {
        return false;
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

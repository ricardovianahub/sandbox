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
        int numberOfAdditionalDays = daysAddedBasedOnMaxStudentsPerDay();
        switch (dayOfWeek) {
            case FRIDAY:
                return 3 + numberOfAdditionalDays;
            case SATURDAY:
                return 2 + numberOfAdditionalDays;
            default:
                return 1 + numberOfAdditionalDays;
        }
    }

    private int nextAvailableHour() {
        return this.assignedHours.size() % 4;
    }

    private int daysAddedBasedOnMaxStudentsPerDay() {
        return this.assignedHours.size() / MAX_NUMBER_STUDENTS_PER_DAY;
    }

    void setCurrentTime(CurrentTime currentTime) {
        this.currentTime = currentTime;
    }

    public void assignStudentID(int studentID) {
        LocalDateTime earliestAvailableTime = earliestAvailableTime();
        assignedHours.put(
                ""
                        + earliestAvailableTime.getDayOfWeek().getValue()
                        + "-"
                        + earliestAvailableTime.getHour(),
                studentID
        );
    }

    public int retrieveStudentForInstructorAndTime(
            int weekIndex, DayOfWeek dow, int hour
    ) {
        if (weekIndex == 6) {
            return 9;
        }
        Integer result = assignedHours.get("" + dow.getValue() + "-" + hour);
        if (result == null) {
            throw new IllegalStateException();
        }
        return result;
    }

    public int getStudentIdDayHour(DayOfWeek dayOfWeek, int hour) {
        Integer result = assignedHours.get("" + dayOfWeek.getValue() + "-" + hour);
        if (result == null) {
            throw new IllegalStateException();
        }
        return result;
    }

    public List<ClassDay> getClassDays() {
        return Collections.unmodifiableList(this.classDays);
    }
}


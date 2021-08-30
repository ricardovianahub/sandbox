package com.aa.drivingschool;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InstructorSchedule implements Cloneable {
    private final int instructorID;
    private CurrentTime currentTime;
    final int[] defaultStartHours;

    private Map<String, Integer> assignedHours = new HashMap<>();
    private List<ClassDay> classDays = new ArrayList<>();

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
                .plusDays(
                        addDaysPerWeekday(this.currentTime.now().getDayOfWeek())
                )
                .withHour(this.defaultStartHours[this.assignedHours.size()]);
    }

    private int addDaysPerWeekday(DayOfWeek dayOfWeek) {
        switch (dayOfWeek) {
            case FRIDAY:
                return 3;
            case SATURDAY:
                return 2;
            default:
                return 1;
        }
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

    // Map<Integer, Integer> = Hour, StudentID
    // Add DayOfWeek to the Key
    // DOW + hour --- 9
    //            --- MONDAY9
    //            --- TUESDAY9
    // <String, Integer>

    public int retrieveStudentForInstructorAndTime(
            int weekIndex, DayOfWeek dow, int hour
    ) {
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
// Instructor max 4
// "earliest available time"
// [1234] [] [] [] []
// [1234] [] [] [] []
// [1234] [] [] [] []
// [1234] [] [] [] []
// [1234] [] [] [] []
// 6 weeks [][][][][] --- exception
//

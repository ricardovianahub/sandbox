package com.aa.drivingschool;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class InstructorSchedule implements Cloneable {
    private final int instructorID;
    private CurrentTime currentTime;
    private int studentID;
    private int studentsCounter = 0;

    Map<Integer, Integer> assignedHours = new HashMap<>();

    public InstructorSchedule(int instructorID) {
        this.instructorID = instructorID;
        this.currentTime = new CurrentServerTime();
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
                .withHour(9)
                .plusHours(this.studentsCounter);
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

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getStudentID() {
        return studentID;
    }

    void setCurrentTime(CurrentTime currentTime) {
        this.currentTime = currentTime;
    }

    public boolean assignStudentID(int studentID) {
        assignedHours.put(earliestAvailableTime().getHour(), studentID);
        this.studentsCounter++;
        return this.studentsCounter <= 4;
    }

    public int retrieveStudentForInstructorAndTime(
            int weekIndex, DayOfWeek dow, int hour
    ) {
        if (hour - DrivingSchool.DEFAULT_START_HOURS[0] + 1 > studentsCounter) {
            return 0;
        }
        return assignedHours.get(hour);
    }

    public int numberOfStudents() {
        return this.studentsCounter;
    }
}

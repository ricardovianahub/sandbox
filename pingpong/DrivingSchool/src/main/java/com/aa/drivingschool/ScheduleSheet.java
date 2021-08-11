package com.aa.drivingschool;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class ScheduleSheet {
    private int instructorID;
    private CurrentTime currentTime;
    private int studentID;

    public ScheduleSheet(int instructorID) {
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
        if (DayOfWeek.FRIDAY.equals(this.currentTime.now().getDayOfWeek())) {
            return this.currentTime.now().plusDays(3);
        }
        if (DayOfWeek.SATURDAY.equals(this.currentTime.now().getDayOfWeek())) {
            return this.currentTime.now().plusDays(2);
        }

        return this.currentTime.now().plusDays(1);

    }

    public void setCurrentTime(CurrentTime currentTime) {
        this.currentTime = currentTime;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void assignStudentID(int studentID) {

    }
}

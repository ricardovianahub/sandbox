package com.aa.drivingschool;

public class ScheduleSheet {
    private int instructorID;

    public ScheduleSheet(int instructorID) {
        this.instructorID = instructorID;
    }

    public int getInstructorId() {
        return instructorID;
    }

    public int getNumberAvailableDays() {
        return 10;
    }
}

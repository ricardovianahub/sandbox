package com.aa.drivingschool;

import java.time.LocalDateTime;

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

    public LocalDateTime earliestAvailableTime() {
        return LocalDateTime.of(
                2021, 8, 5, 12, 0, 0, 0 // Thursday
        );
    }

    public void setCurrentTime(CurrentTime currentTime) {

    }
}

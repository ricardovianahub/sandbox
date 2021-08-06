package com.aa.drivingschool;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class ScheduleSheet {
    private int instructorID;
    private CurrentTime currentTime;

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
//        if (DayOfWeek.FRIDAY.equals(this.currentTime.now().getDayOfWeek())
//                || DayOfWeek.SATURDAY.equals(this.currentTime.now().getDayOfWeek())) {
//            return LocalDateTime.of(
//                    2021, 8, 9, 12, 0, 0, 0 // Monday
//            );
//        }
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
}

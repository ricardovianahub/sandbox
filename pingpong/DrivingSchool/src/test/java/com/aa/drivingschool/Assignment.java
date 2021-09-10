package com.aa.drivingschool;

import java.time.DayOfWeek;

public class Assignment {
    private int assignedStudent;
    private int weekIndex;
    private DayOfWeek dayOfWeek;
    private int hour;

    public Assignment(int studId, int week, DayOfWeek dow, int hour) {
        this.assignedStudent = studId;
        this.weekIndex = week;
        this.dayOfWeek = dow;
        this.hour = hour;
    }

    public int getAssignedStudent() {
        return assignedStudent;
    }

    public int getWeekIndex() {
        return weekIndex;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public int getHour() {
        return hour;
    }
}

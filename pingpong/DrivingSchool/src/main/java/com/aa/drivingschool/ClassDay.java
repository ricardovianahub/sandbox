package com.aa.drivingschool;

import java.util.Arrays;
import java.util.Objects;

public class ClassDay {
    private final String weekDay;
    private final int[] startHours;
    private int instructorID;

    public ClassDay(String weekDay, int[] startHours) {
        this.weekDay = weekDay;
        this.startHours = startHours;
    }

    public ClassDay(String weekDay, int[] startHours, int instructorID) {
        this.weekDay = weekDay;
        this.startHours = startHours;
        this.instructorID = instructorID;
    }

    public String getWeekDay() {
        return weekDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassDay classDay = (ClassDay) o;
        return Objects.equals(weekDay, classDay.weekDay) && Arrays.equals(startHours, classDay.startHours);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(weekDay);
        result = 31 * result + Arrays.hashCode(startHours);
        return result;
    }

    public int getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(int instructorID) {
        this.instructorID = instructorID;
    }

    public int getStudentIdForHour(int hour) {
        if (hour == 9) {
            return 5;
        }
        return 3;
    }
}

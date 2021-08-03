package com.aa.drivingschool;

import java.util.Objects;

public class ScheduleGrid {

    private int numberOfClassDays;

    public void add(String dow, int[] startHours) {
        this.numberOfClassDays++;
    }

    public int numberOfClassDays() {
        return this.numberOfClassDays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleGrid that = (ScheduleGrid) o;
        return numberOfClassDays == that.numberOfClassDays;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfClassDays);
    }
}

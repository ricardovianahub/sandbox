package com.aa.drivingschool;

import java.util.Objects;

public class ClassDay {
    private final String weekDay;

    public ClassDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getWeekDay() {
        return weekDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassDay classDay = (ClassDay) o;
        return Objects.equals(weekDay, classDay.weekDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weekDay);
    }
}

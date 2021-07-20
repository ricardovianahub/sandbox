package com.aa.drivingschool;

import java.util.Arrays;
import java.util.List;


public class DrivingSchool {
    static final int[] DEFAULT_START_HOURS = {9, 10, 11, 1, 2, 3};

    public List<ClassDay> retrieveCalendar() {
        return Arrays.asList(
                new ClassDay("MON", DEFAULT_START_HOURS),
                new ClassDay("TUE", DEFAULT_START_HOURS),
                new ClassDay("WED", DEFAULT_START_HOURS),
                new ClassDay("THU", DEFAULT_START_HOURS),
                new ClassDay("FRI", DEFAULT_START_HOURS),
                new ClassDay("MON", DEFAULT_START_HOURS),
                new ClassDay("TUE", DEFAULT_START_HOURS),
                new ClassDay("WED", DEFAULT_START_HOURS),
                new ClassDay("THU", DEFAULT_START_HOURS),
                new ClassDay("FRI", DEFAULT_START_HOURS)
        );
    }

    public int addInstructor(String firstName, String lastName) {
        return 0;
    }
}

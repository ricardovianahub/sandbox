package com.aa.drivingschool;

import java.util.Arrays;
import java.util.List;

public class DrivingSchool {
    public List<ClassDay> retrieveCalendar() {
        return Arrays.asList(
                new ClassDay("MON"),
                new ClassDay("TUE"),
                new ClassDay("WED"),
                new ClassDay("THU"),
                new ClassDay("FRI"),
                new ClassDay("MON"),
                new ClassDay("TUE"),
                new ClassDay("WED"),
                new ClassDay("THU"),
                new ClassDay("FRI"));
    }
}

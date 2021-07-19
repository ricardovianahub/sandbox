package com.aa.drivingschool;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import net.bytebuddy.matcher.FilterableList;

public class DrivingSchoolTest {

    @Test
    void schedulePrintoutContainsStaticWeekdays() {
        DrivingSchool drivingSchool = new DrivingSchool();

        List<ClassDay> expectedClassDays = new ArrayList<>() {{
           add(new ClassDay("MON"));
           add(new ClassDay("TUE"));
           add(new ClassDay("WED"));
           add(new ClassDay("THU"));
           add(new ClassDay("FRI"));
           add(new ClassDay("MON"));
           add(new ClassDay("TUE"));
           add(new ClassDay("WED"));
           add(new ClassDay("THU"));
           add(new ClassDay("FRI"));
        }};

        assertArrayEquals(expectedClassDays.toArray(), drivingSchool.retrieveCalendar().toArray());
    }

}

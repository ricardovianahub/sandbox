package com.aa.drivingschool;

import static com.aa.drivingschool.DrivingSchool.DEFAULT_START_HOURS;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class DrivingSchoolTest {


    @Test
    void retrieveCalendarContainsStaticWeekdaysAndTimeSlots() {
        DrivingSchool drivingSchool = new DrivingSchool();

        List<ClassDay> expectedClassDays = new ArrayList<>() {{
           add(new ClassDay("MON", DEFAULT_START_HOURS));
           add(new ClassDay("TUE", DEFAULT_START_HOURS));
           add(new ClassDay("WED", DEFAULT_START_HOURS));
           add(new ClassDay("THU", DEFAULT_START_HOURS));
           add(new ClassDay("FRI", DEFAULT_START_HOURS));
           add(new ClassDay("MON", DEFAULT_START_HOURS));
           add(new ClassDay("TUE", DEFAULT_START_HOURS));
           add(new ClassDay("WED", DEFAULT_START_HOURS));
           add(new ClassDay("THU", DEFAULT_START_HOURS));
           add(new ClassDay("FRI", DEFAULT_START_HOURS));
        }};

        assertArrayEquals(expectedClassDays.toArray(), drivingSchool.retrieveCalendar().toArray());
    }

    @Test
    void addInstructor() {
        DrivingSchool drivingSchool = new DrivingSchool();
        String firstName = "John";
        String lastName = "Doe";
        int instructorId = drivingSchool.addInstructor(firstName, lastName);
        assertEquals(1, instructorId);
    }

}

package com.aa.drivingschool;

import java.util.*;


public class DrivingSchool {
    static final int[] DEFAULT_START_HOURS = {9, 10, 11, 1, 2, 3};
    private int instructorCounter = 0;
    private int studentCounter = 0;
    private int scheduleSheetCounter = 0;

    List<Instructor> instructorList = new ArrayList<>();

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

        Instructor instructor = new Instructor(firstName,lastName);
        if(instructorList.contains(instructor)){
            throw new IllegalStateException();
        }
        instructorList.add(instructor);

        return ++instructorCounter;
    }

    public void createScheduleSheet(int instructorID) {
        ++scheduleSheetCounter;
    }

    public int amountOfScheduleSheets() {
        return scheduleSheetCounter;
    }

    public int addStudent(String first, String last) {
        guardAddStudent(first, last);
        return ++studentCounter;
    }

    private void guardAddStudent(String first, String last) {
        if (first == null || last == null || "".equals(last) || "".equals(first)) {
            throw new IllegalArgumentException();
        }
    }
}

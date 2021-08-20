package com.aa.drivingschool;

import java.util.*;

public class DrivingSchool {
    static final int[] DEFAULT_START_HOURS = {9, 10, 11, 13, 14, 15};
    private int instructorCounter = 0;
    private int studentCounter = 0;
    private int scheduleSheetCounter = 0;

    List<Instructor> instructorList = new ArrayList<>();

    private Map<Integer, InstructorSchedule> scheduleSheets = new HashMap<>();

    public ScheduleGrid retrieveDefaultScheduleGrid() {
        ScheduleGrid scheduleGrid = new ScheduleGrid();
        scheduleGrid.add("MON", DEFAULT_START_HOURS);
        scheduleGrid.add("TUE", DEFAULT_START_HOURS);
        scheduleGrid.add("WED", DEFAULT_START_HOURS);
        scheduleGrid.add("THU", DEFAULT_START_HOURS);
        scheduleGrid.add("FRI", DEFAULT_START_HOURS);
        scheduleGrid.add("MON", DEFAULT_START_HOURS);
        scheduleGrid.add("TUE", DEFAULT_START_HOURS);
        scheduleGrid.add("WED", DEFAULT_START_HOURS);
        scheduleGrid.add("THU", DEFAULT_START_HOURS);
        scheduleGrid.add("FRI", DEFAULT_START_HOURS);

        return scheduleGrid;
    }

    private List<ClassDay> retrieveDefaultScheduleGrid(int instructorId) {
        return Arrays.asList(
                new ClassDay("MON", DEFAULT_START_HOURS, instructorId),
                new ClassDay("TUE", DEFAULT_START_HOURS, instructorId),
                new ClassDay("WED", DEFAULT_START_HOURS, instructorId),
                new ClassDay("THU", DEFAULT_START_HOURS, instructorId),
                new ClassDay("FRI", DEFAULT_START_HOURS, instructorId),
                new ClassDay("MON", DEFAULT_START_HOURS, instructorId),
                new ClassDay("TUE", DEFAULT_START_HOURS, instructorId),
                new ClassDay("WED", DEFAULT_START_HOURS, instructorId),
                new ClassDay("THU", DEFAULT_START_HOURS, instructorId),
                new ClassDay("FRI", DEFAULT_START_HOURS, instructorId)
        );
    }

    public InstructorSchedule retrieveScheduleSheet(int instructorID) {

        if (!scheduleSheets.containsKey(instructorID)) {
            ++scheduleSheetCounter;
            InstructorSchedule instructorSchedule = new InstructorSchedule(instructorID);
            scheduleSheets.put(instructorID, instructorSchedule);
        }

        return scheduleSheets.get(instructorID);
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

    public int addInstructor(String firstName, String lastName) {

        Instructor instructor = new Instructor(firstName, lastName, ++instructorCounter);
        if (instructorList.contains(instructor)) {
            throw new IllegalStateException();
        }
        instructorList.add(instructor);

        return instructor.getId();
    }

    public List<ClassDay> retrieveScheduleSheetByInstructor(int instructorId) {
        for (Instructor instructor : instructorList) {
            if (instructor.getId() == instructorId) {
                return retrieveDefaultScheduleGrid(instructorId);
            }
        }
        throw new IllegalArgumentException();
    }

    public boolean assignInstructor(int instructorID, int studentID) {
        if (!scheduleSheets.containsKey(instructorID)) {
            scheduleSheets.put(instructorID, new InstructorSchedule(instructorID));
        }
        InstructorSchedule instructorSchedule = scheduleSheets.get(instructorID);
        return instructorSchedule.assignStudentID(studentID);
    }
}

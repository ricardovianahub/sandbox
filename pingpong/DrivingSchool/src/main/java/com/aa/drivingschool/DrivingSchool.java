package com.aa.drivingschool;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DrivingSchool {
    static final int[] DEFAULT_START_HOURS = {9, 10, 11, 13, 14, 15};
    private int instructorCounter = 0;
    private int studentCounter = 0;

    private final Map<Integer, InstructorSchedule> instructorSchedules = new HashMap<>();

    private final Map<Integer, Instructor> instructors = new HashMap<>();

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

        if (!instructorSchedules.containsKey(instructorID)) {
            InstructorSchedule instructorSchedule = new InstructorSchedule(instructorID, DEFAULT_START_HOURS);
            this.instructorSchedules.put(instructorID, instructorSchedule);
        }

        return instructorSchedules.get(instructorID);
    }

    public int amountOfInstructorSchedules() {
        return this.instructorSchedules.size();
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
        if (instructors.containsValue(new Instructor(firstName, lastName))) {
            throw new IllegalStateException();
        }
        instructors.put(instructor.getId(), instructor);
        instructorSchedules.put(
                instructor.getId(),
                new InstructorSchedule(
                        instructor.getId(),
                        DEFAULT_START_HOURS,
                        retrieveDefaultScheduleGrid(instructor.getId())
                )
        );

        return instructor.getId();
    }

    public InstructorSchedule retrieveInstructorSchedule(int instructorId) {
        InstructorSchedule instructorSchedule = instructorSchedules.get(instructorId);
        if (instructorSchedule == null) {
            throw new IllegalArgumentException();
        }
        return instructorSchedule;
    }

    public boolean assignInstructor(int instructorID, int studentID) {
        if (!instructorSchedules.containsKey(instructorID)) {
            instructorSchedules.put(instructorID, new InstructorSchedule(instructorID, DEFAULT_START_HOURS));
        }
        InstructorSchedule instructorSchedule = this.instructorSchedules.get(instructorID);
        return instructorSchedule.assignStudentID(studentID);
    }
}

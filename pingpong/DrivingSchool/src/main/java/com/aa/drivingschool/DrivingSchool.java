package com.aa.drivingschool;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DrivingSchool {
    private static final int[] START_HOURS_DEFAULT = {9, 10, 11, 13, 14, 15};
    private final int[] startHours;
    private int instructorCounter = 0;
    private int studentCounter = 0;

    private final Map<Integer, InstructorSchedule> instructorSchedules = new HashMap<>();

    private final Map<Integer, Instructor> instructors = new HashMap<>();

    public DrivingSchool() {
        this(START_HOURS_DEFAULT);
    }

    public DrivingSchool(int[] startHours) {
        this.startHours = startHours;
    }

    public int[] getStartHours() {
        return this.startHours;
    }

    public ScheduleGrid retrieveDefaultScheduleGrid() {
        ScheduleGrid scheduleGrid = new ScheduleGrid();
        scheduleGrid.add("MON", new int[]{9, 10, 11, 13, 14, 15});
        scheduleGrid.add("TUE", new int[]{9, 10, 11, 13, 14, 15});
        scheduleGrid.add("WED", new int[]{9, 10, 11, 13, 14, 15});
        scheduleGrid.add("THU", new int[]{9, 10, 11, 13, 14, 15});
        scheduleGrid.add("FRI", new int[]{9, 10, 11, 13, 14, 15});
        scheduleGrid.add("MON", new int[]{9, 10, 11, 13, 14, 15});
        scheduleGrid.add("TUE", new int[]{9, 10, 11, 13, 14, 15});
        scheduleGrid.add("WED", new int[]{9, 10, 11, 13, 14, 15});
        scheduleGrid.add("THU", new int[]{9, 10, 11, 13, 14, 15});
        scheduleGrid.add("FRI", new int[]{9, 10, 11, 13, 14, 15});

        return scheduleGrid;
    }

    private List<ClassDay> retrieveDefaultScheduleGrid(int instructorId) {
        return Arrays.asList(
                new ClassDay("MON", new int[]{9, 10, 11, 13, 14, 15}, instructorId),
                new ClassDay("TUE", new int[]{9, 10, 11, 13, 14, 15}, instructorId),
                new ClassDay("WED", new int[]{9, 10, 11, 13, 14, 15}, instructorId),
                new ClassDay("THU", new int[]{9, 10, 11, 13, 14, 15}, instructorId),
                new ClassDay("FRI", new int[]{9, 10, 11, 13, 14, 15}, instructorId),
                new ClassDay("MON", new int[]{9, 10, 11, 13, 14, 15}, instructorId),
                new ClassDay("TUE", new int[]{9, 10, 11, 13, 14, 15}, instructorId),
                new ClassDay("WED", new int[]{9, 10, 11, 13, 14, 15}, instructorId),
                new ClassDay("THU", new int[]{9, 10, 11, 13, 14, 15}, instructorId),
                new ClassDay("FRI", new int[]{9, 10, 11, 13, 14, 15}, instructorId)
        );
    }

    public InstructorSchedule retrieveScheduleSheet(int instructorID) {

        if (!instructorSchedules.containsKey(instructorID)) {
            InstructorSchedule instructorSchedule =
                    new InstructorSchedule(instructorID, getStartHours());
            this.instructorSchedules.put(instructorID, instructorSchedule);
        }

        return instructorSchedules.get(instructorID);
    }

    public int amountOfInstructorSchedules() {
        return this.instructorSchedules.size();
    }

    public int addStudent(String first, String last) {
        new Student(first, last);
        //guardAddStudent(first, last);
        return ++studentCounter;
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
                        this.startHours,
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

    public void assignInstructor(int instructorID, int studentID) {
        if (!instructorSchedules.containsKey(instructorID)) {
            instructorSchedules.put(
                    instructorID,
                    new InstructorSchedule(instructorID, this.startHours)
            );
        }
        for (InstructorSchedule instructorSchedule : instructorSchedules.values()) {
            if (instructorSchedule.containsStudent(studentID)) {
                throw new IllegalStateException("Student already assigned to another instructor: "
                        + studentID);
            }
        }
        InstructorSchedule instructorSchedule = this.instructorSchedules.get(instructorID);
        instructorSchedule.assignStudentID(studentID);
    }
}

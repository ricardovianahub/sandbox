package com.aa.drivingschool;

public class Student {
    public Student(String firstName, String lastName) {
        guardConstructor(firstName, lastName);
    }

    private void guardConstructor(String first, String last) {
        if (first == null || last == null || "".equals(last) || "".equals(first)) {
            throw new IllegalArgumentException();
        }
    }

}

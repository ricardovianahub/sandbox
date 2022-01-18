package com.aa.serialization;

import java.io.Serializable;
import java.util.Objects;

public class DataRecord2 implements DataRecordTag {
    private static final long SerialVersionUID = 1L;
    private String first;
    private String last;
    private int age;

    @Override
    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    @Override
    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    @Override
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataRecord2 that = (DataRecord2) o;
        return age == that.age && Objects.equals(first, that.first) && Objects.equals(last, that.last);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, last, age);
    }
}

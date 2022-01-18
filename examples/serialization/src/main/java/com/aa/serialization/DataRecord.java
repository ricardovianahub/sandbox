package com.aa.serialization;

import java.io.Serializable;
import java.util.Objects;

public class DataRecord implements Serializable, DataRecordTag {

    private static final long SerialVersionUID = 1L;
    private String first;
    private String last;
    private Integer age;

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

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
        DataRecord that = (DataRecord) o;
        return age == that.age && Objects.equals(first, that.first) && Objects.equals(last, that.last);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, last, age);
    }
}

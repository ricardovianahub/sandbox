package com.aa.improvekataben.data;

public class ImprovementGrid {
    private String teamName;
    private String title;
    private String field1Awesome;
    private String field2Now;
    private String field3Next;
    private String field4Breakdown;

    public ImprovementGrid setTeamName(String teamName) {
        this.teamName = teamName;
        return this;
    }

    public ImprovementGrid setTitle(String title) {
        this.title = title;
        return this;
    }

    public ImprovementGrid setField1Awesome(String field1Awesome) {
        this.field1Awesome = field1Awesome;
        return this;
    }

    public ImprovementGrid setField2Now(String field2Now) {
        this.field2Now = field2Now;
        return this;
    }

    public ImprovementGrid setField3Next(String field3Next) {
        this.field3Next = field3Next;
        return this;
    }

    public ImprovementGrid setField4Breakdown(String field4Breakdown) {
        this.field4Breakdown = field4Breakdown;
        return this;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getTitle() {
        return title;
    }

    public String getField1Awesome() {
        return field1Awesome;
    }

    public String getField2Now() {
        return field2Now;
    }

    public String getField3Next() {
        return field3Next;
    }

    public String getField4Breakdown() {
        return field4Breakdown;
    }
}

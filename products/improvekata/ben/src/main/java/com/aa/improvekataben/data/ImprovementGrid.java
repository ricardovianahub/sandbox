package com.aa.improvekataben.data;

import java.time.OffsetDateTime;

public class ImprovementGrid {
    private String uniqueId;
    private OffsetDateTime createdAt;
    private String teamName;
    private String title;
    private String field1Awesome;
    private String field2Now;
    private String field3Next;
    private String field4Breakdown;

    public String getUniqueId() {
        return uniqueId;
    }

    public ImprovementGrid setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
        return this;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public ImprovementGrid setCreatedAt(String createdAt) {
        this.createdAt = OffsetDateTime.parse(createdAt.replace(' ', 'T').substring(0, 19) + "Z");
        return this;
    }

    public String getTeamName() {
        return teamName;
    }

    public ImprovementGrid setTeamName(String teamName) {
        this.teamName = teamName;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public ImprovementGrid setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getField1Awesome() {
        return field1Awesome;
    }

    public ImprovementGrid setField1Awesome(String field1Awesome) {
        this.field1Awesome = field1Awesome;
        return this;
    }

    public String getField2Now() {
        return field2Now;
    }

    public ImprovementGrid setField2Now(String field2Now) {
        this.field2Now = field2Now;
        return this;
    }

    public String getField3Next() {
        return field3Next;
    }

    public ImprovementGrid setField3Next(String field3Next) {
        this.field3Next = field3Next;
        return this;
    }

    public String getField4Breakdown() {
        return field4Breakdown;
    }

    public ImprovementGrid setField4Breakdown(String field4Breakdown) {
        this.field4Breakdown = field4Breakdown;
        return this;
    }
}

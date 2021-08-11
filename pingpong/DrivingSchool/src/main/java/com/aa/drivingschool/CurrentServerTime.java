package com.aa.drivingschool;

import java.time.LocalDateTime;

public class CurrentServerTime implements CurrentTime {
    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}

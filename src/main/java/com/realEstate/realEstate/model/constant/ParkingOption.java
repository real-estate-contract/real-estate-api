package com.realEstate.realEstate.model.constant;

public enum ParkingOption {
    DEDICATED("전용 주차 시설"),
    SHARED("공동 주차 시설"),
    OTHER("그 밖의 주차 시설");

    private final String displayName;

    ParkingOption(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

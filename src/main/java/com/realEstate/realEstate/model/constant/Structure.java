package com.realEstate.realEstate.model.constant;

public enum Structure {
    APART("아파트"),
    VILLA("빌라/투룸+"),
    ONE_ROOM("원룸"),
    OFFICETEL("오피스텔");

    private final String displayName;

    Structure(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}


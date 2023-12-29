package com.realEstate.realEstate.model.constant;

public enum Structure {
    OPEN_STUDIO("오픈형 원룸"),
    ONE_ROOM("분리형 원룸"),
    TWO_ROOM("투룸"),
    THREE_ROOM("쓰리룸"),
    FOUR_ROOM_PLUS("포룸 +");

    private final String displayName;

    Structure(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}


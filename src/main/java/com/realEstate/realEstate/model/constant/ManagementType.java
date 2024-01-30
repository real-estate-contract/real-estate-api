package com.realEstate.realEstate.model.constant;

public enum ManagementType {
    OUTSOURCED("위탁 관리"),
    SELF_MANAGED("자체 관리"),
    OTHER("그 밖의 유형");

    private final String displayName;

    ManagementType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}

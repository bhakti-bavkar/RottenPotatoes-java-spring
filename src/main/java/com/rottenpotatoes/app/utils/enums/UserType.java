package com.rottenpotatoes.app.utils.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.catalina.User;

public enum UserType {
    ADULT("ADULT"), CHILD("CHILD"), TEEN("TEEN"), SENIOR("SENIOR");

    private final String value;

    UserType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static UserType fromValue(String value) throws Exception {
        if (value != null) {
            for (UserType type : values()) {
                if (type.value.equals(value))
                    return type;
            }
        }
        throw new Exception("Given UserType " + value + " not found in enum");
    }
}

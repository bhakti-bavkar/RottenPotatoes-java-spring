package com.rottenpotatoes.app.utils.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Ratings{
    G("G"), PG("PG"), PG_13("PG_13"), R("R"), NC_17("NC_17");

    private final String value;

    Ratings(String value){
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static Ratings fromValue(String value) throws Exception {
        if(value != null){
            for(Ratings rate: values()){
                if(rate.value.equals(value))
                    return rate;
            }
        }
        throw new Exception("Given Ratings " + value + " not found in enum");
    }

}

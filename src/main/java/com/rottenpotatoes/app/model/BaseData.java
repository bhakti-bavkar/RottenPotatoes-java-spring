package com.rottenpotatoes.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
public class BaseData implements Serializable {
    @Id
    private String id;

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }
}


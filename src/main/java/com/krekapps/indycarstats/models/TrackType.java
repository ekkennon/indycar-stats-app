package com.krekapps.indycarstats.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by raefo on 10-Jul-17.
 */
public class TrackType {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String type;

    public TrackType() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

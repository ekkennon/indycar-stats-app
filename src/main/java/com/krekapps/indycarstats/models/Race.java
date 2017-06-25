package com.krekapps.indycarstats.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by ekk on 25-Jun-17.
 */

@Entity
public class Race {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String name;

    public Race() {
    }

    public Race(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

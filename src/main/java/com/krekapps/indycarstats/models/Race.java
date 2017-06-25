package com.krekapps.indycarstats.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany
    @JoinColumn(name="race_id")
    private List<RaceSession> sessions = new ArrayList<>();

    public Race() {
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

    public List<RaceSession> getSessions() {
        return sessions;
    }

    public void addSession(RaceSession session) {
        sessions.add(session);
    }

    public void setSessions(ArrayList<RaceSession> sessions) {
        this.sessions = sessions;
    }
}

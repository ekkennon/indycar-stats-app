package com.krekapps.indycarstats.models;

import javax.persistence.*;
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

    @ManyToOne
    private Season season;

    @OneToMany
    @JoinColumn(name="race_id")
    private List<Session> sessions = new ArrayList<>();

    @ManyToOne
    private Track track;

    public Race() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void addSession(Session session) {
        sessions.add(session);
    }

    public void setSessions(ArrayList<Session> sessions) {
        this.sessions = sessions;
    }
}

package com.krekapps.indycarstats.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ekk on 25-Jun-17.
 */

@Entity
public class Season {

    @Id
    @NotNull
    private int year;

    @OneToMany
    @JoinColumn(name="season_id")
    private List<Race> races = new ArrayList<>();

    @OneToMany
    @JoinColumn(name="session_id")
    private List<Team> teams = new ArrayList<>();

    public Season() {
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Race> getRaces() {
        return races;
    }

    public void setRaces(List<Race> races) {
        this.races = races;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
}

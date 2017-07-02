package com.krekapps.indycarstats.models.forms;

import com.krekapps.indycarstats.models.Race;

import javax.validation.constraints.NotNull;

/**
 * Created by ekk on 27-Jun-17.
 */
public class SessionAddForm {

    @NotNull
    private String name;

    @NotNull
    private int raceid;

    @NotNull
    private String loggedin;

    private Iterable<Race> races;

    public SessionAddForm() {
    }

    public SessionAddForm(Race r) {
    }

    public SessionAddForm(Iterable<Race> races, String loggedin) {
        this.races = races;
        this.loggedin = loggedin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRaceid() {
        return raceid;
    }

    public void setRaceid(int raceid) {
        this.raceid = raceid;
    }

    public String getLoggedin() {
        return loggedin;
    }

    public void setLoggedin(String loggedin) {
        this.loggedin = loggedin;
    }

    public Iterable<Race> getRaces() {
        return races;
    }

    public void setRaces(Iterable<Race> races) {
        this.races = races;
    }
}

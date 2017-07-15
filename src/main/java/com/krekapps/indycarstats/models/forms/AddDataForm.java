package com.krekapps.indycarstats.models.forms;

import com.krekapps.indycarstats.models.Driver;
import com.krekapps.indycarstats.models.Race;
import com.krekapps.indycarstats.models.Season;
import com.krekapps.indycarstats.models.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by ekk on 11-Jul-17.
 */
public class AddDataForm {

    private String loggedin;
    private Iterable<Season> seasons;
    private int year;
    private int teamid;
    private Iterable<Race> races;
    private int raceid;
    private List<Driver> drivers = new ArrayList<>();
    private int driverid;

    public String getLoggedin() {
        return loggedin;
    }

    public void setLoggedin(String loggedin) {
        this.loggedin = loggedin;
    }

    public Iterable<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(Iterable<Season> seasons) {
        this.seasons = seasons;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTeamid() {
        return teamid;
    }

    public void setTeamid(int teamid) {
        this.teamid = teamid;
    }

    public Iterable<Race> getRaces() {
        return races;
    }

    public void setRaces(Iterable<Race> races) {
        this.races = races;
    }

    public int getRaceid() {
        return raceid;
    }

    public void setRaceid(int raceid) {
        this.raceid = raceid;
    }

    public void addDriver(Driver d) {
        drivers.add(d);
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> driverslist) {
        this.drivers = driverslist;
    }

    public int getDriverid() {
        return driverid;
    }

    public void setDriverid(int driverid) {
        this.driverid = driverid;
    }

}

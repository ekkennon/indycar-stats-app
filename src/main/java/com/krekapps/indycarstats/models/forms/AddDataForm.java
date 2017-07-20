package com.krekapps.indycarstats.models.forms;

import com.krekapps.indycarstats.models.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ekk on 11-Jul-17.
 */
public class AddDataForm {

    private String loggedin;
    private Iterable<Season> seasons;
    private int year;
    private Iterable<Session> sessions;
    private int sessionid;
    private Iterable<Race> races;
    private int raceid;
    private List<Driver> drivers = new ArrayList<>();
    private int driverid;
    private DriverResult stat;

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

    public Iterable<Session> getSessions() {
        return sessions;
    }

    public void setSessions(Iterable<Session> sessions) {
        this.sessions = sessions;
    }

    public int getSessionid() {
        return sessionid;
    }

    public void setSessionid(int sessionid) {
        this.sessionid = sessionid;
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

    public DriverResult getStat() {
        return stat;
    }

    public void setStat(DriverResult stat) {
        this.stat = stat;
    }
}

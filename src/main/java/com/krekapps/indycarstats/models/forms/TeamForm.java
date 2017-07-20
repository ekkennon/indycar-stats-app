package com.krekapps.indycarstats.models.forms;

import com.krekapps.indycarstats.models.Driver;
import com.krekapps.indycarstats.models.Season;

import javax.validation.constraints.NotNull;

/**
 * Created by ekk on 27-Jun-17.
 */
public class TeamForm {

    @NotNull
    private String name;

    @NotNull
    private int year;

    @NotNull
    private String loggedin;

    private String twitterHandle;
    private int id;
    private int[] driverids;
    private Iterable<Season> seasons;
    private Iterable<Driver> drivers;

    public TeamForm() {
    }

    public TeamForm(Iterable<Season> seasons, Iterable<Driver> drivers, String loggedin) {
        this.seasons = seasons;
        this.drivers = drivers;
        this.loggedin = loggedin;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getLoggedin() {
        return loggedin;
    }

    public void setLoggedin(String loggedin) {
        this.loggedin = loggedin;
    }

    public int[] getDriverids() {
        return driverids;
    }

    public void setDriverids(int[] driverids) {
        this.driverids = driverids;
    }

    public Iterable<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(Iterable<Season> seasons) {
        this.seasons = seasons;
    }

    public Iterable<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(Iterable<Driver> drivers) {
        this.drivers = drivers;
    }

    public String getTwitterHandle() {
        return twitterHandle;
    }

    public void setTwitterHandle(String twitterHandle) {
        this.twitterHandle = twitterHandle;
    }
}

package com.krekapps.indycarstats.models.forms;

import com.krekapps.indycarstats.models.Driver;
import com.krekapps.indycarstats.models.Season;

import javax.validation.constraints.NotNull;

/**
 * Created by ekk on 27-Jun-17.
 */
public class TeamAddForm {

    @NotNull
    private String name;

    @NotNull
    private int year;

    private int[] driverids;
    private Iterable<Season> seasons;
    private Iterable<Driver> drivers;

    public TeamAddForm() {
    }

    public TeamAddForm(Iterable<Season> seasons, Iterable<Driver> drivers) {
        this.seasons = seasons;
        this.drivers = drivers;
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
}

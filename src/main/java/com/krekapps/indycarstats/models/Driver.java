package com.krekapps.indycarstats.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ekk on 25-Jun-17.
 */

@Entity
public class Driver {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String name;

    private String twitterHandle;

    @ManyToMany(mappedBy = "drivers")
    private List<Team> teams;

    @OneToMany
    @JoinColumn(name="driver_id")
    private List<DecimalStat> decimalStats = new ArrayList<>();

    @OneToMany
    @JoinColumn(name="driver_id")
    private List<IntStat> intStats = new ArrayList<>();

    @OneToMany
    @JoinColumn(name="driver_id")
    private List<StringStat> stringStats = new ArrayList<>();

    @OneToMany
    @JoinColumn(name="driver_id")
    private List<TimeStat> timeStats = new ArrayList<>();

    public Driver() {
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

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<DecimalStat> getDecimalStats() {
        return decimalStats;
    }

    public void setDecimalStats(List<DecimalStat> decimalStats) {
        this.decimalStats = decimalStats;
    }

    public List<IntStat> getIntStats() {
        return intStats;
    }

    public void setIntStats(List<IntStat> intStats) {
        this.intStats = intStats;
    }

    public List<StringStat> getStringStats() {
        return stringStats;
    }

    public void setStringStats(List<StringStat> stringStats) {
        this.stringStats = stringStats;
    }

    public List<TimeStat> getTimeStats() {
        return timeStats;
    }

    public void setTimeStats(List<TimeStat> timeStats) {
        this.timeStats = timeStats;
    }

    public String getTwitterHandle() {
        return twitterHandle;
    }

    public void setTwitterHandle(String twitterHandle) {
        this.twitterHandle = twitterHandle;
    }
}

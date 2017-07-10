package com.krekapps.indycarstats.models.forms;

import com.krekapps.indycarstats.models.Driver;
import com.krekapps.indycarstats.models.Season;
import com.krekapps.indycarstats.models.Track;

import javax.validation.constraints.NotNull;

/**
 * Created by ekk on 27-Jun-17.
 */
public class RaceForm {

    @NotNull
    private String name;

    @NotNull
    private int year;

    @NotNull
    private int trackid;

    @NotNull
    private String loggedin;

    private int id;
    private Iterable<Season> seasons;
    private Iterable<Track> tracks;

    public RaceForm() {
    }

    public RaceForm(Iterable<Season> seasons, Iterable<Track> tracks, String loggedin) {
        this.seasons = seasons;
        this.tracks = tracks;
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

    public int getTrackid() {
        return trackid;
    }

    public void setTrackid(int trackid) {
        this.trackid = trackid;
    }

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

    public Iterable<Track> getTracks() {
        return tracks;
    }

    public void setTracks(Iterable<Track> tracks) {
        this.tracks = tracks;
    }
}

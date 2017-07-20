package com.krekapps.indycarstats.models.forms;

/**
 * Created by ekk on 09-Jul-17.
 */

public class DriverInTeam {
    private int id;
    private String name;
    private boolean inTeam;

    public DriverInTeam(int id, String name, boolean inTeam) {
        this.id = id;
        this.name = name;
        this.inTeam = inTeam;
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

    public boolean isInTeam() {
        return inTeam;
    }

    public void setInTeam(boolean inTeam) {
        this.inTeam = inTeam;
    }
}

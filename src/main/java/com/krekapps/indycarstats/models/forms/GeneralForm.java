package com.krekapps.indycarstats.models.forms;

import javax.validation.constraints.NotNull;

/**
 * Created by ekk on 08-Jul-17.
 */
//for use with TeamController and DriverController
public class GeneralForm {

    @NotNull
    private String loggedin;

    private String twitterHandle;
    private int id;
    private String name;

    public GeneralForm() {
    }

    public String getLoggedin() {
        return loggedin;
    }

    public void setLoggedin(String loggedin) {
        this.loggedin = loggedin;
    }

    public String getTwitterHandle() {
        return twitterHandle;
    }

    public void setTwitterHandle(String twitterHandle) {
        this.twitterHandle = twitterHandle;
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
}

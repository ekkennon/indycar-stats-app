package com.krekapps.indycarstats.models;

/**
 * Created by ekk on 28-Jun-17.
 */
public class AdminSession {
    private boolean signedIn;

    public AdminSession() {
    }

    public AdminSession(boolean signedIn) {
        this.signedIn = signedIn;
    }

    public boolean isSignedIn() {
        return signedIn;
    }

    public String isSignedInString() {
        return Boolean.toString(signedIn);
    }

    public void setSignedIn(boolean signedIn) {
        this.signedIn = signedIn;
    }

    public void setSignedIn(String signedIn) {
        this.signedIn = Boolean.parseBoolean(signedIn);
    }
}

package com.krekapps.indycarstats.models;

import javax.validation.constraints.NotNull;
import java.util.Calendar;
import org.springframework.data.annotation.Id;

/**
 * Created by ekk on 15-Jul-17.
 */

public class DriverResult {

    @Id
    private String id;

    private int carNum;
    private int startPos;
    private int endPos;

    //private int driverid;
    //private int sessionid;
    //private String driverName;
    //private String raceName;

    private int driverID;
    private int sessionID;

    /*
    private int lapsLed;
    private int lapsFinished;
    private int points;
    private int totalPitStops;
*/
    private String status;
/*
    private double averageSpeed;

    private Calendar totalTime;
    */

    public DriverResult() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCarNum() {
        return carNum;
    }

    public void setCarNum(int carNum) {
        this.carNum = carNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getStartPos() {
        return startPos;
    }

    public void setStartPos(int startPos) {
        this.startPos = startPos;
    }

    public int getEndPos() {
        return endPos;
    }

    public void setEndPos(int endPos) {
        this.endPos = endPos;
    }
/*
    public int getDriverid() {
        return driverid;
    }

    public void setDriverid(int driverid) {
        this.driverid = driverid;
    }

    public int getSessionid() {
        return sessionid;
    }

    public void setSessionid(int sessionid) {
        this.sessionid = sessionid;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }
*/

    public int getDriverID() {
        return driverID;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    public int getSessionID() {
        return sessionID;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }

    @Override
    public String toString() {
        return "DriverResult{" +
                "id='" + id + '\'' +
                ", carNum=" + carNum +
                ", startPos=" + startPos +
                ", endPos=" + endPos +
                ", driverID=" + driverID +
                ", sessionID=" + sessionID +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DriverResult that = (DriverResult) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

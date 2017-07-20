package com.krekapps.indycarstats.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import org.springframework.data.annotation.Id;

/**
 * Created by ekk on 15-Jul-17.
 */

//@Entity
public class DriverResult {

    //@GeneratedValue
    @Id
    private String id;

    //private Session session;
    //private Driver driver;

    private int carNum;
    private int startPos;
    private int endPos;

    private int driverid;
    private int sessionid;

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

    @Override
    public String toString() {
        return "DriverResult{" +
                "id='" + id + '\'' +
                ", carNum=" + carNum +
                ", startPos=" + startPos +
                ", endPos=" + endPos +
                ", driverid=" + driverid +
                ", sessionid=" + sessionid +
                ", status='" + status + '\'' +
                '}';
    }
}

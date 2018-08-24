package com.compus.netbus.bean;

/**
 * Created by NOknow on 2018/05/22/0022.
 */

public class Station {

    private String sId;
    private String sName;
    private double latitude;
    private double longitude;
    private int zIndex;

    public Station() {
    }

    public Station(String sId, String sName, double latitude, double longitude, int zIndex) {
        this.sId = sId;
        this.sName = sName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.zIndex = zIndex;
    }

    @Override
    public String toString() {
        return "Station{" +
                "sId='" + sId + '\'' +
                ", sName='" + sName + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", zIndex=" + zIndex +
                '}';
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getzIndex() {
        return zIndex;
    }

    public void setzIndex(int zIndex) {
        this.zIndex = zIndex;
    }
}

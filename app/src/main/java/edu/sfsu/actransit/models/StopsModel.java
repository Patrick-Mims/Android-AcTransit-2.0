package edu.sfsu.actransit.models;

public class StopsModel {
    private String name;
    private String scheduledTime;
    private double latitude;
    private double longitude;
    private int stopId;

    public StopsModel(double latitude, double longitude, String name, String scheduledTime, int stopId) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.scheduledTime = scheduledTime;
        this.stopId = stopId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(String scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public int getStopId() {
        return stopId;
    }

    public void setStopId(int stopId) {
        this.stopId = stopId;
    }
}
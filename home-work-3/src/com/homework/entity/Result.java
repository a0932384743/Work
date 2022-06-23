package com.homework.entity;

public class Result {
    private double distance;
    private String abaloneClass;

    public Result(double distance, String abaloneClass) {
        this.distance = distance;
        this.abaloneClass = abaloneClass;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getAbaloneClass() {
        return abaloneClass;
    }

    public void setAbaloneClass(String abaloneClass) {
        this.abaloneClass = abaloneClass;
    }
}

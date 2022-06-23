package com.homework.entity;

public class GamePlayer {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    private int points;

    public GamePlayer(String name, int points) {
        this.name = name;
        this.points = points;
    }


}

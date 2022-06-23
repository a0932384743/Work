package com.homework.entity;

public class Abalone {

    private String sex;

    private Double length;

    private Double diameter;

    private Double height;

    private Double totalWeight;

    private Double weight;

    private Double visceralWeight;

    @Override
    public String toString() {
        return sex + "," +
                length + "," +
                diameter + "," +
                height + "," +
                totalWeight + "," +
                weight + "," +
                visceralWeight + "," +
                shellWeight + "," +
                ageRing + ",";
    }

    private Double shellWeight;

    private Double ageRing;

    public Abalone(String sex, Double length, Double diameter, Double height, Double totalWeight, Double weight, Double visceralWeight, Double shellWeight, Double ageRing) {
        this.sex = sex;
        this.length = length;
        this.diameter = diameter;
        this.height = height;
        this.totalWeight = totalWeight;
        this.weight = weight;
        this.visceralWeight = visceralWeight;
        this.shellWeight = shellWeight;
        this.ageRing = ageRing;
    }

    public String getSex() {
        return sex;
    }

    public Double getLength() {
        return length;
    }

    public Double getDiameter() {
        return diameter;
    }

    public Double getHeight() {
        return height;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public Double getWeight() {
        return weight;
    }

    public Double getVisceralWeight() {
        return visceralWeight;
    }

    public Double getShellWeight() {
        return shellWeight;
    }

    public Double getAgeRing() {
        return ageRing;
    }
}

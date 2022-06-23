package com.homework.entity;

public class Traveler {

    private Long id;
    private Boolean isAlive = true;
    private String ticketType;
    private String travelerName;
    private String sex;
    private Integer age;
    private Integer siblingNum;
    private Integer parentNum;
    private String ticketSeries;
    private Integer price;
    private String boardSeries;
    private String port;

    public Traveler(Long id, Boolean isAlive, String ticketType, String travelerName, String sex, Integer age, Integer siblingNum, Integer parentNum, String ticketSeries, Integer price, String boardSeries, String port) {
        this.id = id;
        this.isAlive = isAlive;
        this.ticketType = ticketType;
        this.travelerName = travelerName;
        this.sex = sex;
        this.age = age;
        this.siblingNum = siblingNum;
        this.parentNum = parentNum;
        this.ticketSeries = ticketSeries;
        this.price = price;
        this.boardSeries = boardSeries;
        this.port = port;
    }

    public Long getId() {
        return id;
    }

    public Boolean getAlive() {
        return isAlive;
    }

    public void setAlive(Boolean alive) {
        isAlive = alive;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getTravelerName() {
        return travelerName;
    }

    public void setTravelerName(String travelerName) {
        this.travelerName = travelerName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSiblingNum() {
        return siblingNum;
    }

    public void setSiblingNum(Integer siblingNum) {
        this.siblingNum = siblingNum;
    }

    public Integer getParentNum() {
        return parentNum;
    }

    public void setParentNum(Integer parentNum) {
        this.parentNum = parentNum;
    }

    public String getTicketSeries() {
        return ticketSeries;
    }

    public void setTicketSeries(String ticketSeries) {
        this.ticketSeries = ticketSeries;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getBoardSeries() {
        return boardSeries;
    }

    public void setBoardSeries(String boardSeries) {
        this.boardSeries = boardSeries;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "Traveler{" +
                "id=" + id +
                ", isAlive=" + isAlive +
                ", ticketType='" + ticketType + '\'' +
                ", travelerName='" + travelerName + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", siblingNum=" + siblingNum +
                ", parentNum=" + parentNum +
                ", ticketSeries='" + ticketSeries + '\'' +
                ", price=" + price +
                ", boardSeries='" + boardSeries + '\'' +
                ", port='" + port + '\'' +
                '}';
    }
}

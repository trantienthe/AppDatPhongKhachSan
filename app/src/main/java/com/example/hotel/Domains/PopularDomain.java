package com.example.hotel.Domains;

import java.io.Serializable;

public class PopularDomain implements Serializable {


    private int MP;
    private String title;
    private String location;
    private String description;
    private String bed;
    private String state;
    private double score;
    private String pic;
    private boolean wifi;
    private int price;

    public PopularDomain() {
        this.MP = MP;
        this.title = title;
        this.location = location;
        this.description = description;
        this.bed = bed;
        this.state = state;
        this.score = score;
        this.pic = pic;
        this.wifi = wifi;
        this.price = price;
    }

    public int getMP() {
        return MP;
    }

    public void setMP(int MP) {
        this.MP = MP;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

package com.fis.hotelmanagementapp.models;

public class Details {

    private String parking;

    private String spa;

    private String pool;

    private String food;

    private String location;

    private String rooms;

    public Details(String parking, String spa, String pool, String food, String location, String rooms) {
        this.parking = parking;
        this.spa = spa;
        this.pool = pool;
        this.food = food;
        this.location = location;
        this.rooms = rooms;
    }

    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }

    public String getSpa() {
        return spa;
    }

    public void setSpa(String spa) {
        this.spa = spa;
    }

    public String getPool() {
        return pool;
    }

    public void setPool(String pool) {
        this.pool = pool;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }
}

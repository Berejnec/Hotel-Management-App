package com.fis.hotelmanagementapp;

public class Room {
    private int number;

    private String type;

    private String status;

    public Room(int number, String type, String status) {
        this.number = number;
        this.type = type;
        this.status = status;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

package com.wan.college.model;

/**
 * Created by 万文杰 on 2017/2/9.
 */

public class TestModel {
    private int id;
    private String myname;
    private float price;
    private double lat;

    public String getMyname() {
        return myname;
    }

    public void setMyname(String myname) {
        this.myname = myname;
    }

    private long uid;

    public TestModel(){

    }
    public TestModel(int id, String myname, float price, double lat, long uid) {
        this.id = id;
        this.myname = myname;
        this.price = price;
        this.lat = lat;
        this.uid = uid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }
}

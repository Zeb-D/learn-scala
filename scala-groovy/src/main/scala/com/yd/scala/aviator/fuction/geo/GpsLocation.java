package com.yd.scala.aviator.fuction.geo;

import java.io.Serializable;

/**
 * GPS点
 */
public class GpsLocation implements Serializable {

    private static final long serialVersionUID = 87028724323378438L;


    private double lat;
    private double lng;

    public GpsLocation() { 
    }

    public GpsLocation(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public void setLat(double lat) {
        this.lat=lat;
    }
    public double getLat() {
        return this.lat;
    }


    public void setLng(double lng) {
        this.lng=lng;
    }
    public double getLng() {
        return this.lng;
    }

    /**
     * 从经纬度字符串解析,注意是纬度在前面
     */
    public static GpsLocation parseLocation(String latLngStr) {
        String[] array = latLngStr.split(",");
        double lat = Double.parseDouble(array[0]);
        double lng = Double.parseDouble(array[1]);
        return new GpsLocation(lat,lng);
    }

    public String toString() {
        return String.valueOf(lat) + String.valueOf(lng);
    }

}

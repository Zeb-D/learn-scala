package com.yd.scala.aviator.util;


import com.yd.scala.aviator.fuction.geo.GpsLocation;

import java.util.ArrayList;

/**
 * Gps经续度工具类
 */
public class GpsUtil {

    /**
     * 两点之间的距离,单位为米
     */
    public static double distFrom(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = (earthRadius * c);

        return dist;
    }

    /**
     * 两点之间的距离,单位为米
     */
    public static double distFrom(GpsLocation loc1, GpsLocation loc2) {
        double lat1 = loc1.getLat();
        double lng1 = loc1.getLng();
        double lat2 = loc2.getLat();
        double lng2 = loc2.getLng();
        return distFrom(lat1, lng1, lat2, lng2);
    }

    /**
     * 判断一点是不是在多边型里面
     */
    public static boolean isPointInPolygon(GpsLocation tap, ArrayList<GpsLocation> vertices) {
        int intersectCount = 0;
        for (int j = 0; j < vertices.size() - 1; j++) {
            if (rayCastIntersect(tap, vertices.get(j), vertices.get(j + 1))) {
                intersectCount++;
            }
        }

        return ((intersectCount % 2) == 1); // odd = inside, even = outside;
    }

    private static boolean rayCastIntersect(GpsLocation tap, GpsLocation vertA, GpsLocation vertB) {

        double aY = vertA.getLat();
        double bY = vertB.getLat();
        double aX = vertA.getLng();
        double bX = vertB.getLng();
        double pY = tap.getLat();
        double pX = tap.getLng();

        if ((aY > pY && bY > pY) || (aY < pY && bY < pY)
                || (aX < pX && bX < pX)) {
            return false; // a and b can't both be above or below pt.y, and a or
            // b must be east of pt.x
        }

        double m = (aY - bY) / (aX - bX); // Rise over run
        double bee = (-aX) * m + aY; // y = mx + b
        double x = (pY - bee) / m; // algebra is neat!

        return x > pX;
    }


    /**
     * 和google地图的测量工具比较过, 基本准确.
     */
    public static void main(String[] args) {

        double lat1 = 39.970522, lng1 = 116.491934;
        double lat2 = 39.970456, lng2 = 116.491810;

        long start = System.currentTimeMillis();
        double distance = distFrom(lat1, lng1, lat2, lng2);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(distance);


        lat1 = 30.280275;
        lng1 = 120.099853;

        lat2 = 30.275975;
        lng2 = 120.100742;


        distance = distFrom(lat1, lng1, lat2, lng2);
        System.out.println(distance);

        double lat11 = 30.277654, lng11 = 120.095572;
        double lat12 = 30.276465, lng12 = 120.094563;
        double lat13 = 30.275056, lng13 = 120.096002;
        double lat14 = 30.276446, lng14 = 120.097815;

        ArrayList<GpsLocation> vertices = new ArrayList<GpsLocation>();
        vertices.add(new GpsLocation(lat11, lng11));
        vertices.add(new GpsLocation(lat12, lng12));
        vertices.add(new GpsLocation(lat13, lng13));
        vertices.add(new GpsLocation(lat14, lng14));


        double tapLat11 = 30.276718, tapLng11 = 120.095332;
        boolean in = isPointInPolygon(new GpsLocation(tapLat11, tapLng11), vertices);
        System.out.println(in);


    }

}

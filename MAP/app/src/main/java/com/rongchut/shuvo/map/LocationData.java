package com.rongchut.shuvo.map;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by ASUS on 09-Dec-16.
 */

public class LocationData {

    private LatLng location;
    private String name;
    private String address;

    public LocationData(LatLng location, String name, String address) {
        this.location = location;
        this.name = name;
        this.address = address;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

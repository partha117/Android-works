package com.rongchut.shuvo.map;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.LoaderManager;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by ASUS on 05-Dec-16.
 */

abstract public class MyLocation {

    private Context context;
    private double lat;
    private double lang;
    private LocationManager service;
    private int distance;

    public MyLocation(Context context,int distance) {
        this.context = context;
        service = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        this.distance=distance;
        boolean enabled;
        enabled = service
                .isProviderEnabled(LocationManager.GPS_PROVIDER);


        if (!enabled) {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            context.startActivity(intent);
        }
        /*enabled=service.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if(!enabled)
        {
            Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
            context.startActivity(intent);
        }*/
        /*enabled=haveNetworkConnection(context);
        if(!enabled)
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setClassName("com.android.phone", "com.android.phone.NetworkSetting");
            context.startActivity(intent);
        }*/


        findLocation();


    }
    abstract void onChange(double lat,double lang);


    private void findLocation() {


        /*Location location=service.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        lat=location.getLatitude();
        lang=location.getLongitude();*/


        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                lat = location.getLatitude();
                lang = location.getLongitude();
                onChange(lat,lang);

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        service.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, distance, locationListener);
        LocationListener locationListener1=new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                lat = location.getLatitude();
                lang = location.getLongitude();
                onChange(lat,lang);

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };
        service.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,distance,locationListener1);
    }

    public double getLat() {
        return lat;
    }

    public double getLang() {
        return lang;
    }

    private boolean haveNetworkConnection(Context context) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if(ni!=null) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }

        return haveConnectedWifi || haveConnectedMobile;
    }
}

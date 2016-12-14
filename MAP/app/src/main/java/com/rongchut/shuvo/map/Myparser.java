package com.rongchut.shuvo.map;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ASUS on 09-Dec-16.
 */

public class Myparser {

    JSONObject jsonObject;

    public Myparser(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public ArrayList<LocationData> getData()
    {
        ArrayList<LocationData>data=new ArrayList<>();
        try {
            JSONArray results=jsonObject.getJSONArray("results");
            for(int i=0;i<results.length();i++)
            {
                JSONObject jsonObject=results.getJSONObject(i);
                double lat=((jsonObject.getJSONObject("geometry")).getJSONObject("location")).getDouble("lat");
                double lng=((jsonObject.getJSONObject("geometry")).getJSONObject("location")).getDouble("lng");
                String name=jsonObject.getString("name");
                String address=jsonObject.getString("vicinity");
                LocationData loc=new LocationData(new LatLng(lat,lng),name,address);
                data.add(loc);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }catch (NullPointerException e)
        {
            e.printStackTrace();
        }
        return data;

    }
}

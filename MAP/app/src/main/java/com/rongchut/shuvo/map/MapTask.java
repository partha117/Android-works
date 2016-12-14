package com.rongchut.shuvo.map;

import android.os.AsyncTask;
import android.os.Looper;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by ASUS on 08-Dec-16.
 */

abstract class MapTask extends AsyncTask<Object, Object, JSONObject> {

    private JSONObject jsonObject;

    @Override
    protected void onPostExecute(JSONObject Boolean) {
        super.onPostExecute(Boolean);
        System.out.println(jsonObject); //use jsonObject here
        Myparser myparser=new Myparser(jsonObject);

        finishJob(myparser.getData());
    }

    abstract void finishJob(ArrayList<LocationData> data);
    @Override
    protected JSONObject doInBackground(Object... strings) {
        HttpURLConnection conn = null;
        try {
            Looper.prepare();
            Object latitude = strings[0];
            Object longitude = strings[1];
            Object radius = strings[2];
            Object name = strings[3];
            String key = "AIzaSyC-fMHqfkR0LSjJuH1ZqNKaLXNieZ-COyc";

            String uri = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?"
                    + "location=" + latitude + "," + longitude
                    + "&radius=" + radius
                    + "&name=" + name
                    + "&key="+ key+"&language=bn"; // you can add more options here

            



            URL url = new URL(uri);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            InputStream stream = conn.getInputStream();
            BufferedReader bReader = new BufferedReader(new InputStreamReader(stream, "utf-8"), 8);
            StringBuilder sBuilder = new StringBuilder();

            String line = null;
            while ((line = bReader.readLine()) != null) {
                sBuilder.append(line + "\n");
            }

            stream.close();
            jsonObject = new JSONObject(sBuilder.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            conn.disconnect();
            
        }
        return  jsonObject;
        
    }
    
}

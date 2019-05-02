package com.example.earthquake;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public final class QuakeQueryUtils {

    private QuakeQueryUtils(){}

    public static ArrayList<Earthquake> extractQuery(){
        ArrayList<Earthquake> earthquakes = new ArrayList<Earthquake>();

        try {

            Log.v("QuakeQueryUtils","Start ---- ");

            URL url1 = new URL("https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2019-04-23&endtime=2019-04-24");
            HttpURLConnection conn = (HttpURLConnection)url1.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            String js = "";

            Scanner sc = new Scanner(url1.openStream());
            while(sc.hasNext())
            {
                js+=sc.nextLine();
            }
            sc.close();

            Log.v("QuakeQueryUtils","Response = " + js);


            JSONObject jsonObject = new JSONObject(js);
            JSONArray features = jsonObject.getJSONArray("features");
            for(int i=0;i< features.length();i++){
                JSONObject item = features.getJSONObject(i);
                JSONObject prop = item.getJSONObject("properties");

                double mag = prop.getDouble("mag");
                String place = prop.getString("place");
                long time = prop.getLong("time");

                String url = prop.getString("url");

                earthquakes.add(new Earthquake(mag,place,time,url));
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return earthquakes;
    }
}

package com.example.earthquake;

import android.content.Intent;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ArrayList<Earthquake> details = QuakeQueryUtils.extractQuery();

        ListView listView = (ListView) findViewById(R.id.list);

        /*EarthquakeAdapter arrayAdapter = new EarthquakeAdapter(this,details);

        listView.setAdapter(arrayAdapter);*/

        final EarthquakeAdapter adapter = new EarthquakeAdapter(this,details);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Earthquake earthquake = adapter.getItem(position);

                Uri uri = Uri.parse(earthquake.getUrl());

                Intent web = new Intent(Intent.ACTION_VIEW,uri);

                startActivity(web);
            }
        });
    }
}

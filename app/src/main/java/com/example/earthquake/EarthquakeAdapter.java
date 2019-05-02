package com.example.earthquake;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(Context context, ArrayList<Earthquake> earthquakes){
        super(context,0, earthquakes);
    }

    private String formatDate(Date obj){
        SimpleDateFormat format = new SimpleDateFormat("dd MMM, yyyy");
        return format.format(obj);
    }

    private String formatTime(Date obj){
        SimpleDateFormat time = new SimpleDateFormat("h:mm a");
        return time.format(obj);
    }

    private String formatDouble(Double obj){
        DecimalFormat format = new DecimalFormat("0.0");
        return format.format(obj);
    }

    private int getMagnitudeColor(double obj){
        int magnitudeCOlor;
        int obj1 = (int) Math.floor(obj);
        switch(obj1){
            case 0:
            case 1: magnitudeCOlor = R.color.magnitude1;
                    break;
            case 2: magnitudeCOlor = R.color.magnitude2;
                    break;
            case 3: magnitudeCOlor = R.color.magnitude3;
                    break;
            case 4: magnitudeCOlor = R.color.magnitude4;
                    break;
            case 5: magnitudeCOlor = R.color.magnitude5;
                    break;
            case 6: magnitudeCOlor = R.color.magnitude6;
                    break;
            case 7: magnitudeCOlor = R.color.magnitude7;
                    break;
            case 8: magnitudeCOlor = R.color.magnitude8;
                    break;
            case 9: magnitudeCOlor = R.color.magnitude9;
                    break;
            default: magnitudeCOlor = R.color.magnitude10plus;
                    break;
        }
        return ContextCompat.getColor(getContext(),magnitudeCOlor);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listview = convertView;
        if(listview == null){
            listview = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        Earthquake currentItem = getItem(position);

        TextView magnitude = (TextView) listview.findViewById(R.id.magnitude);
        magnitude.setText(formatDouble(currentItem.getMagnitude()));

        GradientDrawable magCircle = (GradientDrawable) magnitude.getBackground();
        int magColor = getMagnitudeColor(currentItem.getMagnitude());
        magCircle.setColor(magColor);

        TextView place = (TextView) listview.findViewById(R.id.city_name);
        TextView place1 = (TextView) listview.findViewById(R.id.city_name1);

        String pname = currentItem.getPlace();

        if(pname.contains("of")){
            String places[] = currentItem.getPlace().split("of ");
            place.setText(places[0]+"of");
            place1.setText(places[1]);
        } else{
            place.setText("Near the");
            place1.setText(pname);
        }


        TextView date = (TextView) listview.findViewById(R.id.date);
        Date obj = new Date(currentItem.getDate());
        date.setText(formatDate(obj));

        TextView time = (TextView) listview.findViewById(R.id.time);
        time.setText(formatTime(obj));

        return listview;
    }
}

package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import static android.support.v4.content.ContextCompat.getColor;

/**
 * Created by up22 on 28/06/2017.
 */


public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // check if the current view is reused else inflate the view
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_item, parent, false);
        }

        //get the object located at position
        Earthquake item = getItem(position);


        TextView mag_text_view = (TextView) listItemView.findViewById(R.id.earthquake_mag);
        DecimalFormat decimalFormat = new DecimalFormat("0.0");

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) mag_text_view.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(item.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);
        mag_text_view.setText(decimalFormat.format(item.getMagnitude()));

        String location[] = splitLocation(item.getLocation()," of ");

        TextView location_offset_text_view = (TextView) listItemView.findViewById(R.id.earthquake_place_offset);
        location_offset_text_view.setText(location[0]);

        TextView location_text_view = (TextView) listItemView.findViewById(R.id.earthquake_place);
        location_text_view.setText(location[1]);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy", Locale.US);
        TextView event_date_text_view = (TextView) listItemView.findViewById(R.id.earthquake_date);
        event_date_text_view.setText(dateFormat.format(item.getEventDate()));

        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a", Locale.US);
        TextView event_time_text_view = (TextView) listItemView.findViewById(R.id.earthquake_time);
        event_time_text_view.setText(timeFormat.format(item.getEventDate()));

        return listItemView;
    }

    private int getMagnitudeColor(Double magnitude) {
        int magnitudeColor;

        switch (magnitude.intValue()){
            case 0:
            case 1:
                magnitudeColor = getColor(getContext(), R.color.magnitude1);
                break;
            case 2:
                magnitudeColor = getColor(getContext(), R.color.magnitude2);
                break;
            case 3:
                magnitudeColor = getColor(getContext(), R.color.magnitude3);
                break;
            case 4:
                magnitudeColor = getColor(getContext(), R.color.magnitude4);
                break;
            case 5:
                magnitudeColor = getColor(getContext(), R.color.magnitude5);
                break;
            case 6:
                magnitudeColor = getColor(getContext(), R.color.magnitude6);
                break;
            case 7:
                magnitudeColor = getColor(getContext(), R.color.magnitude7);
                break;
            case 8:
                magnitudeColor = getColor(getContext(), R.color.magnitude8);
                break;
            case 9:
                magnitudeColor = getColor(getContext(), R.color.magnitude9);
                break;
            default:
                magnitudeColor = getColor(getContext(), R.color.magnitude10plus);
                break;
        }

        return magnitudeColor;
    }

    private String[] splitLocation(String text, String separator){
        String[] location = new String[2];
        if (text.contains(separator)) {
            location = text.split(separator);
            location[0] = location[0] + separator;
        } else {
            location[0] = getContext().getString(R.string.near_the);
            location[1] = text;
        }
        return location;
    }
}

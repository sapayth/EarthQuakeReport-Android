package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.R.attr.resource;
import static com.example.android.quakereport.R.id.magnitude;

/**
 * Created by sapayth on 10/13/17.
 */

public class QuaksAdaptar extends ArrayAdapter<Earthquake> {

    private static final String LOG_TAG = QuaksAdaptar.class.getSimpleName();

    public QuaksAdaptar(Activity context, ArrayList<Earthquake> quaks) {
        super(context, 0, quaks);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Earthquake currentQuak = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView measureTextView = (TextView) listItemView.findViewById(magnitude);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        measureTextView.setText(currentQuak.getQuakMeasure());

        String[] locations = splitLocation(currentQuak.getLocation());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView locationTextView = (TextView) listItemView.findViewById(R.id.location);

        // set the location in location TextView
        locationTextView.setText(locations[0]);

        TextView countryTextView = (TextView) listItemView.findViewById(R.id.locationCountry);
        // set the country in country TextView
        countryTextView.setText(locations[1].trim());

        // Find the TextView in the list_item.xml layout with the ID time
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time);
        // Find the TextView in the list_item.xml layout with the ID date
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);

        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(currentQuak.getUnixtime());
        // Find the TextView with view ID date
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        // Find the TextView with view ID time
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView

        // get and show the date
        dateTextView.setText(formattedDate);

        // get and show the time
        timeTextView.setText(formattedTime);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) measureTextView.getBackground();

        double magDouble = Double.parseDouble(currentQuak.getQuakMeasure());
        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(magDouble);

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    /**
     * Return split array for the location
     */
    private String[] splitLocation(String location) {
        String[] splits = new String[2];
        if (location.contains(",")) {
            splits = location.split(",");
        } else {
            splits[0] = "Near the";
            splits[1] = location;
        }
        return splits;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}

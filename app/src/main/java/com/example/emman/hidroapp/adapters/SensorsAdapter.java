package com.example.emman.hidroapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.emman.hidroapp.R;

import java.util.List;

import pojo.Line;
import pojo.Sensor;
import pojo.SensorData;

/**
 * Created by emman on 4/24/2016.
 */
public class SensorsAdapter extends ArrayAdapter<SensorData> {
    public SensorsAdapter(Context context, List items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        SensorData line = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_sensor, parent, false);
        }
        // Lookup view for data population
        TextView sensorIDTextView = (TextView) convertView.findViewById(R.id.sensorIDTextView);
        TextView sensorValueTextView = (TextView) convertView.findViewById(R.id.currentValueTextView);
        TextView sensorAvgTextView = (TextView) convertView.findViewById(R.id.avgValueTextView);
        // Populate the data into the template view using the data object
        sensorIDTextView.setText(line.getSensor().getSensorId());
        sensorValueTextView.setText(line.getValue());

        //farmLocationTextView.setText(farm.getFarmLocation());
        // Return the completed view to render on screen
        return convertView;
    }
}

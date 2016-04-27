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
import pojo.Ship;

/**
 * Created by emman on 4/24/2016.
 */
public class LinesAdapter extends ArrayAdapter<Line> {
    public LinesAdapter(Context context,  List items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Line line = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_line, parent, false);
        }
        // Lookup view for data population
        TextView lineNameTextView = (TextView) convertView.findViewById(R.id.lineName);
        //TextView farmLocationTextView = (TextView) convertView.findViewById(R.id.farmLocation);
        // Populate the data into the template view using the data object
        lineNameTextView.setText(line.getLineName());
        //farmLocationTextView.setText(farm.getFarmLocation());
        // Return the completed view to render on screen
        return convertView;
    }
}

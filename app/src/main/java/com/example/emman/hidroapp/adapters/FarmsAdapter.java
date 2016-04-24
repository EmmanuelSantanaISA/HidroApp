package com.example.emman.hidroapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.emman.hidroapp.R;

import java.util.ArrayList;
import java.util.List;

import pojo.Farm;

/**
 * Created by emman on 4/24/2016.
 */
public class FarmsAdapter extends ArrayAdapter<Farm> {
    public FarmsAdapter(Context context, List<Farm> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Farm farm = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_farm, parent, false);
        }
        // Lookup view for data population
        TextView farmNameTextView = (TextView) convertView.findViewById(R.id.farmName);
        TextView farmLocationTextView = (TextView) convertView.findViewById(R.id.farmLocation);
        // Populate the data into the template view using the data object
        farmNameTextView.setText(farm.getFarmName());
        farmLocationTextView.setText(farm.getFarmLocation());
        // Return the completed view to render on screen
        return convertView;
    }
}

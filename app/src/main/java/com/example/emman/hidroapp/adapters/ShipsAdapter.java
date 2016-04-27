package com.example.emman.hidroapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.emman.hidroapp.R;

import java.util.List;

import pojo.Farm;
import pojo.Ship;

/**
 * Created by emman on 4/24/2016.
 */
public class ShipsAdapter extends ArrayAdapter<Ship> {
    public ShipsAdapter(Context context, List<Ship> ships) {
        super(context, 0, ships);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Ship farm = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_ship, parent, false);
        }
        // Lookup view for data population
        TextView farmNameTextView = (TextView) convertView.findViewById(R.id.shipName);
        TextView shipIDTextView = (TextView) convertView.findViewById(R.id.shipID);
        //TextView farmLocationTextView = (TextView) convertView.findViewById(R.id.farmLocation);
        // Populate the data into the template view using the data object
        farmNameTextView.setText(farm.getShipName());
        shipIDTextView.setText(String.valueOf("ID: " +farm.getShipId()));
        // Return the completed view to render on screen
        return convertView;
    }
}

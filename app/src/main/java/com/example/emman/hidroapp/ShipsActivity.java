package com.example.emman.hidroapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Date;

import pojo.Farm;

public class ShipsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ships);
        TextView farmNameTextView = (TextView) findViewById(R.id.textView3);
        TextView farmLocationTextView = (TextView) findViewById(R.id.textView4);
        TextView farmStartDateTextView = (TextView) findViewById(R.id.textView5);

        Farm value = (Farm) getIntent().getExtras().getSerializable("SelectedFarm");
        farmNameTextView.setText(value.getFarmName());
        farmLocationTextView.setText(value.getFarmLocation());
        Date startDate = value.getStartDate();
        if (startDate != null) {
            farmStartDateTextView.setText(value.getStartDate().toString());
        }else{
            farmStartDateTextView.setText(R.string.farm_date_empty);
        }


    }
}

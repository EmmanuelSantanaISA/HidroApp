package com.example.emman.hidroapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emman.hidroapp.R;
import com.example.emman.hidroapp.adapters.LinesAdapter;
import com.example.emman.hidroapp.tasks.AsyncTaskCallback;
import com.example.emman.hidroapp.tasks.LinesTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import main.HidroAPI;
import main.HidroConfig;
import pojo.Line;
import pojo.Ship;

public class LinesActivity extends AppCompatActivity implements AsyncTaskCallback {
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_lines);
        TextView farmStartDateTextView = (TextView) findViewById(R.id.textView4);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowHomeEnabled(false);
//        toolbar.setVisibility(View.GONE);

        lv = (ListView) findViewById(R.id.listView2);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Line selectedLine = (Line) parent.getItemAtPosition(position);
                //Toast.makeText(getApplication().getBaseContext(), "Farm Name: " + selectedFarm.getFarmName(), Toast.LENGTH_LONG).show();
                openReader(selectedLine);
            }
        });

        Ship selectedItem = (Ship) getIntent().getExtras().getSerializable("SelectedShip");
        Date startDate = selectedItem.getStartDate();
        if (startDate != null) {
            String formatMX = "dd-MM-yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(formatMX, Locale.US);
            String dateString = sdf.format(selectedItem.getStartDate());
            farmStartDateTextView.setText(dateString);
        } else {
            farmStartDateTextView.setText(selectedItem.getClass().getSimpleName() + R.string.xyz_date_empty);
        }
        setTitle(selectedItem.getClass().getSimpleName() + " " + selectedItem.getShipName());

        HidroConfig conf = new HidroConfig(getString(R.string.server_ip), Integer.parseInt(getString(R.string.server_port)), "HidroGuys/webresources");
        HidroAPI api = new HidroAPI(conf);
        LinesTask shipsTask = new LinesTask(this, selectedItem, api);
        shipsTask.execute();
    }

    @Override
    public void updateData(List items) {
        if (items != null) {
            LinesAdapter adapter = new LinesAdapter(this, items);
            ListView listView = (ListView) findViewById(R.id.listView2);
            listView.setAdapter(adapter);
        } else {
            Toast.makeText(LinesActivity.this, "Lines not found", Toast.LENGTH_SHORT).show();
        }

    }

    public void openReader(Line line) {
        Intent intent = new Intent(this, ReadersActivity.class);
        intent.putExtra("SelectedLine", line);
        startActivity(intent);
    }
}

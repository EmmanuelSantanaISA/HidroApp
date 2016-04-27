package com.example.emman.hidroapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.emman.hidroapp.R;
import com.example.emman.hidroapp.adapters.ShipsAdapter;
import com.example.emman.hidroapp.tasks.AsyncTaskCallback;
import com.example.emman.hidroapp.tasks.ShipsTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import main.HidroAPI;
import main.HidroConfig;
import pojo.Farm;
import pojo.Line;
import pojo.Ship;

public class ShipsActivity extends AppCompatActivity implements AsyncTaskCallback {
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_ships);
        TextView selectedFarmName = (TextView) findViewById(R.id.textView2);
        TextView farmLocationTextView = (TextView) findViewById(R.id.textView4);
        TextView farmStartDateTextView = (TextView) findViewById(R.id.textView5);

        lv = (ListView) findViewById(R.id.listView2);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Ship selectedLine = (Ship)parent.getItemAtPosition(position);
                //Toast.makeText(getApplication().getBaseContext(), "Farm Name: " + selectedFarm.getFarmName(), Toast.LENGTH_LONG).show();
                openLine(selectedLine);
            }
        });

        Farm selectedItem = (Farm) getIntent().getExtras().getSerializable("SelectedFarm");
        selectedFarmName.setText(selectedItem.getFarmName() + " Farm");
        farmLocationTextView.setText(selectedItem.getFarmLocation());
        Date startDate = selectedItem.getStartDate();
        if (startDate != null) {
            String formatMX = "dd-MM-yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(formatMX, Locale.US);
            String dateString = sdf.format(selectedItem.getStartDate());
            farmStartDateTextView.setText(dateString);
        }else{
            farmStartDateTextView.setText(selectedItem.getClass().getSimpleName() + R.string.xyz_date_empty);
        }

        setTitle(selectedItem.getFarmName() + getString(R.string.farm_title));

        HidroConfig conf = new HidroConfig(getString(R.string.server_ip), Integer.parseInt(getString(R.string.server_port)), "HidroGuys/webresources");
        HidroAPI api = new HidroAPI(conf);
        ShipsTask shipsTask = new ShipsTask(this, selectedItem, api);
        shipsTask.execute();
    }

    @Override
    public void updateData(List items) {
        if (items != null) {
            ShipsAdapter adapter = new ShipsAdapter(this, items);
            ListView listView = (ListView) findViewById(R.id.listView2);
            listView.setAdapter(adapter);
        }else{
            Toast.makeText(this, "Ships not found", Toast.LENGTH_SHORT).show();
        }

    }

    public void openLine(Ship ship) {
        Intent intent = new Intent(this, LinesActivity.class);
        intent.putExtra("SelectedShip", ship);
        startActivity(intent);
    }
}

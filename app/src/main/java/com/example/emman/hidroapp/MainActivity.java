package com.example.emman.hidroapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.emman.hidroapp.adapters.FarmsAdapter;
import com.example.emman.hidroapp.tasks.FarmsCallback;
import com.example.emman.hidroapp.tasks.FarmsTask;

import java.util.ArrayList;
import java.util.List;

import main.HidroAPI;
import main.HidroConfig;
import pojo.Farm;

public class MainActivity extends AppCompatActivity implements FarmsCallback {
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        setContentView(R.layout.content_main);

        lv = (ListView) findViewById(R.id.listView);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Farm selectedFarm = (Farm)parent.getItemAtPosition(position);
                Toast.makeText(getApplication().getBaseContext(), "Farm Name: " + selectedFarm.getFarmName(), Toast.LENGTH_LONG).show();
                openFarm(selectedFarm);
            }
        });

        // Instanciating an array list (you don't need to do this,
        // you already have yours).
        try {
            HidroConfig conf = new HidroConfig("192.168.1.64", 8080, "HidroGuys/webresources");
            HidroAPI api = new HidroAPI(conf);
            FarmsTask farmsTask = new FarmsTask(this);
            farmsTask.execute(api);

            // This is the array adapter, it takes the context of the activity as a
            // first parameter, the type of list view as a second parameter and your
            // array as a third parameter.

        } catch (Exception ex) {
            System.out.print("Error: " + ex.getMessage());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void updateFarms(List<Farm> farms) {
        // Construct the data source

// Create the adapter to convert the array to views
        FarmsAdapter adapter = new FarmsAdapter(this, farms);
// Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    public void openFarm(Farm farm) {
        Intent intent = new Intent(this, ShipsActivity.class);
        intent.putExtra("SelectedFarm", farm);
        startActivity(intent);
    }
}

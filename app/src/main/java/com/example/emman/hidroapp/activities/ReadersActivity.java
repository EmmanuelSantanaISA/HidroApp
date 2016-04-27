package com.example.emman.hidroapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.example.emman.hidroapp.R;
import com.example.emman.hidroapp.adapters.LinesAdapter;
import com.example.emman.hidroapp.adapters.SensorsAdapter;
import com.example.emman.hidroapp.tasks.AsyncTaskCallback;
import com.example.emman.hidroapp.tasks.AsyncTaskSensorsCallback;
import com.example.emman.hidroapp.tasks.LinesTask;
import com.example.emman.hidroapp.tasks.ReadersTask;
import com.example.emman.hidroapp.tasks.SensorsTask;

import java.util.List;

import main.HidroAPI;
import main.HidroConfig;
import pojo.Line;
import pojo.Reader;
import pojo.Sensor;

public class ReadersActivity extends AppCompatActivity implements AsyncTaskCallback, AsyncTaskSensorsCallback {
    private TextView readerName;
    private HidroAPI api;
    private HidroConfig conf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readers);

        TextView lineNameTextView = (TextView) findViewById(R.id.lineNameReadersTextView);
        readerName = (TextView) findViewById(R.id.readerNameReadersTextView);

        Line selectedItem = (Line) getIntent().getExtras().getSerializable("SelectedLine");
        if (selectedItem != null) {
            lineNameTextView.setText(selectedItem.getLineName());

            setTitle(selectedItem.getClass().getSimpleName() + " " + selectedItem.getLineName());
             conf = new HidroConfig(getString(R.string.server_ip), Integer.parseInt(getString(R.string.server_port)), "HidroGuys/webresources");
             api = new HidroAPI(conf);
            ReadersTask readersTask = new ReadersTask(this, selectedItem, api);
            readersTask.execute();
        } else {
            System.out.println("Empty line item from previous activity");
        }


    }

    @Override
    public void updateData(List items) {
        Reader sensor = (Reader) items.get(0);
        readerName.setText(sensor.getReaderName());
        SensorsTask sensorsTask = new SensorsTask(this, sensor, api);
        sensorsTask.execute();
    }

    @Override
    public void updateSensors(List<Sensor> items) {
        SensorsAdapter adapter = new SensorsAdapter(this, items);
        ListView listView = (ListView) findViewById(R.id.listView3);
        listView.setAdapter(adapter);
    }
}

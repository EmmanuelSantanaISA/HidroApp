package com.example.emman.hidroapp.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.emman.hidroapp.R;

import java.util.List;

import main.HidroAPI;
import pojo.Reader;
import pojo.Ship;

/**
 * Created by emman on 4/24/2016.
 */
public class SensorsTask extends AsyncTask<String, Integer, List> {
    private AsyncTaskSensorsCallback callback;
    private ProgressDialog dialog;
    private Context context;
    private String className;
    private Reader selectedReader;
    private HidroAPI api;

    public SensorsTask(Context context, Reader selectedReader, HidroAPI api) {
        this.callback = (AsyncTaskSensorsCallback) context;
        this.context = context;
        this.selectedReader = selectedReader;
        this.api = api;
        dialog = new ProgressDialog(context);
        className = Ship.class.getSimpleName();
    }

    @Override
    protected void onPreExecute() {
        this.dialog.setMessage(context.getString(R.string.loading_farms_message) + " " + className + "s");
        this.dialog.setTitle(className);
        this.dialog.show();
    }

    @Override
    protected List doInBackground(String... params) {
        List items = api.getSensors().getByReaderID(selectedReader.getReaderId());

        return items;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(List s) {

        if (dialog.isShowing()) {
            dialog.dismiss();
        }

        if (s != null) {
            //Toast.makeText(context,  s.size() + " " + context.getString(R.string.farms_loaded), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, className + context.getString(R.string.xyzNotFound), Toast.LENGTH_LONG).show();
        }
        callback.updateSensors(s);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected void onCancelled(List s) {
        super.onCancelled(s);
    }

}



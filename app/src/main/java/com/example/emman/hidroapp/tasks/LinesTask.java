package com.example.emman.hidroapp.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.emman.hidroapp.R;

import java.util.List;

import main.HidroAPI;
import pojo.Ship;

/**
 * Created by emman on 4/24/2016.
 */
public class LinesTask extends AsyncTask<String, Integer, List> {
    private AsyncTaskCallback callback;
    private ProgressDialog dialog;
    private Context context;
    private String className;
    private Ship selectedShip;
    private HidroAPI api;

    public LinesTask(Context context, Ship selectedFarm, HidroAPI api) {
        this.callback = (AsyncTaskCallback) context;
        this.context = context;
        this.selectedShip = selectedFarm;
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
        List items = api.getLines().getByShipID(selectedShip.getShipId());

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
        callback.updateData(s);
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



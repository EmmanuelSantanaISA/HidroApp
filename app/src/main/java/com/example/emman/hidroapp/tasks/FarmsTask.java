package com.example.emman.hidroapp.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.emman.hidroapp.R;

import java.util.List;

import main.HidroAPI;
import pojo.Farm;

/**
 * Created by emman on 4/24/2016.
 */
public class FarmsTask extends AsyncTask<HidroAPI, Integer, List<Farm>> {
    private FarmsCallback callback;
    private ProgressDialog dialog;
    private Context context;

    public FarmsTask(Context context) {
        this.callback = (FarmsCallback) context;
        this.context = context;
        dialog = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        this.dialog.setMessage("Registering user...");
        this.dialog.setTitle("Register");
        this.dialog.show();
    }

    @Override
    protected List<Farm> doInBackground(HidroAPI... params) {
        List<Farm> farms = params[0].getFarms().getAll();

        return farms;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(List<Farm> s) {

        if (dialog.isShowing()) {
            dialog.dismiss();
        }

        if (s != null) {
            Toast.makeText(context,  s.size() + context.getString(R.string.farms_loaded), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, context.getString(R.string.farms_not_loaded), Toast.LENGTH_LONG).show();
        }
        callback.updateFarms(s);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected void onCancelled(List<Farm> s) {
        super.onCancelled(s);
    }

}



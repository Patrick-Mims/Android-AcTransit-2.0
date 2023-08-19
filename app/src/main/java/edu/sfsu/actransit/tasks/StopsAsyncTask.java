package edu.sfsu.actransit.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

import java.util.ArrayList;

import edu.sfsu.actransit.adapters.StopsAdapter;
import edu.sfsu.actransit.models.StopsModel;

/*
 * Author: Patrick Mims
 * Date: August 16, 2023
 * Purpose: An AsyncTask is used to perform an asynchronous task in the background.
 * */

public class StopsAsyncTask extends AsyncTask<String, Integer, String> {
    private Context context;
    ProgressBar progressBar;

    ProgressDialog progressDialog;
    RecyclerView recyclerView;
    private final ArrayList<StopsModel> model;

    public StopsAsyncTask(Context context, ArrayList<StopsModel> model, ProgressBar progressBar, RecyclerView recyclerView) {
        this.context = context;
        this.model = model;
        this.progressBar = progressBar;
        this.recyclerView = recyclerView;
    }

    /* onPreExecute()
     * Runs on the UI thread before doInBackground(Params).
     * Invoked directly by execute(Params) or executeOnExecutor(Executor, Params).
     * The default version does nothing. This method must be called from the main thread of your app.
     * */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog =  new ProgressDialog(context);
        progressDialog.setMessage("Downloading...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {
        String line;
        HttpURLConnection httpURLConnection = null;

        try {
            URL url = new URL(params[0]);

            Log.i("BG", params[0]);

            httpURLConnection = (HttpURLConnection) url.openConnection();

            int code = httpURLConnection.getResponseCode();

            if (code != 200)
                throw new IOException("Invalid response from server: " + code);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

            StringBuilder builder = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line).append("\n");
            }

            if (builder.length() == 0) {
                return null;
            }

            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }

        return null;
    }

    protected void onProgressUpdate(Integer... progress) {
        super.onProgressUpdate(progress[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        Log.i("Post", " * * * \n * onPostExecute  \n * * *");

        try {
            JSONArray list = new JSONArray(result);

            Log.i("Model", " * * * \n * Hello Model \n * * *");

            for(int i = 0; i < list.length(); i++) {
                model.add(new StopsModel(
                        list.getJSONObject(i).getDouble("Latitude"),
                        list.getJSONObject(i).getDouble("Longitude"),
                        list.getJSONObject(i).getString("Name"),
                        list.getJSONObject(i).getString("ScheduledTime"),
                        list.getJSONObject(i).getInt("StopId")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        this.recyclerView.setAdapter(new StopsAdapter(model));
    }
}
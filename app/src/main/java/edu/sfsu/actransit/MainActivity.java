package edu.sfsu.actransit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ProgressBar;

import java.util.ArrayList;

import edu.sfsu.actransit.models.StopsModel;
import edu.sfsu.actransit.tasks.StopsAsyncTask;

public class MainActivity extends AppCompatActivity {
    ArrayList<StopsModel> model;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        model = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        new StopsAsyncTask(MainActivity.this, model, progressBar, recyclerView).execute("https://api.actransit.org/transit/stops/?token=010910F3B6021AE11917D7EFDC2F26CD");
    }
}
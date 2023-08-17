package edu.sfsu.actransit.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.sfsu.actransit.R;
import edu.sfsu.actransit.models.StopsModel;
/*
* File: StopsAdapter
* Author: Patrick Mims
* Date: August 15, 2023
*
* Layout managers aren't particular useful until you have data for them to display;
* that data is provided by the RecyclerView.adapter; which have two important roles:
* 1. The initial creation of the Views to display, including inflating the appropriate layout.
* 2. The creation of the View Holders you'll use to "bind" the View elements to the underlying data source.
* */

public class StopsAdapter extends RecyclerView.Adapter<StopsAdapter.ViewHolder> {

    private final ArrayList<StopsModel> model;

    public StopsAdapter(ArrayList<StopsModel> model) {
        this.model = model;
    }

    /* The Adapter's onCreateViewHolder method is called to get a new RecyclerView.ViewHolder instance whenever
     * the layout manager doesn't have an unused View to reuse - typically only enough views to fill the screen.
     * */

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StopsModel mod = model.get(position);
        holder.tv_name.setText(String.format("%s", mod.getName()));
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    /* Make the elements within the View's layout available for th adapter to bind data to them. */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final RecyclerView recyclerView;
        public final TextView tv_name;
        /*
        public final TextView tv_latitude;
        public final TextView tv_longitude;
        public final TextView tv_scheduledTime;
        public final TextView tv_stopId;
        */
        public ViewHolder(@NonNull View view) {
            super(view);
            recyclerView = view.findViewById(R.id.recyclerView);
            tv_name = view.findViewById(R.id.name);
            /*
            tv_latitude = view.findViewById(R.id.latitude);
            tv_longitude = view.findViewById(R.id.longitude);
            tv_scheduledTime = view.findViewById(R.id.scheduledTime);
            tv_stopId = view.findViewById(R.id.stopId);
            */
        }
    }
}
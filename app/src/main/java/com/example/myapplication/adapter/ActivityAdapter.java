package com.example.myapplication.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.calendar.Activity;

import java.util.ArrayList;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ActivityViewHolder> {


    private ArrayList<Activity> activitys;
    private Activity activity;

    public ActivityAdapter(ArrayList<Activity> activitys,Activity activity)
    {
        this.activity=activity;
        this.activitys = activitys;
    }

    public ArrayList<Activity> getActivitys()
    {
        return activitys;
    }

    public void setUsers(ArrayList<Activity> activitys)
    {
        this.activitys = activitys;
    }

    public Activity getActivity()
    {
        return activity;
    }

    public void setActivity(Activity activity)
    {
        this.activity = activity;
    }

    @NonNull
    @Override
    public ActivityAdapter.ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View activity_view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleritem_activity,parent,false);
        return new ActivityAdapter.ActivityViewHolder(activity_view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityViewHolder holder, int position) {

        Activity activity=activitys.get(position);
        holder.activity_name_textView.setText(activity.getName());
        holder.activity_time_start_textView.setText(activity.getTimeStart());
        holder.activity_time_end_textView.setText(activity.getTimeEnd());
    }

    @Override
    public int getItemCount() {
        return activitys.size();
    }

    public static class ActivityViewHolder extends RecyclerView.ViewHolder
    {
        public TextView activity_name_textView;
        public TextView activity_time_start_textView;
        public TextView activity_time_end_textView;

        public ActivityViewHolder(@NonNull View itemView) {
            super(itemView);

            //student
            activity_name_textView=itemView.findViewById(R.id.textView_activity_name);
            activity_time_start_textView=itemView.findViewById(R.id.textView_activity_time_start);
            activity_time_end_textView=itemView.findViewById(R.id.textView_activity_time_end);

        }
    }
}


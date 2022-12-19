package com.example.myapplication.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.interfaceSelectListener.InterfaceSelectActivityListener;
import com.example.myapplication.model.FirebaseModelActivity;

import java.util.ArrayList;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ActivityViewHolder> {


    private ArrayList<FirebaseModelActivity> activitys;
    private FirebaseModelActivity activity;
    private InterfaceSelectActivityListener listener;

    public ActivityAdapter(ArrayList<FirebaseModelActivity> activitys, FirebaseModelActivity activity, InterfaceSelectActivityListener listener)
    {
        this.activity=activity;
        this.activitys = activitys;
        this.listener = listener;
    }

    public ArrayList<FirebaseModelActivity> getActivitys()
    {
        return activitys;
    }

    public void setUsers(ArrayList<FirebaseModelActivity> activitys)
    {
        this.activitys = activitys;
    }

    public FirebaseModelActivity getActivity()
    {
        return activity;
    }

    public void setActivity(FirebaseModelActivity activity)
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

        FirebaseModelActivity activity=activitys.get(position);
        holder.activity_name_textView.setText(activity.getName());
        holder.activity_time_start_textView.setText(activity.getTimeStart());
        holder.activity_time_end_textView.setText(activity.getTimeEnd());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(activitys.get(holder.getAdapterPosition()));
            }
        });
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

        public CardView cardView;

        public ActivityViewHolder(@NonNull View itemView) {
            super(itemView);

            //student
            activity_name_textView=itemView.findViewById(R.id.textView_activity_name);
            activity_time_start_textView=itemView.findViewById(R.id.textView_activity_time_start);
            activity_time_end_textView=itemView.findViewById(R.id.textView_activity_time_end);

            cardView=itemView.findViewById(R.id.screen_container_activity);

        }
    }
}


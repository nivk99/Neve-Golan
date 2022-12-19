package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.FirebaseModelFeedback;

import java.util.ArrayList;




public class FeedbacksAdapter extends RecyclerView.Adapter<FeedbacksAdapter.FeedbackViewHolder>  {

    private ArrayList<FirebaseModelFeedback> feedbacks;

    public FeedbacksAdapter(ArrayList<FirebaseModelFeedback> comments) {
        this.feedbacks = comments;
    }

    public ArrayList<FirebaseModelFeedback> get_feedbacks()
    {
        return this.feedbacks;
    }
    public void set_feedbacks(ArrayList<FirebaseModelFeedback> comments) {
        this.feedbacks = comments;
    }

    @NonNull
    @Override
    public FeedbacksAdapter.FeedbackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleritem_feedback,parent,false);
        return new FeedbacksAdapter.FeedbackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedbackViewHolder holder, int position) {
        FirebaseModelFeedback comment=feedbacks.get(position);
        holder.details.setText(comment.getMsg());
        holder.author.setText(comment.getName());

    }

    @Override
    public int getItemCount() {
        return feedbacks.size();
    }


    public static class FeedbackViewHolder extends RecyclerView.ViewHolder {
        public TextView details;
        public TextView author;


        public FeedbackViewHolder(@NonNull View itemView) {
            super(itemView);
            details =(TextView)itemView.findViewById(R.id.comment_details);
            author =(TextView) itemView.findViewById(R.id.comment_fullname);
        }


    }
}

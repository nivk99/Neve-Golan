package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.UserViewHolder> {


    private final ArrayList<student> users;

    public StudentAdapter(ArrayList<student> users) {
        this.users = users;
    }


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View user_view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleritem_student,parent,false);
        return new UserViewHolder(user_view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        student user=users.get(position);
        holder.name_textView.setText(user.get_name());
        holder.last_name_textView.setText(user.get_last_name());
        holder.age_textView.setText(""+user.get_age());
        holder.phone_textView.setText(user.get_phone());
        holder.email_textView.setText(user.get_email());
        holder.address_textView.setText(user.get_address());
        holder.class_textView.setText(user.get_class());



    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder
    {
        public TextView name_textView;
        public TextView last_name_textView;
        public TextView age_textView;
        public TextView phone_textView;
        public TextView email_textView;
        public TextView address_textView;
        public TextView class_textView;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            name_textView=itemView.findViewById(R.id.textView_student_name);
            last_name_textView=itemView.findViewById(R.id.textView_student_last_name);
            age_textView=itemView.findViewById(R.id.textView_student_age);
            phone_textView=itemView.findViewById(R.id.textView_student_phone);
            email_textView=itemView.findViewById(R.id.textView_student_email);
            address_textView=itemView.findViewById(R.id.textView_student_address);
            class_textView=itemView.findViewById(R.id.textView_student_class);

        }
    }
}

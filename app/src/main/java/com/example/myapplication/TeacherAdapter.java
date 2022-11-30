package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.UserViewHolder>  {



    private final ArrayList<teacher> users;

    public TeacherAdapter(ArrayList<teacher> users) {
        this.users = users;
    }


    @NonNull
    @Override
    public TeacherAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View user_view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleritem_teacher,parent,false);
        return new TeacherAdapter.UserViewHolder(user_view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherAdapter.UserViewHolder holder, int position) {
        teacher user=users.get(position);
        holder.name_textView.setText(user.get_name());
        holder.last_name_textView.setText(user.get_last_name());
        holder.age_textView.setText(""+user.get_age());
        holder.phone_textView.setText(user.get_phone());
        holder.email_textView.setText(user.get_email());
        holder.address_textView.setText(user.get_address());
        holder.profession_textView.setText(user.get_profession());



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
        public TextView profession_textView;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            name_textView=itemView.findViewById(R.id.textView_teacher_name);
            last_name_textView=itemView.findViewById(R.id.textView_teacher_last_name);
            age_textView=itemView.findViewById(R.id.textView_teacher_age);
            phone_textView=itemView.findViewById(R.id.textView_teacher_phone);
            email_textView=itemView.findViewById(R.id.textView_teacher_email);
            address_textView=itemView.findViewById(R.id.textView_teacher_address);
            profession_textView=itemView.findViewById(R.id.textView_teacher_profession);

        }
    }


}

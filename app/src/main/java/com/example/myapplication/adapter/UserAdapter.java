package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.users.Student;
import com.example.myapplication.users.Teacher;
import com.example.myapplication.users.User;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {


    private ArrayList<User> users;
    private User user;

    public UserAdapter(ArrayList<User> users,User user)
    {
        this.user=user;
        this.users = users;
    }

    public ArrayList<User> getUsers()
    {
        return users;
    }

    public void setUsers(ArrayList<User> users)
    {
        this.users = users;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    @NonNull
    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(this.user instanceof Student)
        {
            //student
            View user_view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleritem_student,parent,false);
            return new UserAdapter.UserViewHolder(user_view,this.user);
        }
        else
        {
            //teacher
            View user_view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleritem_teacher,parent,false);
            return new UserAdapter.UserViewHolder(user_view,this.user);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

        User user=users.get(position);
        if(this.user instanceof Student)
        {
            //student
            Student student=(Student)(user);
            holder.student_name_textView.setText(user.get_name());
            holder.student_last_name_textView.setText(user.get_last_name());
            holder.student_age_textView.setText(""+user.get_age());
            holder.student_phone_textView.setText(user.get_phone());
            holder.student_email_textView.setText(user.get_email());
            holder.student_id_textView.setText(user.get_id());
            holder.student_class_textView.setText(student.get_class());
        }
        else
        {
            //teacher
            Teacher teacher=(Teacher)(user);
            holder.teacher_name_textView.setText(user.get_name());
            holder.teacher_last_name_textView.setText(user.get_last_name());
            holder.teacher_age_textView.setText(""+user.get_age());
            holder.teacher_phone_textView.setText(user.get_phone());
            holder.teacher_email_textView.setText(user.get_email());
            holder.teacher_id_textView.setText(user.get_id());
            holder.teacher_profession_textView.setText(teacher.get_profession());
        }


    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder
    {
        //student
        public TextView student_name_textView;
        public TextView student_last_name_textView;
        public TextView student_age_textView;
        public TextView student_phone_textView;
        public TextView student_email_textView;
        public TextView student_id_textView;
        public TextView student_class_textView;

        //teacher
        public TextView teacher_name_textView;
        public TextView teacher_last_name_textView;
        public TextView teacher_age_textView;
        public TextView teacher_phone_textView;
        public TextView teacher_email_textView;
        public TextView teacher_id_textView;
        public TextView teacher_profession_textView;


        public UserViewHolder(@NonNull View itemView,User user) {
            super(itemView);

            if(user instanceof Student)
            {
                //student
                student_name_textView = itemView.findViewById(R.id.textView_student_name);
                student_last_name_textView = itemView.findViewById(R.id.textView_student_last_name);
                student_age_textView = itemView.findViewById(R.id.textView_student_age);
                student_phone_textView = itemView.findViewById(R.id.textView_student_phone);
                student_email_textView = itemView.findViewById(R.id.textView_student_email);
                student_id_textView = itemView.findViewById(R.id.textView_student_id);
                student_class_textView = itemView.findViewById(R.id.textView_student_class);
            }
            else {


                //teacher
                teacher_name_textView = itemView.findViewById(R.id.textView_teacher_name);
                teacher_last_name_textView = itemView.findViewById(R.id.textView_teacher_last_name);
                teacher_age_textView = itemView.findViewById(R.id.textView_teacher_age);
                teacher_phone_textView = itemView.findViewById(R.id.textView_teacher_phone);
                teacher_email_textView = itemView.findViewById(R.id.textView_teacher_email);
                teacher_id_textView = itemView.findViewById(R.id.textView_teacher_id);
                teacher_profession_textView = itemView.findViewById(R.id.textView_teacher_profession);
            }

        }
    }
}


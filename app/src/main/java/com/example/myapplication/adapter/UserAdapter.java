package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.interfaceSelectListener.InterfaceSelectUserListener;
import com.example.myapplication.model.FirebaseModelStudent;
import com.example.myapplication.model.FirebaseModelTeacher;
import com.example.myapplication.model.FirebaseModeUser;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {


    //array of users
    private ArrayList<FirebaseModeUser> users;

    //Checks whether it is a teacher or a student
    private FirebaseModeUser user;

    //listening for clicks
    private InterfaceSelectUserListener listener;


    //constructor
    public UserAdapter(ArrayList<FirebaseModeUser> users, FirebaseModeUser user, InterfaceSelectUserListener listener)
    {
        this.listener=listener;
        this.user=user;
        this.users = users;
    }

    public ArrayList<FirebaseModeUser> getUsers()
    {
        return users;
    }

    public void setUsers(ArrayList<FirebaseModeUser> users)
    {
        this.users = users;
    }

    public FirebaseModeUser getUser()
    {
        return user;
    }

    public void setUser(FirebaseModeUser user)
    {
        this.user = user;
    }

    @NonNull
    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(this.user instanceof FirebaseModelStudent)
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
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position)
    {

        FirebaseModeUser user=users.get(position);
        if(this.user instanceof FirebaseModelStudent)
        {
            //student
            FirebaseModelStudent student=(FirebaseModelStudent)(user);
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
            FirebaseModelTeacher teacher=(FirebaseModelTeacher)(user);
            holder.teacher_name_textView.setText(user.get_name());
            holder.teacher_last_name_textView.setText(user.get_last_name());
            holder.teacher_age_textView.setText(""+user.get_age());
            holder.teacher_phone_textView.setText(user.get_phone());
            holder.teacher_email_textView.setText(user.get_email());
            holder.teacher_id_textView.setText(user.get_id());
            holder.teacher_profession_textView.setText(teacher.get_profession());
        }

        // recognize which user the client click and move to the user's page
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(users.get(holder.getAdapterPosition()));
            }
        });


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

        public CardView cardView;


        public UserViewHolder(@NonNull View itemView, FirebaseModeUser user) {
            super(itemView);

            if(user instanceof FirebaseModelStudent)
            {
                //student
                student_name_textView = itemView.findViewById(R.id.textView_student_name);
                student_last_name_textView = itemView.findViewById(R.id.textView_student_last_name);
                student_age_textView = itemView.findViewById(R.id.textView_student_age);
                student_phone_textView = itemView.findViewById(R.id.textView_student_phone);
                student_email_textView = itemView.findViewById(R.id.textView_student_email);
                student_id_textView = itemView.findViewById(R.id.textView_student_id);
                student_class_textView = itemView.findViewById(R.id.textView_student_class);
                cardView=itemView.findViewById(R.id.screen_container_student);
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
                cardView=itemView.findViewById(R.id.screen_container_teacher);
            }

        }
    }
}


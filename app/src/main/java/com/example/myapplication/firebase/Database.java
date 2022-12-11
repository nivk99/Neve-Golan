package com.example.myapplication.firebase;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.myapplication.adapter.UserAdapter;
import com.example.myapplication.calendar.CalendarViewActivity;
import com.example.myapplication.calendar.EditDayActivities;
import com.example.myapplication.personalInformation.Personal_Information_teacher;
import com.example.myapplication.readUser.admin.AdminContactListStudentActivity;
import com.example.myapplication.readUser.InterfaceContactList;
import com.example.myapplication.readUser.client.ClientContactListStudentActivity;
import com.example.myapplication.users.Student;
import com.example.myapplication.users.Teacher;
import com.example.myapplication.users.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class Database {

    private FirebaseDatabase _data_base;
    private String _name_path;
    private Query _query;
    private Teacher _teacher;

    public Database(String name_path)
    {
        this._data_base=FirebaseDatabase.getInstance();
        this._name_path=name_path;
        this._query =this._data_base.getReference(this._name_path);

    }

    public FirebaseDatabase get_data_base()
    {
        return _data_base;
    }

    public void set_data_base(FirebaseDatabase _data_base)
    {
        this._data_base = _data_base;
    }


    public String get_name_path()
    {
        return _name_path;
    }

    public void set_name_path(String _name_path)
    {
        this._name_path = _name_path;
    }

    public Query get_query() {
        return _query;
    }

    public void set_query(Query _query) {
        this._query = _query;
    }

    public void write_database(User user)
    {
        DatabaseReference myRef = this._data_base.getReference(this._name_path).child(user.get_id());
        if(user instanceof Student)
        {
            myRef.setValue((Student)(user));
        }
        else
        {
            myRef.setValue((Teacher)(user));
        }

    }


    public void read_database(UserAdapter adapter,InterfaceContactList ContactList)
    {
        ArrayList<User> users=adapter.getUsers();

        _query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users.clear();
                for(DataSnapshot usersnapshot:snapshot.getChildren())
                {
                    User currentuser=usersnapshot.getValue(User.class);
                    if( ContactList._this() instanceof AdminContactListStudentActivity||ContactList._this() instanceof ClientContactListStudentActivity)
                    {
                        currentuser=usersnapshot.getValue(Student.class);
                    }
                    else
                    {
                         currentuser=usersnapshot.getValue(Teacher.class);
                    }
                    adapter.getUsers().add(currentuser);
                }
                adapter.notifyDataSetChanged();
                Toast.makeText((Context) ContactList._this(),"there are " +users.size() +" users ",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText((Context) ContactList._this(),"error onCancelled",Toast.LENGTH_SHORT).show();

            }
        });



    }

    public void orderByChild(String key, UserAdapter adapter, InterfaceContactList ContactList)
    {
        this._query = this._data_base.getReference(this._name_path).orderByChild(key);
        read_database(adapter,ContactList);


    }

    public void equalTo(String name,UserAdapter adapter,InterfaceContactList ContactList)
    {
        this._query = this._data_base.getReference(this._name_path).orderByChild("_name").equalTo(name);
        read_database(adapter,ContactList);
    }
    
    
    
    public void update( Map<String, Object> Map,String key)
    {
        _data_base.getReference(this._name_path).child(key).updateChildren(Map);

        
    }
    public void  remove(String key)
    {
        _data_base.getReference(this._name_path).child(key).removeValue();
    }

    public void get_teacher(String email, Personal_Information_teacher p)
    {
        this._query= this._data_base.getReference(this._name_path).orderByChild("_email").equalTo(email);

        _query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot usersnapshot:snapshot.getChildren()) {
                    Teacher teacher = usersnapshot.getValue(Teacher.class);;
                     p.teacher_name_textView.setText(teacher.get_name());
                    p.teacher_last_name_textView.setText(teacher.get_last_name());
                    p.teacher_age_textView.setText("" + teacher.get_age());
                    p.teacher_phone_textView.setText(teacher.get_phone());
                    p.teacher_email_textView.setText(teacher.get_email());
                    p.teacher_id_textView.setText(teacher.get_id());
                    p.teacher_profession_textView.setText(teacher.get_profession());
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }








}

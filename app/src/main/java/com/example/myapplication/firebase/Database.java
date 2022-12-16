package com.example.myapplication.firebase;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.myapplication.adapter.ActivityAdapter;
import com.example.myapplication.adapter.UserAdapter;
import com.example.myapplication.calendar.Activity;
import com.example.myapplication.calendar.InterfaceActivity;
import com.example.myapplication.personalInformation.Personal_Information_teacher;
import com.example.myapplication.readUser.admin.AdminContactListStudentActivity;
import com.example.myapplication.readUser.InterfaceContactList;
import com.example.myapplication.readUser.client.ClientContactListStudentActivity;
import com.example.myapplication.users.FirebaseModelStudent;
import com.example.myapplication.users.FirebaseModelTeacher;
import com.example.myapplication.users.FirebaseModeUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class Database {


    //the database
    private FirebaseDatabase _data_base;

    //Name the path to the tree
    private String _name_path;

    //A query to the database
    private Query _query;

    public Database(String name_path) {
        this._data_base = FirebaseDatabase.getInstance();
        this._name_path = name_path;
        this._query = this._data_base.getReference(this._name_path);

    }

    public FirebaseDatabase get_data_base() {
        return _data_base;
    }

    public void set_data_base(FirebaseDatabase _data_base) {
        this._data_base = _data_base;
    }


    public String get_name_path() {
        return _name_path;
    }

    public void set_name_path(String _name_path) {
        this._name_path = _name_path;
    }

    public Query get_query() {
        return _query;
    }

    public void set_query(Query _query) {
        this._query = _query;
    }

    public void write_database(FirebaseModeUser user) {
        DatabaseReference myRef = this._data_base.getReference(this._name_path).child(user.get_id());
        if (user instanceof FirebaseModelStudent) {
            myRef.setValue((FirebaseModelStudent) (user));
        } else {
            myRef.setValue((FirebaseModelTeacher) (user));
        }

    }


    public void read_database(UserAdapter adapter, InterfaceContactList ContactList) {
        ArrayList<FirebaseModeUser> users = adapter.getUsers();

        _query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users.clear();
                for (DataSnapshot usersnapshot : snapshot.getChildren()) {
                    FirebaseModeUser currentuser = usersnapshot.getValue(FirebaseModeUser.class);
                    if (ContactList._this() instanceof AdminContactListStudentActivity || ContactList._this() instanceof ClientContactListStudentActivity) {
                        currentuser = usersnapshot.getValue(FirebaseModelStudent.class);
                    } else {
                        currentuser = usersnapshot.getValue(FirebaseModelTeacher.class);
                    }
                    adapter.getUsers().add(currentuser);
                }
                adapter.notifyDataSetChanged();
                Toast.makeText((Context) ContactList._this(), "משתמשים" + users.size() + "יש ", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText((Context) ContactList._this(), "יש שגיאה", Toast.LENGTH_SHORT).show();

            }
        });


    }


    public void read_database_activity(ActivityAdapter adapter, InterfaceActivity interfaceActivity)
    {
        ArrayList<Activity> activitys=adapter.getActivitys();
        _query.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                activitys.clear();
                for(DataSnapshot activitysnapshot:snapshot.getChildren())
                {
                    Activity currentactivity=activitysnapshot.getValue(Activity.class);
                    adapter.getActivitys().add(currentactivity);
                }
                adapter.notifyDataSetChanged();
                Toast.makeText((Context) interfaceActivity._this(),"פעילויות " +activitys.size() +" יש ",Toast.LENGTH_LONG).show();
                //Toast.makeText((Context) ContactList._this(),"there are " +users.size() +" users ",Toast.LENGTH_LONG).show();
                //_query.removeEventListener(this);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("cancle");

            }

        });
    }

    public void orderByChild(String key, UserAdapter adapter, InterfaceContactList ContactList) {
        this._query = this._data_base.getReference(this._name_path).orderByChild(key);
        read_database(adapter, ContactList);


    }

    public void equalTo(String name, UserAdapter adapter, InterfaceContactList ContactList) {
        this._query = this._data_base.getReference(this._name_path).orderByChild("_name").equalTo(name);
        read_database(adapter, ContactList);
    }


    public void update(Map<String, Object> Map, String key) {
        _data_base.getReference(this._name_path).child(key).updateChildren(Map);


    }

    public void remove(String key) {
        _data_base.getReference(this._name_path).child(key).removeValue();
    }

    public void get_teacher(String email, Personal_Information_teacher p) {
        this._query = this._data_base.getReference(this._name_path).orderByChild("_email").equalTo(email);

        _query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot usersnapshot : snapshot.getChildren()) {
                    FirebaseModelTeacher teacher = usersnapshot.getValue(FirebaseModelTeacher.class);
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

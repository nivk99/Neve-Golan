package com.example.myapplication.AddUser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.firebase.Authenticate;
import com.example.myapplication.firebase.Database;
import com.example.myapplication.menu.AdminMenuActivity;
import com.example.myapplication.readUser.admin.AdminContactListStudentActivity;

public class AddAdminActivity extends AppCompatActivity {

    //user definition
    private Authenticate mAuth;

    //Phone text
    public EditText admin_phone_textView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_admin);
        mAuth = new Authenticate();
        admin_phone_textView=(EditText)findViewById(R.id.editTextPhone_admin);
    }

    public void click_add_admin(View view)
    {
        mAuth.register_admin(admin_phone_textView.getText().toString(),this);
    }
}
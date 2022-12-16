package com.example.myapplication.AddUser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.firebase.Authenticate;
import com.example.myapplication.AddUser.InterfaceAddUser;

public class AddClientActivity extends AppCompatActivity implements InterfaceAddUser {

    //user definition
    private Authenticate mAuth;

    //email text
    private EditText email;

    //full name text
    private TextView full_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);
        email=(EditText)findViewById(R.id.editText_email_client);
        full_name=(TextView)findViewById(R.id.editText_full_name_client);
        mAuth = new Authenticate();


    }

    public void click_add_client(View view)
    {

        mAuth.register(email.getText().toString(),full_name.getText().toString(),this);

    }

    @Override
    public InterfaceAddUser _this() {
        return this;
    }
}
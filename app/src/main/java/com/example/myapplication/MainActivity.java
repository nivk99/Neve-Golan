package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.login.AdminLoginActivity;
import com.example.myapplication.login.ClientLoginActivity;

public class MainActivity extends AppCompatActivity {

    /*
    * This is the first page when opening the app
    * there is two button for log-in: (1)admin log-in  (2) teacher log-in
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /* this is direct to teacher log-in windows   */
    public void click_client_sign_in(View view)
    {
        startActivity(new Intent(this, ClientLoginActivity.class));

    }
    /* this is direct to admin log-in windows   */
    public void click_admin_sign_in(View view)
    {
        startActivity(new Intent(this, AdminLoginActivity.class));
    }
}
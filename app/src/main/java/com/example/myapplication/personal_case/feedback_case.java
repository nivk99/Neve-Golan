package com.example.myapplication.personal_case;

import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class feedback_case extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_case);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        Toast.makeText(this,id,Toast.LENGTH_SHORT).show();
    }
}
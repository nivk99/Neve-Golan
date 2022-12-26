package com.example.myapplication.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.firebase.Database;
import com.google.firebase.database.DatabaseReference;

public class BroadcastsActivity extends AppCompatActivity {

    Button sendMessage;
    EditText messageContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcasts);

        messageContent = findViewById(R.id.message_content);
        sendMessage = findViewById(R.id.publish_message);
        //startActivity(new Intent(getApplicationContext(), MainActivity.class));

        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

                String messege = messageContent.getText().toString();
                if (messege.compareTo("") != 0){
                    new Database("").get_data_base().getReference("broadcastMessage").child("massage").setValue(messege);
                }


            }


        });
    }
}
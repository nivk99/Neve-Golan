package com.example.myapplication.messages;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CreateMessages extends AppCompatActivity {

    EditText mcreatetitleofnote,mcreatecontentofnote;
    FloatingActionButton msavenote;
    FirebaseFirestore firebaseFirestore;
    ProgressBar mprogressbarofcreatenote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_messages);
        msavenote=findViewById(R.id.savenote);
        mcreatecontentofnote=findViewById(R.id.createcontentofnote);
        mcreatetitleofnote=findViewById(R.id.createtitleofnote);

        mprogressbarofcreatenote=findViewById(R.id.progressbarofcreatenote);
        Toolbar toolbar=findViewById(R.id.toolbarofcreatenote);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseFirestore=FirebaseFirestore.getInstance();



        msavenote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=mcreatetitleofnote.getText().toString().trim();
                String content=mcreatecontentofnote.getText().toString().trim();
                if(title.isEmpty() || content.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Both field are Require",Toast.LENGTH_SHORT).show();
                }
                else
                {

                    mprogressbarofcreatenote.setVisibility(View.VISIBLE);

                    DocumentReference documentReference=firebaseFirestore.collection("messages").document("Admin").collection("myMessages").document();
                    Map<String ,Object> note= new HashMap<>();
                    note.put("title",title);
                    note.put("content",content);

                    documentReference.set(note).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getApplicationContext(),"ההודעה נשמרה",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(CreateMessages.this, MessageActivity.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"ההודעה לא נשמרה",Toast.LENGTH_SHORT).show();
                            mprogressbarofcreatenote.setVisibility(View.INVISIBLE);
                        }
                    });
                }
            }
        });


    }



    public void click_speak(View view)
    {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        if(intent.resolveActivity(getPackageManager())!=null)
        {
            startActivityForResult(intent ,10);
        }
        else
        {
            Toast.makeText(this,"Your Device Doesn't Support It",Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){

            case 10:
                if(resultCode==RESULT_OK && data!=null)
                {
                    ArrayList<String > result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    mcreatecontentofnote.setText(result.get(0));
                }
                break;
        }
    }
}
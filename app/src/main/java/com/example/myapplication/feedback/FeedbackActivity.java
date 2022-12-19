package com.example.myapplication.feedback;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.adapter.FeedbacksAdapter;
import com.example.myapplication.firebase.Database;
import com.example.myapplication.menu.ClientMenuActivity;
import com.example.myapplication.model.FirebaseModelFeedback;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;




public class FeedbackActivity extends AppCompatActivity {

    //Database
    private  Database database;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Intent intent=getIntent();
        id=intent.getStringExtra("_id");

        final RecyclerView commentsListView = findViewById(R.id.recyclerView_feedback);
        commentsListView.setLayoutManager(new LinearLayoutManager(this));
        final ArrayList<FirebaseModelFeedback> feedback = new ArrayList<>();
        FeedbacksAdapter adapter = new FeedbacksAdapter(feedback);
        commentsListView.setAdapter(adapter);
        database=new Database("feedbacks/"+id);
         database.read_database_feedback(adapter,this);


    }

    public  void click_add_feedback(View view)
    {
        EditText editText = findViewById(R.id.editText_feedback);
        DatabaseReference myRef = this.database.get_data_base().getReference("feedbacks/"+id).push();
        FirebaseModelFeedback comment =new FirebaseModelFeedback(ClientMenuActivity.get(),editText.getText().toString());
        myRef.setValue(comment);
    }
}
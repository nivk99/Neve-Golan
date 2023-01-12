package com.example.myapplication.calendar;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ActivityAdapter;
import com.example.myapplication.adapter.interfaceSelectListener.InterfaceSelectActivityListener;
import com.example.myapplication.firebase.Authenticate;
import com.example.myapplication.firebase.Database;
import com.example.myapplication.model.FirebaseModelActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class Calender_Model {
    private Context context;
    public void Add_new_activity(String year, String month, String day, String Add_ActivityName, String Add_TimeStart, String Add_TimeEnd,String id, Context con){
        this.context=con;
        task t=new task();
        String send="http://10.0.2.2:8000/addActivity/"+Add_ActivityName+"/"+Add_TimeStart+"/"+Add_TimeEnd+"/"+id+"/"+year+"/"+month+"/"+day;
        t.execute(send);

    }

    public static String removeSpaces(String s){
        if(s.length()==0)
            return s;
        int i=0;      // remove start spaces(if there are)
        while(s.charAt(i)==' '){
            i++;
        }
        return s.substring(i);
    }

    public void delete_Activity(String params,Context con){
        this.context=con;
        task t=new task();
        String send="http://10.0.2.2:8000/deleteActivity/"+params;
        t.execute(send);
    }

    public static void Display_Activity(String path, ActivityAdapter adapter, Database database, InterfaceSelectActivityListener listener, RecyclerView recyclerView){
        final ArrayList<FirebaseModelActivity> activitis=new ArrayList<>();
        adapter =new ActivityAdapter(activitis,new FirebaseModelActivity(),listener);
        recyclerView.setAdapter(adapter);
        database = new Database(path);
        database.read_database_activity(adapter);
    }

    // for sending websocket to the server
    public class task extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection=null;
            try {
                URL url=new URL(params[0]);
                connection=(HttpURLConnection)url.openConnection();
                connection.connect();
                InputStream stream=connection.getInputStream();
                BufferedReader redReader=new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer=new StringBuffer();
                String line="";
                while((line=redReader.readLine())!=null){
                    buffer.append(line);
                }
                String s=buffer.toString();
                return s;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(String s){
            super.onPostExecute(s);
            AddActivity.toasty(context, s);

        }
    }
}

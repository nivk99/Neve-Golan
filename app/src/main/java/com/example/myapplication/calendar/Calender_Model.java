package com.example.myapplication.calendar;

import static android.content.ContentValues.TAG;

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

import java.util.ArrayList;
import java.util.Map;

public class Calender_Model {
    public static int Add_new_activity(String year, String month,String day,String Add_ActivityName,String Add_TimeStart,String Add_TimeEnd){
        final int[] Ex_value = {0};
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Query nameQuery = ref.child("activity").child(year).child(month).child(day).orderByChild("name");
        nameQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            Boolean flag=false;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                flag=false;
                for (DataSnapshot nameSnapshot: dataSnapshot.getChildren()) {
                    Map dataActivity=((Map)nameSnapshot.getValue());
                    String [] save_name={(String) dataActivity.get("name"),(String)dataActivity.get("timeStart"),(String)dataActivity.get("timeEnd")};
                    if(save_name[0].equals(Add_ActivityName) && save_name[1].equals(Add_TimeStart) && save_name[2].equals(Add_TimeEnd)){
                        flag=true;
                        Ex_value[0] =1;break;
                    }
                }
                if(!flag){
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    Database teacher = new Database("users/teacher");
                    String ID = teacher.get_teacher_ID(Authenticate.get_current_email());
                    DatabaseReference myRef = database.getReference("activity/"+Integer.parseInt(year)+"/"+Integer.parseInt(month)+"/"+Integer.parseInt(day)).child(Add_ActivityName+","+Add_TimeStart+","+Add_TimeEnd);
                    myRef.setValue(new FirebaseModelActivity(Add_ActivityName,Add_TimeStart,Add_TimeEnd, ID));
                    flag = false;
                    Ex_value[0] =0;
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "onCancelled", databaseError.toException());
            }
        });
        return Ex_value[0];
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

    public static DatabaseReference delete_Activity(DatabaseReference myRef){
        myRef.setValue(null);
        return myRef;
    }

    public static void Display_Activity(String path, ActivityAdapter adapter, Database database, InterfaceSelectActivityListener listener, RecyclerView recyclerView){
        final ArrayList<FirebaseModelActivity> activitis=new ArrayList<>();
        adapter =new ActivityAdapter(activitis,new FirebaseModelActivity(),listener);
        recyclerView.setAdapter(adapter);
        database = new Database(path);
        database.read_database_activity(adapter);
    }
}


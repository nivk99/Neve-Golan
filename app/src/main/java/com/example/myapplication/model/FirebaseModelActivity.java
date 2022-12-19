package com.example.myapplication.model;

public class FirebaseModelActivity {

    String name;
    String timeStart;
    String timeEnd;

    public FirebaseModelActivity(String Name, String TimeStart, String TimeEnd){
        this.name=Name;
        this.timeStart=TimeStart;
        this.timeEnd=TimeEnd;
    }
    public String getName() {
        return name;
    }
    public String getTimeStart() {
        return timeStart;
    }
    public String getTimeEnd() {
        return timeEnd;
    }

    public FirebaseModelActivity() {
        super();
    }

}

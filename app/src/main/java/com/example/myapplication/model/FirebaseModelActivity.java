package com.example.myapplication.model;

public class FirebaseModelActivity
{

    String name;
    String timeStart;
    String timeEnd;
    String id;


    public FirebaseModelActivity(String Name, String TimeStart, String TimeEnd, String id){
        this.name=Name;
        this.timeStart=TimeStart;
        this.timeEnd=TimeEnd;
        this.id = id;

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
    public String getID(){return id;}

    public FirebaseModelActivity() {
        super();
    }

}

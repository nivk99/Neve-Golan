package com.example.myapplication.calendar;

public class Activity {

    String Name;
    String TimeStart;
    String TimeEnd;

    public Activity(String Name,String TimeStart,String TimeEnd){
        this.Name=Name;
        this.TimeStart=TimeStart;
        this.TimeEnd=TimeEnd;
    }
    public String getName() {
        return Name;
    }
    public String getTimeStart() {
        return TimeStart;
    }
    public String getTimeEnd() {
        return TimeEnd;
    }



}

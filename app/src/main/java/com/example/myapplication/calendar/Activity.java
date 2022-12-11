package com.example.myapplication.calendar;

public class Activity {

    String name;
    String timeStart;
    String timeEnd;

    public Activity(String Name,String TimeStart,String TimeEnd){
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

    public Activity() {
        super();
    }

}

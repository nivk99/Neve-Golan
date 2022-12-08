package com.example.myapplication;

public class activity {

    String Name;
    String TimeStart;
    String TimeEnd;

    public activity(String Name,String TimeStart,String TimeEnd){
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

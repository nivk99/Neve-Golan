package com.example.myapplication.feedback;

import androidx.annotation.NonNull;

public class FirebaseModelFeedback {

    String name;
    String msg;


    public FirebaseModelFeedback(String name, String msg) {
        this.name = name;
        this.msg = msg;
    }

    public FirebaseModelFeedback() {
        this.msg = "null";
        this.name="null";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

package com.example.myapplication.model;

public class FirebaseModelStudent extends FirebaseModeUser {

    protected String _class;

    //constructor
    public FirebaseModelStudent(String _name, String _last_name, double _age, String _phone, String _email, String _id, String _class) {
        super(_name, _last_name, _age, _phone, _email, _id);
        this._class = _class;
    }


    //constructor
    public FirebaseModelStudent() {
        super();
        _class="null";
    }

    public String get_class() {
        return _class;
    }

    public void set_class(String _class) {
        this._class = _class;
    }
}

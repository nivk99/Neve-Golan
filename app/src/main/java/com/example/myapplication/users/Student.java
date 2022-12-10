package com.example.myapplication.users;

public class Student extends User {

    protected String _class;

    public Student(String _name, String _last_name, double _age, String _phone, String _email, String _id, String _class) {
        super(_name, _last_name, _age, _phone, _email, _id);
        this._class = _class;
    }

    public Student() {
        super();
    }

    public String get_class() {
        return _class;
    }

    public void set_class(String _class) {
        this._class = _class;
    }
}

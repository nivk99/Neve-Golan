package com.example.myapplication;

public class student extends user {

    private String _class;

    public student(String _name, String _last_name, double _age, String _phone, String _email, String _address, String _class) {
        super(_name, _last_name, _age, _phone, _email, _address);
        this._class = _class;
    }

    public student() {
        super();
    }

    public String get_class() {
        return _class;
    }

    public void set_class(String _class) {
        this._class = _class;
    }
}

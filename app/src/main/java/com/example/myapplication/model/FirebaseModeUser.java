package com.example.myapplication.model;

public class FirebaseModeUser {

    protected String _name;
    protected String _last_name;
    protected double _age;
    protected String _phone;
    protected String _email;
    protected String _id;


    //constructor
    public FirebaseModeUser(String _name, String _last_name, double _age, String _phone, String _email, String _id) {
        this._name = _name;
        this._last_name = _last_name;
        this._age = _age;
        this._phone = _phone;
        this._email = _email;
        this._id = _id;
    }

    //constructor
    public FirebaseModeUser() {
        this._name = "null";
        this._last_name = "null";
        this._age = 0;
        this._phone = "null";
        this._email = "null";
        this._id = "null";

    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_last_name() {
        return _last_name;
    }

    public void set_last_name(String _last_name) {
        this._last_name = _last_name;
    }

    public double get_age() {
        return _age;
    }

    public void set_age(double _age) {
        this._age = _age;
    }

    public String get_phone() {
        return _phone;
    }

    public void set_phone(String _phone) {
        this._phone = _phone;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    @Override
    public String toString() {
        return "User{" +
                "_name='" + _name + '\'' +
                ", _last_name='" + _last_name + '\'' +
                ", _age=" + _age +
                ", _phone='" + _phone + '\'' +
                ", _email='" + _email + '\'' +
                ", _id='" + _id + '\'' +
                '}';
    }
}

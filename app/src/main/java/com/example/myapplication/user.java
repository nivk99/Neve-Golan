package com.example.myapplication;

public class user {

    private String _name;
    private String _last_name;
    private double _age;
    private String _phone;
    private String _email;
    private String _address;


    public user(String _name, String _last_name, double _age, String _phone, String _email, String _address) {
        this._name = _name;
        this._last_name = _last_name;
        this._age = _age;
        this._phone = _phone;
        this._email = _email;
        this._address = _address;
    }

    public user() {
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

    public String get_address() {
        return _address;
    }

    public void set_address(String _address) {
        this._address = _address;
    }
}

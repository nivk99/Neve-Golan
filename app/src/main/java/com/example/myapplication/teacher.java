package com.example.myapplication;

public class teacher extends user {

    private String _profession;

    public teacher(String _name, String _last_name, double _age, String _phone, String _email, String _address, String _profession) {
        super(_name, _last_name, _age, _phone, _email, _address);
        this._profession = _profession;
    }

    public teacher() {
        super();
    }

    public String get_profession() {
        return _profession;
    }

    public void set_profession(String _profession) {
        this._profession = _profession;
    }
}

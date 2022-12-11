package com.example.myapplication.users;

public class Teacher extends User {

    protected String _profession;

    public Teacher(String _name, String _last_name, double _age, String _phone, String _email, String _id, String _profession) {
        super(_name, _last_name, _age, _phone, _email, _id);
        this._profession = _profession;
    }

    public Teacher() {
        super();
        this._profession="null";
    }

    public String get_profession() {
        return _profession;
    }

    public void set_profession(String _profession) {
        this._profession = _profession;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "_profession='" + _profession + '\'' +
                ", _name='" + _name + '\'' +
                ", _last_name='" + _last_name + '\'' +
                ", _age=" + _age +
                ", _phone='" + _phone + '\'' +
                ", _email='" + _email + '\'' +
                ", _id='" + _id + '\'' +
                '}';
    }
}

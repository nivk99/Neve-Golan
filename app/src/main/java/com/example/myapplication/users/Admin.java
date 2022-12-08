package com.example.myapplication.users;

public final class Admin
{


    private boolean _is_admin;
    private String _name;
    private String _password;



    public Admin()
    {
        _is_admin=false;
        _name = "Admin";
        _password = "Admin";
    }

    public Admin(String name, String password)
    {
        _is_admin=false;
        _name = name;
        _password = password;
    }

    public String get_name()
    {
        return _name;
    }

    public void set_name(String _name)
    {
        this._name = _name;
    }

    public String get_password()
    {
        return _password;
    }

    public void set_password(String _password)
    {
        this._password = _password;
    }

    public boolean is_admin()
    {
        return _is_admin;
    }

    public void set_is_admin(boolean _is_admin)
    {
        this._is_admin = _is_admin;
    }
}

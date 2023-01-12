package com.example.myapplication.readUser;

import android.widget.CheckBox;

public class model_teacher_Activity {
    public static String find_the_sortType_teacher(CheckBox name, CheckBox age, CheckBox profession,  CheckBox address) {

        if(name.isChecked())
        {
            return "_name";
        }
        if(age.isChecked())
        {
            return "_age";
        }
        if(profession.isChecked())
        {
            return "_profession";
        }
        if(address.isChecked())
        {
            return "_address";
        }
        return null;
    }
}




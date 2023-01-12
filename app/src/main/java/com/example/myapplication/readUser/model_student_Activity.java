package com.example.myapplication.readUser;
import android.widget.CheckBox;

public class model_student_Activity {
    public static String find_the_sortType_student(CheckBox name, CheckBox age,CheckBox clas,CheckBox address) {
        if (name.isChecked()) {
            return "_name";
        }
        if (age.isChecked()) {
            return "_age";
        }
        if (clas.isChecked()) {
            return "_class";
        }
        if (address.isChecked()) {
            return "_address";
        }
        return null;
    }
}

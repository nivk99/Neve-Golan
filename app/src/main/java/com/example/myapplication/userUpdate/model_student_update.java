package com.example.myapplication.userUpdate;

import com.example.myapplication.firebase.Database;
import com.example.myapplication.model.FirebaseModelStudent;
import com.example.myapplication.model.FirebaseModelTeacher;
import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class model_student_update extends FirebaseModelStudent{
    public static void Update_student_card(Database database, String student_name_textView_str, String student_last_name_textView_str ,
                                           double student_age_textView_str, String student_phone_textView_str, String student_email_textView_str ,
                                           String student_id_textView_str, String student_class_textView_str, String id){
        if(id.equals(student_id_textView_str))
        {
            updateStudent update_student=new updateStudent(student_name_textView_str,student_last_name_textView_str,student_age_textView_str,student_phone_textView_str,student_email_textView_str,student_id_textView_str,student_class_textView_str);
            database.update(update_student.toMap(),id);

        }
        else
        {
            database.remove(id);
            FirebaseModelStudent new_student=new FirebaseModelStudent(student_name_textView_str,student_last_name_textView_str,student_age_textView_str,student_phone_textView_str,student_email_textView_str,student_id_textView_str,student_class_textView_str);
            database.write_database(new_student);
        }
    }
    private static class updateStudent extends FirebaseModelStudent
    {
        public updateStudent(String _name, String _last_name, double _age, String _phone, String _email, String _id, String _class) {
            super(_name, _last_name, _age, _phone, _email, _id, _class);
        }

        public updateStudent() {
        }

        @Exclude
        public Map<String, Object> toMap()
        {
            HashMap<String,Object> result =new HashMap<>();
            result.put("_name",this._name);
            result.put("_last_name",this._last_name);
            result.put("_age",this._age);
            result.put("_phone",this._phone);
            result.put("_email",this._email);
            result.put("_id",this._id);
            result.put("_class",this._class);
            return result;

        }
    }
}



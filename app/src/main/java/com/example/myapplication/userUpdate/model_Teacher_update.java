package com.example.myapplication.userUpdate;

import com.example.myapplication.firebase.Database;
import com.example.myapplication.model.FirebaseModelTeacher;
import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class model_Teacher_update extends FirebaseModelTeacher {

    public static void update_teacher_card(String teacher_name_textView_str, String teacher_last_name_textView_str,
                                           double teacher_age_textView_str , String teacher_phone_textView_str , String teacher_email_textView_str ,
                                           String teacher_id_textView_str , String teacher_profession_textView_str, Database database , String id)
    {
        if(id.equals(teacher_id_textView_str))
        {
            updateTeacher update_teacher=new updateTeacher(teacher_name_textView_str,teacher_last_name_textView_str,teacher_age_textView_str,teacher_phone_textView_str,teacher_email_textView_str,teacher_id_textView_str,teacher_profession_textView_str);
            database.update(update_teacher.toMap(),id);

        }
        else
        {
            database.remove(id);
            FirebaseModelTeacher new_teacher=new FirebaseModelTeacher(teacher_name_textView_str,teacher_last_name_textView_str,teacher_age_textView_str,teacher_phone_textView_str,teacher_email_textView_str,teacher_id_textView_str,teacher_profession_textView_str);
            database.write_database(new_teacher);
        }
    }


    private static class  updateTeacher extends FirebaseModelTeacher
    {
        public updateTeacher(String _name, String _last_name, double _age, String _phone, String _email, String _id, String _profession) {
            super(_name, _last_name, _age, _phone, _email, _id, _profession);
        }

        public updateTeacher() {
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
            result.put("_profession",this._profession);
            return result;

        }




    }
}

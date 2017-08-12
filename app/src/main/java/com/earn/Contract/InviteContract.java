package com.earn.Contract;

import com.earn.model.StudentData;

import java.util.ArrayList;

/**
 * Created by asus on 2017/8/8.
 */

public interface InviteContract {
    interface View{


        void showSuccess(ArrayList<StudentData.Students> studentses);
    }

    interface Presenter{

        void getStudents();

    }
}

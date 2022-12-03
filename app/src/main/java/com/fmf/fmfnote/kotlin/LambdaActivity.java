package com.fmf.fmfnote.kotlin;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fmf.fmfnote.R;

import java.util.ArrayList;
import java.util.List;

public class LambdaActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Student> studentList = getDataList();



      /*Filter filter =(student) -> student.getName().startsWith("小杨");
         List<Student> startsWithYeList = filterStudent(studentList,filter);
              filter=(student) -> student.getAge()>18;
         List<Student> ageList =  filterStudent(studentList,filter);*/

       // studentList.forEach();


       List<Student> startsWithYeList = filterStudent(studentList,student -> {return student.getName().startsWith("小杨"); });
       List<Student> ageList =  filterStudent(studentList,student -> {return student.getAge()>18; });




    }




    private static List<Student> getDataList() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("小杨逗比", 24));
        studentList.add(new Student("潇湘剑雨", 25));
        studentList.add(new Student("你是傻逼", 18));
        return studentList;
    }



    private static List<Student> filterStudent(List<Student> studentList, Filter filter) {
        List<Student> students = new ArrayList<>();
        for (Student student : studentList) {
            if (filter.filter(student)) {
                students.add(student);
            }
        }
        return students;
    }





    public interface Filter {
        boolean filter(Student student);
    }



    public interface Filters<T> {
        boolean filter(T t);
    }

}

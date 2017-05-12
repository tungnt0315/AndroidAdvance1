package com.example.tungnt.androidadvance1_lesson3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tungnt.androidadvance1_lesson3.SqlHelper.MyDatabaseHelper;
import com.example.tungnt.androidadvance1_lesson3.adapter.StudentAdapter;
import com.example.tungnt.androidadvance1_lesson3.model.Student;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView rvStudent;

    List<Student> studentList = new ArrayList<>();
    StudentAdapter studentAdapter;
    RecyclerView.LayoutManager studentlayoutManager;
    MyDatabaseHelper db;
    int i = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        rvStudent = (RecyclerView) findViewById(R.id.rv_student);

        db = new MyDatabaseHelper(this);
        db.createDefaultStudentsIfNeed();

        studentAdapter = new StudentAdapter(this);
        studentlayoutManager = new LinearLayoutManager(this);
        rvStudent.setAdapter(studentAdapter);
        rvStudent.setLayoutManager(studentlayoutManager);

        ((Button)findViewById(R.id.btn_add)).setOnClickListener(this);
    }

    //Add student vao db va listview
    @Override
    public void onClick(View v) {
        i = db.getCount()+1;
        Student student;
        while (true){
            student = new Student(i++, "Nhan", "Hoa Van", "0324862374");
            if(db.addStudent(student) != -1){
                studentAdapter.addItem(db.getCount()-1, student);
                break;
            }
        }



    }
}

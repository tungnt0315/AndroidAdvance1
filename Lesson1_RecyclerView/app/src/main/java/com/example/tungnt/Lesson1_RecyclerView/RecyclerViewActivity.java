package com.example.tungnt.Lesson1_RecyclerView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tungnt.Lesson1_RecyclerView.adapter.StudentAdapter;
import com.example.tungnt.Lesson1_RecyclerView.data.Student;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView rvStudent;
    private StudentAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private EditText etName;

    private List<Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recylerview);

        //set widget
        rvStudent = (RecyclerView) findViewById(R.id.rv_student);
        etName = (EditText) findViewById(R.id.et_name);

        //set recyclerView
        setRecyclerView();

        //set listener for button
        ((Button)findViewById(R.id.btn_add)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Student student = new Student(etName.getText().toString().trim());
        adapter.addItem(students.size(), student);
    }

    private void setRecyclerView(){
        students = new ArrayList<>();
        students.add(new Student("Nguyen Thanh Tung"));
        students.add(new Student("Nguyen Van Thanh"));
        students.add(new Student("Tran Trung Hieu"));
        students.add(new Student("Phan Thi Nhan"));
        students.add(new Student("Le Hoang Anh"));
        students.add(new Student("Tran Thi Hoa"));
        students.add(new Student("Bang Quang Hung"));
        students.add(new Student("Doan Hung"));

        adapter = new StudentAdapter(students);
        rvStudent.setAdapter(adapter);

        layoutManager = new LinearLayoutManager(this);
        rvStudent.setLayoutManager(layoutManager);


    }
}

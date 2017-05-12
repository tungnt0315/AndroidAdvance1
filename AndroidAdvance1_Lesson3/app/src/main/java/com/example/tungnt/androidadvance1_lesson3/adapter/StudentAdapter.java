package com.example.tungnt.androidadvance1_lesson3.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tungnt.androidadvance1_lesson3.R;
import com.example.tungnt.androidadvance1_lesson3.SqlHelper.MyDatabaseHelper;
import com.example.tungnt.androidadvance1_lesson3.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nguyen on 12/05/2017.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<Student> studentList = new ArrayList<>();
    private LayoutInflater layoutInflater;
    MyDatabaseHelper db;

    public StudentAdapter(List<Student> studentList) {
        this.studentList = studentList;
    }

    public StudentAdapter(Context context){
        db = new MyDatabaseHelper(context);
        studentList = db.getAllStudents();
        System.out.println("================= db");
        for (Student st: studentList ) {
            System.out.println(st.toString());
        }
    }

    @Override
    public int getItemCount() {
        return studentList != null ? studentList.size() : 0;
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        View v = layoutInflater.inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position) {
        holder.bindData(studentList.get(position));
    }

    public void updateList(List<Student> students) {
        this.studentList = students;
        notifyDataSetChanged();
    }

    /**
     * StudentViewHolder for item view of list
     */
    public class StudentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvId;
        private TextView tvName;
        private TextView tvAddress;
        private TextView tvPhone;
        private Button btnDelete;

        public StudentViewHolder(View itemView) {
            super(itemView);

            tvId = (TextView) itemView.findViewById(R.id.tv_id);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvAddress = (TextView) itemView.findViewById(R.id.tv_address);
            tvPhone = (TextView) itemView.findViewById(R.id.tv_phone);
            btnDelete = (Button) itemView.findViewById(R.id.btn_delete);

            btnDelete.setOnClickListener(this);
        }

        public void bindData(Student student){
            if (student == null) return;
            tvId.setText(""+student.getId());
            tvName.setText("Name: " + student.getName());
            tvAddress.setText("Address: " + student.getAddress());
            tvPhone.setText("Phone: " + student.getPhone());
        }

        // remove item when click button delete
        @Override
        public void onClick(View v) {
            removeItem(getAdapterPosition());
        }
    }

    public void addItem(int position, Student data) {
        studentList.add(position, data);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        db.deleteStudent(studentList.get(position).getId());
        studentList.remove(position);
        notifyItemRemoved(position);
    }

}

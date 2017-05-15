package com.example.tungnt.Lesson1_RecyclerView.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tungnt.Lesson1_RecyclerView.R;
import com.example.tungnt.Lesson1_RecyclerView.data.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nguyen on 10/05/2017.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.RecyclerViewHolder> {

    private List<Student> students = new ArrayList<>();

    public StudentAdapter(List<Student> students) {
        this.students = students;
    }

    public void updateList(List<Student> students) {
        this.students = students;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup,
                                                 int position) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.item_recyler_view, viewGroup, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder viewHolder, int position) {
        viewHolder.tvName.setText(students.get(position).getName());
    }

    public void addItem(int position, Student data) {
        students.add(position, data);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        students.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * ViewHolder for item view of list
     */
    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        public TextView tvName;
        public ImageView ivDelete;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            ivDelete = (ImageView) itemView.findViewById(R.id.iv_delete);

            // set listener for button delete
            ivDelete.setOnClickListener(this);
        }

        // remove item when click button delete
        @Override
        public void onClick(View v) {
            removeItem(getPosition());
        }
    }

}


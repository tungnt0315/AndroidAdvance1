package com.example.tung.Lesson4_Permision_Load_Image.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tung.Lesson4_Permision_Load_Image.R;
import com.example.tung.Lesson4_Permision_Load_Image.model.Contact;

import java.util.List;

/**
 * Created by tung on 5/14/17.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    List<Contact> mContactList;

    public ContactAdapter(List<Contact> contactList) {
        mContactList = contactList;
    }

    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ContactAdapter.ViewHolder holder, int position) {
        holder.bindData(mContactList.get(position));
    }

    @Override
    public int getItemCount() {
        return mContactList == null ? 0 : mContactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTextName, mTextPhone;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextName = (TextView) itemView.findViewById(R.id.text_name);
            mTextPhone = (TextView) itemView.findViewById(R.id.text_phone);
        }

        public void bindData(Contact contact) {
            if (contact != null) {
                mTextName.setText(contact.getName());
                mTextPhone.setText(contact.getPhone());
            }
        }
    }
}

package com.example.tung.Lesson4_Permision_Load_Image;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tung.Lesson4_Permision_Load_Image.adapter.ContactAdapter;
import com.example.tung.Lesson4_Permision_Load_Image.adapter.ImageAdapter;
import com.example.tung.Lesson4_Permision_Load_Image.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_READ_EXTERNAL_STORAGE = 1;
    private static final int REQUEST_READ_CONTACT = 2;

    private int permissionReadStorage, permissionReadContact;

    private List<String> mImagePathList;
    private ImageAdapter mImageAdapter;

    private List<Contact> mContactList;
    private ContactAdapter mContactAdapter;

    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_READ_EXTERNAL_STORAGE:
                permissionReadStorage = grantResults[0];
                break;
            case REQUEST_READ_CONTACT:
                permissionReadContact = grantResults[0];
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_image_picasso:
                showImages("Picasso");
                break;
            case R.id.btn_image_glide:
                showImages("Glide");
                break;
            case R.id.btn_image_imageload:
                showImages("Universal Image Loader");
                break;
            case R.id.btn_contact:
                showContacts();
                break;
        }
    }

    private void showImages(String loadImageBy) {
        // yeu cau quyen truy cap bo nho
        permissionReadStorage = requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE,
                REQUEST_READ_EXTERNAL_STORAGE,
                "We want to access to your external storage to get image");

        // kiem tra neu co quyen thi lay va hien thi anh ra recyclerview
        if (permissionReadStorage == PackageManager.PERMISSION_GRANTED) {

            // set data vao adapter
            mImagePathList.clear();
            mImagePathList.addAll(getImagePaths());
            mImageAdapter.mLoadImageBy = loadImageBy;
            mImageAdapter.notifyDataSetChanged();

            //gan adapter va layout cho recyclerview
            mRecyclerView.setAdapter(mImageAdapter);
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }

        // neu khong duoc cap quyen thi hien thi thong bao
        else Toast.makeText(this, "Permission Deny !", Toast.LENGTH_SHORT).show();
    }

    private void showContacts() {
        int permissionReadContact = requestPermission(Manifest.permission.READ_CONTACTS,
                REQUEST_READ_CONTACT,
                "We want to access to your contacts data");
        if (permissionReadContact == PackageManager.PERMISSION_GRANTED) {
            mContactList.clear();
            mContactList.addAll(getContacts());
            mContactAdapter.notifyDataSetChanged();
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.setAdapter(mContactAdapter);
        }
        else Toast.makeText(this, "Permission Deny !", Toast.LENGTH_SHORT).show();
    }

    private int requestPermission(String permission, int requestCode, String message) {
        // kiem tra co quyen chua, neu co roi thi bo qua, thoat khoi ham
        int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this, permission);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) return permissionCheck;
        // neu chua co quyen thi yeu cau cap quyen
        else ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);
        return ContextCompat.checkSelfPermission(MainActivity.this, permission);
    }

    private void showDialogRequestPermission(String message, final String permission, final int requestCode) {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage(message)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat
                                .requestPermissions(MainActivity.this,
                                        new String[]{permission}, requestCode);
                    }
                })
                .setNegativeButton("No", null)
                .create()
                .show();
    }

    private List<String> getImagePaths() {
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String projection[] = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        List<String> imagePathList = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                imagePathList.add(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media
                        .DATA)));
            } while (cursor.moveToNext());
            cursor.close();
        }

        if (imagePathList.isEmpty()) {
            imagePathList.add("https://www.w3schools.com/css/img_fjords.jpg");
            imagePathList.add("http://dreamicus.com/data/image/image-02.jpg");
            imagePathList.add("https://www.w3schools.com/css/img_lights.jpg");
            imagePathList.add("https://ih0.redbubble.net/image.3463429.5304/flat,800x800,075,f.jpg");
            imagePathList.add("http://dreamicus.com/data/image/image-06.jpg");
            imagePathList.add("http://beebom.com/wp-content/uploads/2016/01/Reverse-Image-Search-Engines-Apps-And-Its-Uses-2016.jpg");
            imagePathList.add("http://keenthemes.com/preview/metronic/theme/assets/global/plugins/jcrop/demos/demo_files/image2.jpg");
            imagePathList.add("https://www.w3schools.com/css/img_fjords.jpg");
            imagePathList.add("http://dreamicus.com/data/image/image-02.jpg");
            imagePathList.add("https://www.w3schools.com/css/img_lights.jpg");
            imagePathList.add("https://ih0.redbubble.net/image.3463429.5304/flat,800x800,075,f.jpg");
            imagePathList.add("http://dreamicus.com/data/image/image-06.jpg");
            imagePathList.add("http://beebom.com/wp-content/uploads/2016/01/Reverse-Image-Search-Engines-Apps-And-Its-Uses-2016.jpg");
            imagePathList.add("http://keenthemes.com/preview/metronic/theme/assets/global/plugins/jcrop/demos/demo_files/image2.jpg");
        }

        return imagePathList;
    }

    private List<Contact> getContacts() {
        List<Contact> contactList = new ArrayList<>();
        //
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        // lay ra ten cot name va phone cua dien thoai
        String colName = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME;
        String colNumber = ContactsContract.CommonDataKinds.Phone.NUMBER;

        // chi dinh lay ra 2 truong name va phone
        String projection[] = {colName, colNumber};

        // dung cursor duyet toan bo contact lay thong tin them vao list
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                contactList.add(new Contact(cursor.getString(0), cursor.getString(1)));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return contactList;
    }

    private void initWidgets() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_load);
        mImagePathList = new ArrayList<>();
        mImageAdapter = new ImageAdapter(mImagePathList, "Glide");
        mContactList = new ArrayList<>();
        mContactAdapter = new ContactAdapter(mContactList);
        ((Button) findViewById(R.id.btn_image_picasso)).setOnClickListener(this);
        ((Button) findViewById(R.id.btn_image_glide)).setOnClickListener(this);
        ((Button) findViewById(R.id.btn_image_imageload)).setOnClickListener(this);
        ((Button) findViewById(R.id.btn_contact)).setOnClickListener(this);
    }

}


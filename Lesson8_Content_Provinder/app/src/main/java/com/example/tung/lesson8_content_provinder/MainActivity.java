package com.example.tung.lesson8_content_provinder;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tung.lesson8_content_provinder.provider.StudentProvider;

public class MainActivity extends AppCompatActivity {

    EditText txtName, txtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void onClickAddName(View view){

        ContentValues values = new ContentValues();
        values.put(StudentProvider.NAME, (txtName.getText().toString()));
        values.put(StudentProvider.PHONE, (txtPhone.getText().toString()));

        Uri uri = getContentResolver().insert(StudentProvider.CONTENT_URI, values);

        Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
    }

    public void onClickRetrieveStudents (View view){
        String URI = "content://com.example.provider.College/students";
        Uri students = Uri.parse(URI);
        Cursor c = getContentResolver().query(students, null, null, null, null);

        if(c.moveToFirst()){
            do{
                Toast.makeText(this, c.getString(c.getColumnIndex(StudentProvider.ID)) +
                        ", " + c.getString(c.getColumnIndex(StudentProvider.NAME)) +
                        ", " + c.getString(c.getColumnIndex(StudentProvider.PHONE)), Toast.LENGTH_LONG).show();
            }while (c.moveToNext());
        }
    }

    public void initWidget(){
        txtName = (EditText) findViewById(R.id.txtName);
        txtPhone = (EditText) findViewById(R.id.txtPhone);
    }
}

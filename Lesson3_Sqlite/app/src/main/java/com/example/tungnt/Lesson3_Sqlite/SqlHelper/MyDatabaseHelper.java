package com.example.tungnt.Lesson3_Sqlite.SqlHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.tungnt.Lesson3_Sqlite.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nguyen on 11/05/2017.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";

    // Phien ban
    private static final int DATABASE_VERSION = 1;
    // Ten database
    private static final String DATABASE_NAME = "school_manager";
    // Ten bang
    private static final String TABLE_NAME = "student";
    // Ten cac truong
    private static final String KEY_ID ="id";
    private static final String KEY_NAME ="name";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_PHONE = "phone";

    public MyDatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Ham duoc goi sau khi database duoc tao
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "MyDatabaseHelper.onCreate ... ");
        //Tao table
        String script = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_ADDRESS + " TEXT," + KEY_PHONE + " TEXT" + ")";
        db.execSQL(script);
    }

    // Ham duoc goi khi thay doi database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "MyDatabaseHelper.onUpgrade ... ");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void createDefaultStudentsIfNeed()  {
        if(this.getCount() == 0 ) {
            Student student1 = new Student(1, "Nguyen Thanh Tung", "Hoa Khanh", "012345678");
            Student student2 = new Student(2, "Phan Van Tien", "Cam Le", "0234673534");
            this.addStudent(student1);
            this.addStudent(student2);
        };
    }

    public long addStudent(Student student){
        Log.i(TAG, "MyDatabaseHelper.addStudent ... " + student.getName());
        SQLiteDatabase db = this.getWritableDatabase();
        // Tao ContentValues de luu 1 student, roi insert vao database
        ContentValues values = new ContentValues();
        values.put(KEY_ID, student.getId());
        values.put(KEY_NAME, student.getName());
        values.put(KEY_PHONE, student.getPhone());
        values.put(KEY_ADDRESS, student.getAddress());
        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        return result;
    }

    public Student getStudent(int id){
        Log.i(TAG, "MyDatabaseHelper.getStudent ... " + id);
        SQLiteDatabase db = this.getReadableDatabase();
        //Tao cursor de duyet tim tren bang
        Cursor cursor = db.query(TABLE_NAME, new String[] {KEY_ID, KEY_NAME, KEY_ADDRESS, KEY_PHONE}, KEY_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null) cursor.moveToFirst();
        else return new Student(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        return null;
    }

    public List<Student> getAllStudents(){
        Log.i(TAG, "MyDatabaseHelper.getAllStudents ... " );
        List<Student> students = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        // Tao cursor tro vao bang student
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Dung cursor duyet tren bang va add data vao list.
        if (cursor.moveToFirst()) {
            do {
                students.add(new Student(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
            } while (cursor.moveToNext());
        }
        return students;
    }

    public void updateStudent(Student student){
        Log.i(TAG, "MyDatabaseHelper.updateStudent ... " + student.getId());
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, student.getName());
        values.put(KEY_ADDRESS, student.getAddress());
        values.put(KEY_PHONE, student.getPhone());
        db.update(TABLE_NAME, values, KEY_ID + " = ?", new String[] { String.valueOf(student.getId()) });
        db.close();
    }

    public void deleteStudent(int id){
        Log.i(TAG, "MyDatabaseHelper.deleteStudent ... " + id);
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?", new String[] { String.valueOf(id) });
        db.close();
    }

    public int getCount() {
        Log.i(TAG, "MyDatabaseHelper.getCount ... " );
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
}

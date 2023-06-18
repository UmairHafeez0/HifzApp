package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class DbHelper2 extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "student.db";
    private static final String TABLE_NAME = "students";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_ROLLNO = "roll_no";

    public DbHelper2(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_ROLLNO + " TEXT"
                + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }

    public void insertStudent(Student student,Context context) {
        SQLiteDatabase db = this.getWritableDatabase();

        if (isStudentExists(student.getId())) {

            updateStudent(student);
        } else {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, student.getName());
            values.put(COLUMN_ROLLNO, student.getId());

           long newRowId= db.insert(TABLE_NAME, null, values);


            if (newRowId != -1) {
                Toast.makeText(context, "Data inserted successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Failed to insert data", Toast.LENGTH_SHORT).show();
            }

        }

        db.close();
    }

    private boolean isStudentExists(String rollNo) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_ROLLNO + " = ?";
        String[] selectionArgs = {rollNo};

        Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null);
        boolean exists = (cursor.getCount() > 0);

        cursor.close();


        return exists;
    }

    public void updateStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, student.getName());

        db.update(TABLE_NAME, values, COLUMN_ROLLNO + " = ?", new String[]{student.getId()});

        db.close();
    }

    public List<Student> searchStudent(String searchQuery) {
        List<Student> students = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_NAME, COLUMN_ROLLNO};
        String selection = COLUMN_NAME + " LIKE ?";
        String[] selectionArgs = {"%" + searchQuery + "%"};

        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(COLUMN_ID);
            int nameIndex = cursor.getColumnIndex(COLUMN_NAME);
            int rollNoIndex = cursor.getColumnIndex(COLUMN_ROLLNO);

            do {
                int id = cursor.getInt(idIndex);
                String name = cursor.getString(nameIndex);
                String rollNo = cursor.getString(rollNoIndex);

                Student student = new Student(name, rollNo);
                student.setId(String.valueOf(id));

                students.add(student);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return students;
    }
}

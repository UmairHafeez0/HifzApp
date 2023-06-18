package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "students.db";
    private static final String TABLE_NAME = "students";

    private static final String COLUMN_SabqStaringAyat = "StartingAyat";

    private static final String COLUMN_SabqEndingAyat = "EndingAyat";

    private static final String COLUMN_Sabqi = "Sabqi";

    private static final String COLUMN_Manzil = "Manzil";

    private static final String COLUMN_Date = "Date";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_ROLLNO = "roll_no";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + COLUMN_NAME + " TEXT,"
                + COLUMN_ROLLNO + " TEXT,"
                + COLUMN_Date + " TEXT,"
                + COLUMN_SabqEndingAyat + " INTEGER,"
                + COLUMN_SabqStaringAyat + " INTEGER,"
                + COLUMN_Sabqi + " INTEGER,"
                + COLUMN_Manzil + " INTEGER"
                + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }

    public void insertStudent(Student student,String Date) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, student.getName());
        values.put(COLUMN_ROLLNO, student.getRollNo());
        values.put(COLUMN_Date, Date );
        values.put(COLUMN_Manzil,student.getManzil());
        values.put(COLUMN_Sabqi ,student.getSabqi());
        values.put(COLUMN_SabqEndingAyat ,student.getEA());
        values.put(COLUMN_SabqStaringAyat , student.getSA());


        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void updateStudent(Student student,int Date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, student.getName());
        values.put(COLUMN_ROLLNO, student.getRollNo());
        values.put(COLUMN_Date, Date );
        values.put(COLUMN_Manzil,student.getManzil());
        values.put(COLUMN_Sabqi ,student.getSabqi());
        values.put(COLUMN_SabqEndingAyat ,student.getEA());
        values.put(COLUMN_SabqStaringAyat , student.getSA());

        db.update(TABLE_NAME, values, COLUMN_ROLLNO + " = ?", new String[] {student.getRollNo()});
        db.close();
    }

    public void deleteStudent(String rollNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ROLLNO + " = ?", new String[] {rollNo});
        db.close();
    }


    public List<Student> selectAllStudents() {
        List<Student> students = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        /*
        * if (cursorCourses.moveToFirst()) {
            do {

                studentArrayList.add(new StudentModel(cursorCourses.getString(1),
                      cursorCourses.getInt(2),
                        cursorCourses.getInt(3) == 1 ? true : false));
            } while (cursorCourses.moveToNext());

        }
        * */

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                @SuppressLint("Range")  String rollNo = cursor.getString(cursor.getColumnIndex(COLUMN_ROLLNO));
                @SuppressLint("Range") boolean isEnroll = cursor.getInt(cursor.getColumnIndex(COLUMN_ENROLL))>0;
                students.add(new Student(name, rollNo, isEnroll));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return students;
    }
}
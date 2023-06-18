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

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_ROLLNO = "roll_no";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_MANZIL = "manzil";
    private static final String COLUMN_SABQI = "sabqi";
    private static final String COLUMN_SABQ_ENDING_AYAT = "ending_ayat";
    private static final String COLUMN_SABQ_STARTING_AYAT = "starting_ayat";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_ROLLNO + " TEXT,"
                + COLUMN_DATE + " TEXT,"
                + COLUMN_MANZIL + " INTEGER,"
                + COLUMN_SABQI + " INTEGER,"
                + COLUMN_SABQ_ENDING_AYAT + " INTEGER,"
                + COLUMN_SABQ_STARTING_AYAT + " INTEGER"
                + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }

    public void insertStudent(StudentRecord student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, student.getName());
        values.put(COLUMN_ROLLNO, student.getRollNo());
        values.put(COLUMN_DATE, student.getDate());
        values.put(COLUMN_MANZIL, student.getManzil());
        values.put(COLUMN_SABQI, student.getSabqi());
        values.put(COLUMN_SABQ_ENDING_AYAT, student.getEA());
        values.put(COLUMN_SABQ_STARTING_AYAT, student.getSA());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void updateStudent(StudentRecord student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, student.getName());
        values.put(COLUMN_ROLLNO, student.getRollNo());
        values.put(COLUMN_DATE, student.getDate());
        values.put(COLUMN_MANZIL, student.getManzil());
        values.put(COLUMN_SABQI, student.getSabqi());
        values.put(COLUMN_SABQ_ENDING_AYAT, student.getEA());
        values.put(COLUMN_SABQ_STARTING_AYAT, student.getSA());

        db.update(TABLE_NAME, values, COLUMN_ROLLNO + " = ?", new String[]{student.getRollNo()});
        db.close();
    }

    public void deleteStudent(String rollNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ROLLNO + " = ?", new String[]{rollNo});
        db.close();
    }

    public List<StudentRecord> selectAllStudents() {
        List<StudentRecord> students = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        int columnIndexId = cursor.getColumnIndex(COLUMN_ID);
        int columnIndexName = cursor.getColumnIndex(COLUMN_NAME);
        int columnIndexRollNo = cursor.getColumnIndex(COLUMN_ROLLNO);
        int columnIndexDate = cursor.getColumnIndex(COLUMN_DATE);
        int columnIndexManzil = cursor.getColumnIndex(COLUMN_MANZIL);
        int columnIndexSabqi = cursor.getColumnIndex(COLUMN_SABQI);
        int columnIndexEndingAyat = cursor.getColumnIndex(COLUMN_SABQ_ENDING_AYAT);
        int columnIndexStartingAyat = cursor.getColumnIndex(COLUMN_SABQ_STARTING_AYAT);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(columnIndexId);
            String name = cursor.getString(columnIndexName);
            String rollNo = cursor.getString(columnIndexRollNo);
            String date = cursor.getString(columnIndexDate);
            int manzil = cursor.getInt(columnIndexManzil);
            int sabqi = cursor.getInt(columnIndexSabqi);
            int endingAyat = cursor.getInt(columnIndexEndingAyat);
            int startingAyat = cursor.getInt(columnIndexStartingAyat);

            StudentRecord student = new StudentRecord(name, rollNo,startingAyat,endingAyat,sabqi,manzil,date);

            students.add(student);
        }

        cursor.close();
        db.close();

        return students;
    }



}
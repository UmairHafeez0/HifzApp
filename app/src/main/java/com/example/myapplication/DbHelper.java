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


public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "student.db";
    private static final String TABLE_NAME = "studentsRecord";

    private static final String TABLE_NAME2 = "students";

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

        String sql2 = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME2 + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_ROLLNO + " TEXT"
                + ")";
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        String sql2 = "DROP TABLE IF EXISTS " + TABLE_NAME2;
        db.execSQL(sql2);
        onCreate(db);

    }

    public void insertStudent(StudentRecord student, Context context) {
        SQLiteDatabase db = this.getWritableDatabase();


        if (!isStudentExists(student.getRollNo())) {

            Toast.makeText(context, "No student with Roll No: " + student.getRollNo() + " exists.", Toast.LENGTH_SHORT).show();
        } else {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, student.getName());
            values.put(COLUMN_ROLLNO, student.getRollNo());
            values.put(COLUMN_DATE, student.getDate());
            values.put(COLUMN_MANZIL, student.getManzil());
            values.put(COLUMN_SABQI, student.getSabqi());
            values.put(COLUMN_SABQ_ENDING_AYAT, student.getEA());
            values.put(COLUMN_SABQ_STARTING_AYAT, student.getSA());

            db.insert(TABLE_NAME, null, values);
            Toast.makeText(context, "Student Record added successfully", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }

    private boolean isStudentExists(String rollNo) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_ROLLNO + " = ?";
        String[] selectionArgs = {rollNo};

        Cursor cursor = db.query(TABLE_NAME2, null, selection, selectionArgs, null, null, null);
        boolean exists = (cursor.getCount() > 0);

        cursor.close();


        return exists;
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

    public List<StudentRecord> searchStudent(String rollNo) {
        List<StudentRecord> students = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_NAME, COLUMN_ROLLNO, COLUMN_DATE, COLUMN_MANZIL, COLUMN_SABQI, COLUMN_SABQ_ENDING_AYAT, COLUMN_SABQ_STARTING_AYAT};
        String selection = COLUMN_ROLLNO + " = ?";
        String[] selectionArgs = {rollNo};

        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(COLUMN_ID);
            int nameIndex = cursor.getColumnIndex(COLUMN_NAME);
            int rollNoIndex = cursor.getColumnIndex(COLUMN_ROLLNO);
            int dateIndex = cursor.getColumnIndex(COLUMN_DATE);
            int manzilIndex = cursor.getColumnIndex(COLUMN_MANZIL);
            int sabqiIndex = cursor.getColumnIndex(COLUMN_SABQI);
            int endingAyatIndex = cursor.getColumnIndex(COLUMN_SABQ_ENDING_AYAT);
            int startingAyatIndex = cursor.getColumnIndex(COLUMN_SABQ_STARTING_AYAT);

            do {
                int id = cursor.getInt(idIndex);
                String name = cursor.getString(nameIndex);
                String rollNoa = cursor.getString(rollNoIndex);
                String date = cursor.getString(dateIndex);
                int manzil = cursor.getInt(manzilIndex);
                int sabqi = cursor.getInt(sabqiIndex);
                int endingAyat = cursor.getInt(endingAyatIndex);
                int startingAyat = cursor.getInt(startingAyatIndex);

                StudentRecord student = new StudentRecord(name, rollNoa, startingAyat, endingAyat, sabqi, manzil, date);


                students.add(student);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return students;
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



    public void updateStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, student.getName());

        db.update(TABLE_NAME, values, COLUMN_ROLLNO + " = ?", new String[]{student.getId()});

        db.close();
    }

    public List<Student> searchStudents(String searchQuery) {
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
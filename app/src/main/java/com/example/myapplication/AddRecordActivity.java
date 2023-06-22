package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddRecordActivity extends AppCompatActivity {

    private EditText etStudentId, etStartingAyat, etEndingAyat, etSabqi, etMazil, etDate;
    private Button AddRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addrecord);

        etStudentId = findViewById(R.id.etStudentId);
        etStartingAyat = findViewById(R.id.etStartingAyat);
        etEndingAyat = findViewById(R.id.etEndingAyat);
        etSabqi = findViewById(R.id.etSabqi);
        etMazil = findViewById(R.id.etMazil);
        etDate = findViewById(R.id.etDate);
        AddRecord = findViewById(R.id.AddRecord);

        AddRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper dbHelper = new DbHelper(AddRecordActivity.this);
                String studentId = etStudentId.getText().toString().trim();
                String startingAyat = etStartingAyat.getText().toString().trim();
                String endingAyat = etEndingAyat.getText().toString().trim();
                String sabqi = etSabqi.getText().toString().trim();
                String mazil = etMazil.getText().toString().trim();
                String date = etDate.getText().toString().trim();

                int sa = Integer.parseInt(startingAyat);
                int ea =Integer.parseInt(endingAyat);
                if(sa < ea) {
                    String name = dbHelper.getNameByRollNumber(studentId, AddRecordActivity.this);
                    StudentRecord studentRecord = new StudentRecord(name, studentId, Integer.parseInt(startingAyat),
                            Integer.parseInt(endingAyat), Integer.parseInt(sabqi), Integer.parseInt(mazil), date);

                    dbHelper.insertStudent(studentRecord, AddRecordActivity.this);
                    Toast.makeText(AddRecordActivity.this, "Record added successfully", Toast.LENGTH_SHORT).show();

                    finish();
                }
                else
                {
                    Toast.makeText(AddRecordActivity.this, "Starting ayat can not be greater than ending ayat", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
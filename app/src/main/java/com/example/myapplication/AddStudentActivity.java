package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class AddStudentActivity extends AppCompatActivity {
    private EditText etName, etId;
    private Button btnAddStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.students);

        etName = findViewById(R.id.etName);
        etId = findViewById(R.id.etId);
        btnAddStudent = findViewById(R.id.AddStudent);

        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String id = etId.getText().toString();

                Student student = new Student(name, id);

                DbHelper db = new DbHelper(AddStudentActivity.this);
                db.insertStudent(student, AddStudentActivity.this);

                finish();
            }
        });
    }
}

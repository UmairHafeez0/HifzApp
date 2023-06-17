package com.example.myapplication;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAddStudent = findViewById(R.id.btnAddStudent);
        Button btnViewRecord = findViewById(R.id.btnViewRecord);
        Button btnAddRecord = findViewById(R.id.btnAddRecord);
        Button btnCommit = findViewById(R.id.btnCommit);

        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start AddStudentActivity
                Intent intent = new Intent(MainActivity.this, AddStudentActivity.class);
                startActivity(intent);
            }
        });

        btnViewRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start ViewRecordActivity
                Intent intent = new Intent(MainActivity.this, ViewRecordActivity.class);
                startActivity(intent);
            }
        });

        btnAddRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start AddRecordActivity
                Intent intent = new Intent(MainActivity.this, AddRecordActivity.class);
                startActivity(intent);
            }
        });

        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open a website URL
                String url = "https://github.com/UmairHafeez0/HifzApp"; // Replace with your desired website URL
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
    }
}
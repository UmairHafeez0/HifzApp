package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Search extends AppCompatActivity {
    private EditText editTextRollNo;
    private Button buttonSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        editTextRollNo = findViewById(R.id.editTextRollNo);
        buttonSearch = findViewById(R.id.buttonSearch);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rollNo = editTextRollNo.getText().toString().trim();

                // Pass the roll number to the ViewRecordActivity
                Intent intent = new Intent(Search.this, ViewRecordActivity.class);
                intent.putExtra("rollNo", rollNo);
                startActivity(intent);
            }
        });
    }
}

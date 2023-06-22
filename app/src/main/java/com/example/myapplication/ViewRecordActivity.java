package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ViewRecordActivity extends AppCompatActivity {

    List<StudentRecord> friendsList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewrecord);

        String rollNo = getIntent().getStringExtra("rollNo");

        dbHelper = new DbHelper(this);

        friendsList = dbHelper.searchStudent(rollNo);

        recyclerView = findViewById(R.id.recyclerViewStudent);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(ViewRecordActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new myRecyclerViewAdapter(friendsList);
        recyclerView.setAdapter(adapter);
    }
}

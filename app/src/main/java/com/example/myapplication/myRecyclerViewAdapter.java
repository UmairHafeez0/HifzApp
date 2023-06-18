package com.example.myapplication;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class myRecyclerViewAdapter extends RecyclerView.Adapter<myRecyclerViewAdapter.MyVH> {

    List<StudentRecord> friendsList;
    public myRecyclerViewAdapter(List<StudentRecord> friendsList) {
        this.friendsList = friendsList;
    }

    @NonNull
    @Override
    public myRecyclerViewAdapter.MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_item, parent, false);
        return new MyVH(itemView);
    }

    public void onBindViewHolder(@NonNull myRecyclerViewAdapter.MyVH holder, int position) {
        StudentRecord student = friendsList.get(position);

        holder.textDate.setText(student.getDate());
        holder.textRollNo.setText(student.getRollNo());
        holder.textName.setText(student.getName());
        holder.textSabaq.setText("SABAQ:");
        holder.textStartingAyat.setText("STARTING AYAT: " + student.getSA());
        holder.textEndingAyat.setText("ENDING AYAT: " + student.getEA());
        holder.textSabqi.setText("SABQI: " + student.getSabqi());
        holder.textManzil.setText("MANZIL: " + student.getManzil());
    }


    @Override
    public int getItemCount() {
        return friendsList.size();
    }


    public class MyVH extends RecyclerView.ViewHolder {

        TextView textDate;
        TextView textRollNo;
        TextView textName;
        TextView textSabaq;
        TextView textStartingAyat;
        TextView textEndingAyat;
        TextView textSabqi;
        TextView textManzil;

        public MyVH(@NonNull View itemView) {
            super(itemView);
            textDate = itemView.findViewById(R.id.text_date);
            textRollNo = itemView.findViewById(R.id.text_roll_no);
            textName = itemView.findViewById(R.id.text_name);
            textSabaq = itemView.findViewById(R.id.text_sabaq);
            textStartingAyat = itemView.findViewById(R.id.text_starting_ayat);
            textEndingAyat = itemView.findViewById(R.id.text_ending_ayat);
            textSabqi = itemView.findViewById(R.id.text_sabqi);
            textManzil = itemView.findViewById(R.id.text_manzil);
        }
    }

}
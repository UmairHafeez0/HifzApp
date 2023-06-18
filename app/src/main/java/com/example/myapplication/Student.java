package com.example.myapplication;

public class Student {
    private String name;
    private String rollNo;
    private int Starting_ayat;

    private int Ending_ayat;

    private int sabqi;

    private int manzil;

    private String date;

    public Student(String name, String rollNo, int s_a, int e_a , int sabqi , int manzil ,String date) {
        this.name = name;
        this.rollNo = rollNo;
        this.Ending_ayat = e_a;
        this.Starting_ayat = s_a;
        this.manzil = manzil;
        this.sabqi = sabqi;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public void setSA(int s_a)
    {
        this.Starting_ayat = s_a;
    }

    public void setEA(int e_a)
    {
        this.Ending_ayat = e_a;
    }

    public void setSabqi(int sabqi)
    {
        this.sabqi = sabqi;
    }

    public void setManzil(int manzil)
    {
        this.manzil = manzil;
    }

    public int getSA()
    {
        return Starting_ayat;
    }

    public int getEA()
    {
        return Ending_ayat;
    }

    public int getSabqi()
    {
        return sabqi;
    }

    public int getManzil()
    {
        return manzil;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String Date)
    {
        this.date = Date;
    }
    @Override
    public String toString() {
        return "Student name=" + name + ", rollNo=" + rollNo + ", Sabaq starting ayat =" + Starting_ayat + " Sabaq ending ayat+ " + Ending_ayat + " Sabqi : " + sabqi + " Manzi : " + manzil;
    }

}
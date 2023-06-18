package com.example.myapplication;

public class Student {
    private String rollNo;
    private String name;

    public Student(String name,String id) {
        this.name = name;
        this.rollNo = id;
    }

    public String getId() {
        return rollNo;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.rollNo = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + rollNo +
                ", name='" + name + '\'' +
                '}';
    }
}

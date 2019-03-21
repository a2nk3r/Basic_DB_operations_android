package com.example.lab9;

public class Student {
    private int mRollNumber;
    private String mName;
    private int mAge;

    public Student() {
    }

    public Student(int rollNumber, String name, int age) {
        mRollNumber = rollNumber;
        mName = name;
        mAge = age;
    }

    public int getRollNumber() {
        return mRollNumber;
    }

    public void setRollNumber(int rollNumber) {
        mRollNumber = rollNumber;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(int age) {
        mAge = age;
    }
}

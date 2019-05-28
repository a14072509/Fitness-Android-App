package com.example.afs;

import android.app.AppComponentFactory;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

enum Gender
{
    MALE, FEMALE, UNKNOWN
}

public class UserInfo
{

    //instance variables
    private String userName;

    private String email; //optional

    private double weight; // in unit of pounds

    private double height; // in unit of centimeters
    private Gender gender; //'F' = female, 'M' = male, 'U' = Unknown
    private int age;

    //constructors

    public UserInfo(String userName, double weight,
                    double height, Gender gender, int age, String email)
    {
        setUserName(userName);
        setWeight(weight);
        setHeight(height);
        setGender(gender);
        setAge(age);
        setEmail(email);
    }

    public UserInfo()
    {
        setUserName(userName);
        setWeight(weight);
        setHeight(height);
        setGender(gender);
        setAge(age);
        setEmail(email);
    }

    //methods
    public Map<String, Object> toMap()
    {
        HashMap<String, Object> result = new HashMap<>();
        result.put("userName", userName);
        result.put("height", height);
        result.put("weight", weight);
        result.put("Gender", gender);
        result.put("email", email);
        result.put("age", age);
        return result;
    }

    //getters and setters
    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName) { this.userName = userName; }

    public int getAge() {return age;}

    public void setAge(int age) {this.age = age;}

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public double getWeight()
    {
        return weight;
    }

    public void setWeight(double weight)
    {
        this.weight = weight;
    }

    public double getHeight()
    {
        return height;
    }

    public void setHeight(double height)
    {
        this.height = height;
    }

    public Gender getGender()
    {
        return gender;
    }

    public void setGender(Gender gender)
    {
        this.gender = gender;
    }
}
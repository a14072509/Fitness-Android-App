package com.example.afs;

enum Gender
{
    MALE, FEMALE, UNKNOWN
}

public class UserInfo
{

    //instance variables
    private String userName;
    private String password;

    private String email; //optional

    private double weight; // in unit of pounds

    private double height; // in unit of centimeters
    private Gender gender; //'F' = female, 'M' = male, 'U' = Unknown

    //constructors

    public UserInfo(String userName, String password, double weight,
                    double height, Gender gender, String email)
    {
        setUserName(userName);
        setPassword(password);
        setWeight(weight);
        setHeight(height);
        setGender(gender);
        setEmail(email);
    }

    //methods


    //getters and setters
    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName) { this.userName = userName; }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

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
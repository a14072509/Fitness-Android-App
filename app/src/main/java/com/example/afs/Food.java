package com.example.afs;


public class Food
{
    private String name;
    private int calorie;

    public Food(String name, int calorie)
    {
        setName(name);
        setCalorie(calorie);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }
}

package com.example.afs;

import java.util.*;

public class History
{
    private Date date; // the date of the history we are recording
    private int calNeeded; // the calorie goal for that day
    private int calSpent; // the calorie spent on that day
    private int calTaken; // the calorie intake on that day
    private Map<String, Integer> exercise; // the list of exercises performed
    private Map<String, Integer> food; // the food that were eaten
    private int result;

    //Constructors
    public History(Date date, int calNeeded)
    {
        setDate(date);
        setCalNeeded(calNeeded);
        exercise = new HashMap<String, Integer>();
        food = new HashMap<String, Integer>();
        calSpent = 0;
        calTaken = 0;
        result = 0;
    }

    //Methods, these should be tested
    public void addExercise(String name, Integer calorie)
    {
        exercise.put(name, calorie);
        calSpent += calorie;
    }

    public void addFood(String name, Integer calorie)
    {
        food.put(name, calorie);
        calTaken += calorie;
    }

    /**
     * Remove the exercise and change the calSpent
     * @param name The name of the exercise to be removed
     * @return True if the exercise is found, False if not
     */
    public boolean removeExercise(String name)
    {
        Integer calorie = exercise.remove(name);
        if(calorie == null)
            return false;
        calSpent -= calorie;
        return true;
    }

    // same as above
    public boolean removeFood(String name)
    {
        Integer calorie = food.remove(name);
        if(calorie == null)
            return false;
        calTaken -= calorie;
        return true;
    }


    //Setters
    public void setDate(Date date) { this.date = date; }

    public void setCalNeeded(int calNeeded) { this.calNeeded = calNeeded; }

    //Getters
    public Date getDate() { return date; }

    public int getCalNeeded() { return calNeeded; }

    public int getCalSpent() { return calSpent; }

    public int getCalTaken() { return calTaken; }

    public Map<String, Integer> getExercise() { return exercise; }

    public Map<String, Integer> getFood() { return food; }

    public int getResult() {
        result = calTaken - calSpent;
        return result;
    }
}

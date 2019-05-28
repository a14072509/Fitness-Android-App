package com.example.afs;
import java.util.*;
public class Diet
{

    //A class that just contains name and calorie to provide convenience for queue
    protected class FoodCal
    {
        String name;
        int calorie;

        public FoodCal(String name, int calorie)
        {
            this.name = name;
            this.calorie = calorie;
        }
    }

    private final int HISTORY_SIZE = 20;
    private Queue<FoodCal> history; //The food-calorie entered by use. First in first out
    private List<FoodCal> given; //The food-calorie that are pre-registered


    public void addFood(String name, int calorie)
    {
        history.add(new FoodCal(name, calorie));

        // maintain the size to be 20
        if(history.size() > HISTORY_SIZE )
            history.remove();
    }



}

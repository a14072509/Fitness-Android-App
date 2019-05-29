package com.example.afs;
import java.util.*;
public class Diet
{

    private final int HISTORY_SIZE = 20;
    private Queue<Food> history; //The food-calorie entered by use. First in first out

    public void addFood(String name, int calorie)
    {
        history.add(new Food(name, calorie));

        // maintain the size to be 20
        if(history.size() > HISTORY_SIZE )
            history.remove();
    }

}

package com.example.afs;
import java.util.*;
public class BodyPart
{
    Map<String, List<Exercise>> bodyToExercise;

    //The path to the csv file that contains all the exercise information.
    private final String dataPath = "";

    public BodyPart()
    {
        bodyToExercise = new TreeMap<String, List<Exercise>>();
        setUp();
    }

    /**
     * This method will read the file at dataPath and parse the information
     * to store them into the map. So that once we update the csv file, all the exercises
     * will be updated automatically.
     */
    private void setUp()
    {
        //TODO
    }
}

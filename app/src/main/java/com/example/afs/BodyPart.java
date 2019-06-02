package com.example.afs;
import android.content.Context;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
public class BodyPart
{
    Map<String, List<Exercise>> bodyToExercise;

    //The path to the csv file that contains all the exercise information.
    private final String dataPath = "exerciseInstruction.csv";
    private Context context;

    public BodyPart(Context context)
    {
        bodyToExercise = new TreeMap<String, List<Exercise>>();
        this.context = context;
        try {
            setUp();
        }
        catch(IOException e){
            System.err.println("Failed to parse file");
            return;
        }
        System.err.println("Successful");
    }

    /**
     * This method will read the file at dataPath and parse the information
     * to store them into the map. So that once we update the csv file, all the exercises
     * will be updated automatically.
     */
    private void setUp() throws IOException {
        InputStream is = context.getAssets().open(dataPath);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        br.readLine();
        while(br.ready())
        {
            storeLine(br.readLine().replace("\"", ""));
        }
    }

    private void storeLine(String str)
    {
        int comma = str.indexOf(",");
        String number = str.substring(0, comma);
        str = str.substring(comma+1);

        comma = str.indexOf(",");
        String name = str.substring(0, comma);
        str = str.substring(comma+1);

        comma = str.indexOf(",");
        String eqpt = str.substring(0, comma);
        str = str.substring(comma+1);

        comma = str.indexOf(",");
        String bodyPart = str.substring(0, comma);
        str = str.substring(comma+1);

        String instruction = str;

        Exercise curExer = new Exercise(name, eqpt, instruction, bodyPart+number, null);
        if(!bodyToExercise.containsKey(bodyPart))
            bodyToExercise.put(bodyPart, new ArrayList<Exercise>());
        bodyToExercise.get(bodyPart).add(curExer);
    }
}

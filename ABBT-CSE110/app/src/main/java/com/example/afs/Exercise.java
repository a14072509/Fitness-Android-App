package com.example.afs;
import java.util.*;

public class Exercise {
    private String name; //name of the exercise
    private String equipment; //the equipment associated
    private String instructions; //the instruction
    private String imagePath; //the path to the image file
    private String gifPath; //the path to the gif file

    //most likely going to be TreeMap to sort by weight for convenience
    private Map<Integer, Integer> weightCal; //the mapping between weight and calorie


    public Exercise(String name, String equipment, String instructions, String imagePath,
                    String gifPath, Map<Integer, Integer> weightCal)
    {
        setName(name);
        setEquipment(equipment);
        setInstructions(instructions);
        setImagePath(imagePath);
        setGifPath(gifPath);
        setWeightCal(weightCal);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getGifPath() {
        return gifPath;
    }

    public void setGifPath(String gifPath) {
        this.gifPath = gifPath;
    }

    public Map<Integer, Integer> getWeightCal() {
        return weightCal;
    }

    public void setWeightCal(Map<Integer, Integer> weightCal) {
        this.weightCal = weightCal;
    }

}

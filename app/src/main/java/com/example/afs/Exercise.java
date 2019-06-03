package com.example.afs;
import java.util.*;

public class Exercise {
    private String name; //name of the exercise
    private String equipment; //the equipment associated
    private String instructions; //the instruction
    private String imagePath; //the path to the image file
    private double met;

    //most likely going to be TreeMap to sort by weight for convenience
    private Map<Integer, Integer> weightCal; //the mapping between weight and calorie

    public Exercise(String name, String imagePath)
    {
        setName(name);
        setImagePath(imagePath);
    }

    public Exercise(String name, String equipment, String instructions, String imagePath, double met)
    {
        setName(name);
        setEquipment(equipment);
        setInstructions(instructions);
        setImagePath(imagePath);
        setMet(met);
    }

    public String toString()
    {
        return getName() + ", " + getEquipment() + ", " + getImagePath() + ", " + getMet() + ", " + getInstructions();
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
        this.imagePath = imagePath.toLowerCase().replace(" ", "");
    }

    public double getMet() {
        return met;
    }

    public void setMet(double met) {
        this.met = met;
    }
}

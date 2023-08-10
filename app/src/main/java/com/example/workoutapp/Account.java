package com.example.workoutapp;

//this is a test push
public class Account {

    private int id;
    private String name;
    private double weight;
    private int Goal;
    private String dateOfBirth;
    private int heightFeet;
    private int heightInches;


    public Account(int id, String name, double weight, int Goal, String dateOfBirth, int heightFeet, int heightInches) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.Goal = Goal;
        this.dateOfBirth = dateOfBirth;
        this.heightFeet = heightFeet;
        this.heightInches = heightInches;

    }

    private void setId(int id) {
        this.id = id;
    }
    public int getId() { return id; }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getGoal() { return Goal; }

    public void setGoal(int goal) { Goal = goal; }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getHeightFeet() {
        return heightFeet;
    }

    public void setHeightFeet(int heightFeet) {
        this.heightFeet = heightFeet;
    }

    public int getHeightInches() {
        return heightInches;
    }

    public void setHeightInches(int heightInches) {
        this.heightInches = heightInches;
    }
}

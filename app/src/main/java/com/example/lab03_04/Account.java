package com.example.lab03_04;

import android.content.res.AssetManager;

public class Account {

    private int id;
    private String name;
    private int age;
    private int weight;
    private int goal;
    private int heightFt;
    private int heightIn;
    private String bday;
    private int workoutsCompleted;

    public Account(int id, String name, int age, int weight, int goal, int heightFt, int heightIn, String bday) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.goal = goal;
        this.heightFt = heightFt;
        this.heightIn = heightIn;
        this.bday = bday;
        this.workoutsCompleted = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public int getHeightFt() {
        return heightFt;
    }

    public void setHeightFt(int heightFt) {
        this.heightFt = heightFt;
    }

    public int getHeightIn() {
        return heightIn;
    }

    public void setHeightIn(int heightIn) {
        this.heightIn = heightIn;
    }

    public String getBday() {
        return bday;
    }

    public void setBday(String bday) {
        this.bday = bday;
    }

    public int getWorkoutsCompleted() {
        return workoutsCompleted;
    }

    public void setWorkoutsCompleted(int workoutsCompleted) {
        this.workoutsCompleted = workoutsCompleted;
    }

}



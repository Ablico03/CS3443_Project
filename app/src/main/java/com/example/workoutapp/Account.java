package com.example.workoutapp;

import android.content.res.AssetManager;
//this is a test push
public class Account {

    private int id;
    private String name;
    private String email;

    public Account(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    private void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}

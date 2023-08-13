package com.example.lab03_04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.ComponentActivity;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class HistoryActivity extends ComponentActivity {

    private int id;
    //File f = new File(getFilesDir().getAbsolutePath() +"/workouts.txt");
    Scanner s;
    String str = "";
    String[] arr = null;

    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        setupButtons();
        Intent intent = getIntent();
        id = intent.getIntExtra("id",-1);
        TextView history = (TextView) findViewById(R.id.historyEntry);
        history.setText("hello world");
    }

    private void setupButtons() {
        ImageButton profileButton;
        ImageButton workoutsButton;

        profileButton = (ImageButton) findViewById(R.id.navProfileHistory);
        workoutsButton = (ImageButton) findViewById(R.id.navWorkoutHistory);

        profileButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoryActivity.this, ProfileActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        workoutsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HistoryActivity.this, CreateWorkoutActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }
/*
    private void getHistory(File f){
        try {
            if(f.exists()) {
                TextView history = (TextView) findViewById(R.id.historyEntry);

                s = new Scanner(openFileInput("workouts.txt"));
                if (s.hasNext()) {
                    str = s.nextLine();
                    arr = str.split(",");

                    history.setText(arr[1]);
                }
                s.close();
            }
        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
*/
    private boolean verifyHistory(){
        return true;
    }
}

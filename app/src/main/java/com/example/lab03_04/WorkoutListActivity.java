package com.example.lab03_04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import androidx.activity.ComponentActivity;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class WorkoutListActivity extends ComponentActivity {
    private int id;
    private ArrayList<String> workoutList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workoutlist);

        Intent intent = getIntent();
        id = intent.getIntExtra("id",-1);

        loadWorkouts();

        ListView listView = findViewById(R.id.workoutListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, workoutList);
        listView.setAdapter(adapter);

        setupButtons();
    }

    private void loadWorkouts() {
        String name = "username"; // Fetch the user's name or ID here
        File file = new File(getFilesDir(), name + "workouts.txt");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                workoutList.add(line.split(",")[1]); // Assuming 2nd element is the workout name
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "Error loading workouts", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupButtons() {
        ImageButton profileButton = findViewById(R.id.navProfileList);
        ImageButton createWorkoutButton = findViewById(R.id.navCreateWorkoutList);

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutListActivity.this, ProfileActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        createWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkoutListActivity.this, CreateWorkoutActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }
}

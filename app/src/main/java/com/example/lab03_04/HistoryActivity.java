package com.example.lab03_04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.ComponentActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class HistoryActivity extends ComponentActivity {

    private int id;
    Scanner s;
    String str = "";
    String name = "";
    String fileName = "";
    String[] arr = null;
    String[][] arr1 = null;
    String f = "";


    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        Intent intent = getIntent();
        id = intent.getIntExtra("id",-1);
        try{
            name = getUserName(id);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        fileName = "/" + name + "workout.txt";
        f = getFilesDir().getAbsolutePath() + fileName;
        int numWorkouts = 0;
        //numWorkouts = getHistory(f, numWorkouts);
        setupButtons();
    }

    private void setupButtons() {
        ImageButton profileButton;
        ImageButton workoutsButton;
        Button prev;
        Button next;
        TextView Name = (TextView) findViewById(R.id.histNameDisplay);
        TextView Type = (TextView) findViewById(R.id.histTypeDisplay);
        TextView Weight = (TextView) findViewById(R.id.histWeightDisplay);
        TextView Sets = (TextView) findViewById(R.id.histSetsDisplay);
        TextView Reps = (TextView) findViewById(R.id.histRepsDisplay);

        profileButton = (ImageButton) findViewById(R.id.navProfileHistory);
        workoutsButton = (ImageButton) findViewById(R.id.navWorkoutHistory);
        prev = (Button) findViewById(R.id.prevEntry);
        next = (Button) findViewById(R.id.nextEntry);

        profileButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoryActivity.this, ProfileActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /*if (arr1[numWorkouts-1][0] != null) {//if previous array index contains values, set hist textViews to those values
                    Name.setText(arr1[numWorkouts-1][1]);
                    Type.setText(arr1[numWorkouts-1][2]);
                    Weight.setText(arr1[numWorkouts-1][3]);
                    Sets.setText(arr1[numWorkouts-1][4]);
                    Reps.setText(arr1[numWorkouts-1][5]);
                }*/
                Name.setText("orp");
                Type.setText("morp");
                Weight.setText("klorpengorp");
                Sets.setText("mek");
                Reps.setText("sek");
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /*if (arr1[numWorkouts+1][0] != null) {//if previous array index contains values, set hist textViews to those values
                    Name.setText(arr1[numWorkouts+1][1]);
                    Type.setText(arr1[numWorkouts+1][2]);
                    Weight.setText(arr1[numWorkouts+1][3]);
                    Sets.setText(arr1[numWorkouts+1][4]);
                    Reps.setText(arr1[numWorkouts+1][5]);
                }*/
                Name.setText("borp");
                Type.setText("dorp");
                Weight.setText("forpenorp");
                Sets.setText("gork");
                Reps.setText("mork");
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

    private String getUserName(int id) throws FileNotFoundException {
        File f = new File(getFilesDir().getAbsolutePath() + "/accounts.txt");
        Scanner s;
        String name = "";
        String str = "";
        String[] arr = null;
        try {
            if(f.exists()) {
                s = new Scanner(f);
                while (s.hasNext()) {
                    str = s.nextLine();
                    arr = str.split(",");
                    if (Integer.parseInt(arr[0]) == id) {
                        return arr[1];
                    }
                }
                s.close();
            }
        }
        catch (Exception e) {
            Toast.makeText(getBaseContext(), "Exception while getting name", Toast.LENGTH_SHORT).show();
        }

        return "noName";
    }

    private int getHistory(String f, int i){
        TextView Name = (TextView) findViewById(R.id.histNameDisplay);
        TextView Type = (TextView) findViewById(R.id.histTypeDisplay);
        TextView Weight = (TextView) findViewById(R.id.histWeightDisplay);
        TextView Sets = (TextView) findViewById(R.id.histSetsDisplay);
        TextView Reps = (TextView) findViewById(R.id.histRepsDisplay);
        try {

            s = new Scanner(openFileInput(f));
            i = 0;

            while (s.hasNext()) {
                str = s.nextLine();
                arr = str.split(",");

                for(int j = 0; j < 6; j++){
                    arr1[i][j] = arr[j];
                }
                i++;
            }
            Name.setText(arr1[i][1]);
            Type.setText(arr1[i][2]);
            Weight.setText(arr1[i][3]);
            Sets.setText(arr1[i][4]);
            Reps.setText(arr1[i][5]);
            Toast.makeText(getBaseContext(), "index: " + i, Toast.LENGTH_SHORT).show();
            s.close();
        }
        catch (Exception e) {
            Toast.makeText(getBaseContext(), "Exception while getting history" + i, Toast.LENGTH_SHORT).show();
        }
        return i;
    }
}

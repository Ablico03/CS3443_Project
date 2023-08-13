package com.example.lab03_04;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.ComponentActivity;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class CreateWorkoutActivity extends ComponentActivity{
    private Account profileInfo;
    private AssetManager assets;
    public int id;
        public void onCreate(Bundle savedInstanceState){

            super.onCreate(savedInstanceState);
            setContentView(R.layout.createworkout);
            setupButtons();

            Intent intent = getIntent();
            id = intent.getIntExtra("id",-1);
        }

        private void setupButtons(){

            Button buttonCreate = (Button) findViewById(R.id.workoutCreate);

            //ImageButton workoutsButton;
            ImageButton profileButton;
            ImageButton historyButton;

            // Nav Bar Buttons
            profileButton = (ImageButton) findViewById(R.id.navProfileWorkout);
            historyButton = (ImageButton) findViewById(R.id.navHistoryWorkout);
            //workoutsButton = (ImageButton) findViewById(R.id.navWorkoutProfile);

            buttonCreate.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    //int id = -1;

                    EditText wNInput = (EditText) findViewById(R.id.workoutName);
                    EditText wTInput = (EditText) findViewById(R.id.workoutType);
                    EditText wWInput = (EditText) findViewById(R.id.workoutWeight);
                    EditText wSInput = (EditText) findViewById(R.id.editTextText2);
                    EditText wRInput = (EditText) findViewById(R.id.workoutReps);




                    if(validateAccountInfo()){
                        //profile info to retrieve name for newfile
                        //String name = profileInfo.getName();
                        createWorkout(Integer.toString(id));
                        Toast.makeText(getBaseContext(), "hey", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else{
                        wNInput.setText("");
                        wTInput.setText("");
                        wWInput.setText("");
                        wSInput.setText("");
                        wRInput.setText("");
                        wNInput.setError("All fields must be filled out");
                        wTInput.setError("All fields must be filled out");
                        wWInput.setError("All fields must be filled out");
                        wSInput.setError("All fields must be filled out");
                        wRInput.setError("All fields must be filled out");
                    }
                }
            });


            // Moves to history page - TODO set intent to History class once implemented
            historyButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    /*Intent intent = new Intent(ProfileActivity.this, history.class);
                    intent.putExtra("id",id);
                    startActivity(intent); */
                    Toast.makeText(getBaseContext(), "HISTORY NEEDS TO BE IMPLEMENTED", Toast.LENGTH_LONG).show();
                }
            });

            // Move to profile activity
            profileButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(CreateWorkoutActivity.this, ProfileActivity.class);
                    intent.putExtra("id",id);
                    startActivity(intent);
                }
            });
        }

    private boolean validateAccountInfo(){

        EditText wNInput = (EditText) findViewById(R.id.workoutName);
        EditText wTInput = (EditText) findViewById(R.id.workoutType);
        EditText wWInput = (EditText) findViewById(R.id.workoutWeight);
        EditText wSInput = (EditText) findViewById(R.id.editTextText2);
        EditText wRInput = (EditText) findViewById(R.id.workoutReps);
            if(!wNInput.getText().toString().equals("") && !wTInput.getText().toString().equals("") &&
                    !wWInput.getText().toString().equals("") && !wSInput.getText().toString().equals("")
            && !wRInput.getText().toString().equals("")){
                return true;
            }
            return false;
        }
        private void createWorkout(String name){


            EditText wNInput = (EditText) findViewById(R.id.workoutName);
            EditText wTInput = (EditText) findViewById(R.id.workoutType);
            EditText wWInput = (EditText) findViewById(R.id.workoutWeight);
            EditText wSInput = (EditText) findViewById(R.id.editTextText2);
            EditText wRInput = (EditText) findViewById(R.id.workoutReps);
            String workoutName = wNInput.getText().toString();
            String workoutType = wTInput.getText().toString();
            String workoutWeight = wWInput.getText().toString();
            String workoutSets = wSInput.getText().toString();
            String workoutReps = wRInput.getText().toString();

            String tName = profileInfo.getName() + "workouts.txt";


            File f = new File(getFilesDir().getAbsolutePath() + "/" + tName);
            OutputStreamWriter w = null;
            if(!f.exists()){
                try {
                    w = new OutputStreamWriter(openFileOutput(tName, MODE_PRIVATE));
                    w.write(profileInfo.getName() + "," + workoutName + "," + workoutType + "," + workoutWeight + "," + workoutSets + "," + workoutReps);
                    w.close();
                }
                catch(IOException e){
                    Toast.makeText(getBaseContext(),"IOException" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            else{
                try {
                    w = new OutputStreamWriter(openFileOutput(tName, MODE_APPEND));
                    w.append("\n" + profileInfo.getName() +","+workoutName+","+workoutType+","+workoutWeight+","+workoutSets+","+workoutReps);
                    w.close();
                }
                catch(IOException e){
                    Toast.makeText(getBaseContext(),"IOException" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
package com.example.lab03_04;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
        public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.createworkout);
            setupButtons();
        }

        private void setupButtons(){
            Button button1 = (Button) findViewById(R.id.workoutCreate);
            button1.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int id = -1;
                    TextView wNInput = (TextView) findViewById(R.id.workoutName);
                    TextView wTInput = (TextView) findViewById(R.id.workoutType);
                    TextView wWInput = (TextView) findViewById(R.id.workoutWeight);
                    TextView wSInput = (TextView) findViewById(R.id.workoutSets);
                    TextView wRInput = (TextView) findViewById(R.id.workoutReps);

                    if(validateAccountInfo()){
                        //profile info to retrieve name for newfile
                        String name = profileInfo.getName();
                        createWorkout(name);
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
        }

    private boolean validateAccountInfo(){
            TextView wNInput = (TextView) findViewById(R.id.workoutName);
            TextView wTInput = (TextView) findViewById(R.id.workoutType);
            TextView wWInput = (TextView) findViewById(R.id.workoutWeight);
            TextView wSInput = (TextView) findViewById(R.id.workoutSets);
            TextView wRInput = (TextView) findViewById(R.id.workoutReps);
            if(!wNInput.getText().toString().equals("") && !wTInput.getText().toString().equals("") &&
                    !wWInput.getText().toString().equals("") && !wSInput.getText().toString().equals("")
            && !wRInput.getText().toString().equals("")){
                return true;
            }
            return false;
        }
        private void createWorkout(String name){
            TextView wNInput = (TextView) findViewById(R.id.workoutName);
            TextView wTInput = (TextView) findViewById(R.id.workoutType);
            TextView wWInput = (TextView) findViewById(R.id.workoutWeight);
            TextView wSInput = (TextView) findViewById(R.id.workoutSets);
            TextView wRInput = (TextView) findViewById(R.id.workoutReps);
            String workoutName = wNInput.getText().toString();
            String workoutType = wTInput.getText().toString();
            String workoutWeight = wWInput.getText().toString();
            String workoutSets = wSInput.getText().toString();
            String workoutReps = wRInput.getText().toString();

            String nameF = name + "workouts.txt";
            String nameDir = "/"+nameF;
            File f = new File(getFilesDir().getAbsolutePath() + nameDir);
            OutputStreamWriter w = null;
            if(!f.exists()){
                try {
                    w = new OutputStreamWriter(openFileOutput(nameF, MODE_PRIVATE));
                    w.write(name + "," + workoutName + "," + workoutType + "," + workoutWeight + "," + workoutSets + "," + workoutReps);
                    w.close();
                }
                catch(IOException e){
                    Toast.makeText(getBaseContext(),"IOException" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            else{
                try {
                    w = new OutputStreamWriter(openFileOutput(nameF, MODE_APPEND));
                    w.append("\n" +  name +","+workoutName+","+workoutType+","+workoutWeight+","+workoutSets+","+workoutReps);
                    w.close();
                }
                catch(IOException e){
                    Toast.makeText(getBaseContext(),"IOException" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
package com.example.workoutapp;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.ComponentActivity;

import java.io.IOException;
import java.util.Scanner;


public class ProfileActivity extends ComponentActivity {

    private Account profileInfo;
    private AssetManager assets;
    Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        assets = getAssets();
        setUpProfile();
        setUpButtons();
    }

    private void setUpButtons() {
        button = (Button) findViewById(R.id.buttonReturn);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setUpProfile() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",-1);

        Scanner s;
        String str = "";
        String[] creds = null;

        try {
            s = new Scanner(assets.open("accounts.txt"));
            while(s.hasNext()) {
                str = s.nextLine();
                creds = str.split(",");
                if(Integer.parseInt(creds[0]) == id) {
                    profileInfo = new Account(id,creds[1],creds[2]);
                    break;
                }
            }
            s.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        TextView name = (TextView) findViewById(R.id.profNameString);
        TextView email = (TextView) findViewById(R.id.nameString);
        name.setText(profileInfo.getName());
        email.setText(profileInfo.getEmail());
    }
}

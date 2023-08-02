package com.example.workoutapp;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.ComponentActivity;

import java.io.IOException;
import java.util.Scanner;

public class MainActivity extends ComponentActivity {
    private Button button;
    private AssetManager assets;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login2);
        assets = getAssets();
        //Toast.makeText(this, "Hello World", Toast.LENGTH_SHORT).show();
        setUpButtons();
    }
    private void setUpButtons() {
        button = (Button) findViewById(R.id.login);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText uText = (EditText) findViewById(R.id.userNameString);
                EditText pText = (EditText) findViewById(R.id.passwordString);
                int id = authenticate(uText.getText().toString(), pText.getText().toString());

                if(id > 0) {
                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    intent.putExtra("id",id);
                    startActivity(intent);
                } else {
                    uText.setText("");
                    pText.setText("");
                    uText.setError("Incorrect username and password combination.");
                    pText.setError("Incorrect username and password combination.");
                }
            }
        });
    }
    private int authenticate(String username, String password) {
        Scanner s;
        String str = "";
        String[] creds = null;
        int id = -1;

        try {
            s = new Scanner(assets.open("login.txt"));
            while(s.hasNext()) {
                str = s.nextLine();
                creds = str.split(",");
                if(username.equalsIgnoreCase(creds[1]) && password.equals(creds[2])) {
                    id = Integer.parseInt(creds[0]);
                    break;
                }
            }
            s.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return id;
    }
}

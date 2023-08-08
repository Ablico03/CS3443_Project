package com.example.lab03_04;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.ComponentActivity;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class RegisterActivity extends ComponentActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        setUpButtons();
    }

    private void setUpButtons() {
        Button button1 = (Button) findViewById(R.id.submitRegister);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int id = -1;
                EditText unameInput = (EditText) findViewById(R.id.registerusername_input);
                EditText passInput = (EditText) findViewById(R.id.registerpassword_input);
                EditText nameInput = (EditText) findViewById(R.id.registername_input);
                EditText emailInput = (EditText) findViewById(R.id.registeremail_input);

                if(validateAccountInfo()) {
                    id = createLogin();
                    if (id > 0) {
                        createAccount(id);
                    }
                    finish();
                } else {
                    unameInput.setText("");
                    passInput.setText("");
                    nameInput.setText("");
                    emailInput.setText("");
                    unameInput.setError("All fields must be filled out.");
                    passInput.setError("All fields must be filled out.");
                    nameInput.setError("All fields must be filled out.");
                    emailInput.setError("All fields must be filled out.");
                }
            }
        });
    }

    private boolean validateAccountInfo() {
        EditText unameInput = (EditText) findViewById(R.id.registerusername_input);
        EditText passInput = (EditText) findViewById(R.id.registerpassword_input);
        EditText nameInput = (EditText) findViewById(R.id.registername_input);
        EditText emailInput = (EditText) findViewById(R.id.registeremail_input);

        if(!unameInput.getText().toString().equals("") && !passInput.getText().toString().equals("") && !nameInput.getText().toString().equals("") && !emailInput.getText().toString().equals("")) {
            return true;
        }
        else return false;
    }

    private int createLogin() {
        EditText unameInput = (EditText) findViewById(R.id.registerusername_input);
        EditText passInput = (EditText) findViewById(R.id.registerpassword_input);
        String username = unameInput.getText().toString();
        String password = passInput.getText().toString();


        File f = new File(getFilesDir().getAbsolutePath() + "/login.txt");
        Scanner s;
        int id = -1;
        String str = null;
        String[] arr;
        if(!f.exists()) {
            id = 1;
        try {
            OutputStreamWriter w = new OutputStreamWriter(openFileOutput("login.txt", MODE_PRIVATE));
            w.write(id + "," + username + "," + password);
            w.close();
        } catch(IOException e) {
            Toast.makeText(getBaseContext(), "IOEXCEPTION: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        } else {
            try {
                s = new Scanner(openFileInput("login.txt"));
                while (s.hasNextLine()) {
                    str = s.nextLine();
                }
                if (str != null) {
                    arr = str.split(",");
                   if(arr.length == 3) {
                       id = Integer.parseInt(arr[0]) + 1;
                   }
                }
                s.close();

                OutputStreamWriter w = new OutputStreamWriter(openFileOutput("login.txt", MODE_APPEND));
                w.append("\n" + id + "," + username + "," + password);
                w.close();
            }
            catch(IOException e) {
                Toast.makeText(getBaseContext(), "IOEXCEPTION: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        return id;
    }


        private void createAccount(int id) {

            EditText nameInput = (EditText) findViewById(R.id.registername_input);
            EditText emailInput = (EditText) findViewById(R.id.registeremail_input);
            String name = nameInput.getText().toString();
            String email = emailInput.getText().toString();


            File f = new File(getFilesDir().getAbsolutePath() + "/accounts.txt");
            OutputStreamWriter w = null;

            if (!f.exists()) {
                try {
                    w = new OutputStreamWriter(openFileOutput("accounts.txt", MODE_PRIVATE));
                    w.write(id + "," + name + "," + email);
                    w.close();
                } catch (IOException e) {
                    Toast.makeText(getBaseContext(), "IOEXCEPTION: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            } else {
                try {
                    w = new OutputStreamWriter(openFileOutput("accounts.txt", MODE_APPEND));
                    w.append("\n" + id + "," + name + "," + email);
                    w.close();
                } catch (IOException e) {
                    Toast.makeText(getBaseContext(), "IOEXCEPTION: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
}




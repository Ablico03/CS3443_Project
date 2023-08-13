package com.example.lab03_04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.ComponentActivity;

public class HistoryActivity extends ComponentActivity {

    private int id;

    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        setupButtons();
        Intent intent = getIntent();
        id = intent.getIntExtra("id",-1);
    }
    private void setupButtons() {
        Button returnButton = (Button) findViewById(R.id.historyReturn);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoryActivity.this, ProfileActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }

    private boolean verifyHistory(){

        return true;
    }
}

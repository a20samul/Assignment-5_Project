package com.example.assignment5project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entered);

        String name = "";

        Intent intent = getIntent();
        if (intent != null){
            String temp = intent.getStringExtra("name");
            if (temp != null) {
                name=temp;
            }
        }

        TextView textName = findViewById(R.id.text_name);
        textName.setText(name);



    }
}
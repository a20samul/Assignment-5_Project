package com.example.assignment5project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

        
        Button wondersButton = findViewById(R.id.button_wonder);
        wondersButton.setOnClickListener(view -> {
            Intent intent2 = new Intent(SecondActivity.this, ListviewActivity.class);
            startActivity(intent2);
            Log.d("TAG", "Start ListviewActivity");
        });

    }
}
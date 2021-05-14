package com.example.assignment5project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class About extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dropdown_menu, menu);
        return true;
    }

    Button returnButton = findViewById(R.id.return_button);
    returnButton.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            Log.d("END", "Finish app");
            finish();

        }
    });
}
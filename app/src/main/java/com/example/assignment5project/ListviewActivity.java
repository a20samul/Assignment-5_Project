package com.example.assignment5project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class ListviewActivity extends AppCompatActivity {

    private ArrayList<Wonders> items;
    private ArrayAdapter<Wonders> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
    }
}
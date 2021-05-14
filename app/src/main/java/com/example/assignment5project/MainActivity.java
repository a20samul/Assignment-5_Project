package com.example.assignment5project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dropdown_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.about_page:
                Intent intent = new Intent(MainActivity.this, About.class);
                Log.d("ABOUT", "About page");
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


  //  @Override
    //public boolean onOptionsItemSelected(MenuItem item) {

    //  int id = item.getItemId();

        //noinspection SimplifiableIfStatement
      //  if (id == R.id.about_page)
        //showAboutPage();
    //onMenuItemSelected(R.id.dropdown_menu, R.id.about_page) {
      //      Intent intent = new Intent(MainActivity.this, About.class);
        //    Log.d("==>","Will display About page");
          //  return true;
    // }
      //  return super.onOptionsItemSelected(item);
    //}

    //public void showAboutPage(){
    //  loadurl(file///:assets/about.html)
    // }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Button startButton = findViewById(R.id.enter_button);
        //startButton.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View view) {
              //  Intent intent = new Intent(MainActivity.this, SecondActivity.class);
               // startActivity(intent);
                //Log.d("TAG", "Start SecondActivity");
            //}



        //});

    }
}


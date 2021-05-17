package com.example.assignment5project;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.StyleableRes;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ListviewActivity extends AppCompatActivity {

    private ArrayList<Wonders> items;
    private ArrayAdapter<Wonders> adapter;
   /* private Wonders[] wonder; */
   private WebView mySecondWebView;
    private SharedPreferences myPreferenceRef;
    private SharedPreferences.Editor myPreferenceEditor;
    private TextView textView;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        myPreferenceRef = getPreferences(MODE_PRIVATE);
        myPreferenceEditor = myPreferenceRef.edit();

        new JsonTask().execute("https://wwwlab.iit.his.se/brom/kurser/mobilprog/dbservice/admin/getdataasjson.php?type=a20samul");

        items = new ArrayList<>();
        adapter = new ArrayAdapter<Wonders>(ListviewActivity.this, R.layout.listview2, R.id.item, items);
        textView = findViewById(R.id.recentlyViewed);
        textView.setText(myPreferenceRef.getString("recentlyVisited", "No history found."));

        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Wonders wonder = items.get(position);

            mySecondWebView = findViewById(R.id.webView2);
            WebViewClient myWebViewClient = new WebViewClient();
            mySecondWebView.setWebViewClient(myWebViewClient);

            WebSettings webSettings = mySecondWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);

            mySecondWebView.loadUrl(wonder.getAuxdata());
            /*ImageView imageView = findViewById(R.id.imageView);
            Picasso.get().load(wonder.getAuxdata()).into(imageView);*/

            String message = "The wonder " +  wonder.getName() + " is a " + wonder.getCategory() +
                    ". It is located in " + wonder.getLocation() + " and was built " + wonder.getCompany();

           /*Snackbar snackbar = Snackbar.make(findViewById(R.id.CL), message, Snackbar.LENGTH_LONG);
           snackbar.show();*/

            Snackbar snackbar =  Snackbar.make(view, message,Snackbar.LENGTH_LONG).setDuration(7000);
            View snackbarView = snackbar.getView();
            TextView tv = snackbarView.findViewById(R.id.snackbar_text);
            tv.setMaxLines(3);
            snackbar.show();

            textView.setText(wonder.getName());
            // Store the new preference
            myPreferenceEditor.putString("recentlyVisited", wonder.getName());
            myPreferenceEditor.apply();
            //Toast.makeText(ListviewActivity.this, message, Toast.LENGTH_LONG).show();

        });
    }



    @SuppressLint("StaticFieldLeak")
    private class JsonTask extends AsyncTask<String, String, String> {

        private HttpURLConnection connection = null;
        private BufferedReader reader = null;

        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null && !isCancelled()) {
                    builder.append(line).append("\n");
                }
                return builder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String json) {
            try {
                Log.d("AsyncTask", json);
                Gson gson = new Gson();
                Wonders[] wonder = gson.fromJson(json, Wonders[].class);
                adapter.clear();
                for (int i = 0; i < wonder.length; i++) {
                    Log.d("ListviewActivity ==>", "Found a wonder: " + wonder[i]);
                    adapter.add(wonder[i]);
                }
                adapter.notifyDataSetChanged();
            } catch (Exception e) {
                Log.e("ListviewActivity ==>", "Something went wrong.");
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dropdown_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.home_page:
                Intent intent1 = new Intent(ListviewActivity.this, MainActivity.class);
                startActivity(intent1);
                Log.d("HOME", "Successfully launched home page");
                return true;
            case R.id.about_page:
                Intent intent = new Intent(ListviewActivity.this, About.class);
                startActivity(intent);
                Log.d("ABOUT", "About page");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
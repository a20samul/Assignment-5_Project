package com.example.assignment5project;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        new JsonTask().execute("https://wwwlab.iit.his.se/brom/kurser/mobilprog/dbservice/admin/getdataasjson.php?type=a20samul");

        items = new ArrayList<>();
        adapter = new ArrayAdapter<Wonders>(ListviewActivity.this, R.layout.listview2, R.id.item, items);

        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {

            Wonders wonder = items.get(position);

            String message = "The wonder " +  wonder.getName() + " is a " + wonder.getCategory() +
                    ". It is located in " + wonder.getLocation() + " and was built " + wonder.getCompany();
            Toast.makeText(ListviewActivity.this, message, Toast.LENGTH_LONG).show();
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
}
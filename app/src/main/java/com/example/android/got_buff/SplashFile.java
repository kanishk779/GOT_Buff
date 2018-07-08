package com.example.android.got_buff;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.got_buff.ModelClasses.CharacterModelClass.Data;
import com.example.android.got_buff.ModelClasses.CitiesModelClass.AllCity;
import com.example.android.got_buff.ModelClasses.EpisodeModelClass.AllEpisode;
import com.example.android.got_buff.ModelClasses.EventModelClass.AllEvent;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SplashFile extends AppCompatActivity {
    ArrayList<AllCity> cityList;
    ArrayList<AllEpisode> episodeList;
    ArrayList<AllEvent> eventList;
    Button start;
    private static final String BASE_URL = "https://api.got.show/api/";
    private static final String CITIES_URL =BASE_URL+"cities/";
    private static final String EPISODES_URL = BASE_URL+"episodes/";
    private static final String EVENTS_URL = BASE_URL+"events/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_file);
        cityList = new ArrayList<>();
        episodeList = new ArrayList<>();
        eventList = new ArrayList<>();
        start = findViewById(R.id.startApp);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    public class AsyncCaller extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            StringRequest sr = new StringRequest(Request.Method.GET, CITIES_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        Gson g = new Gson();
                        JSONArray arr = new JSONArray(response);
                        for (int i = 0; i < arr.length(); i++) {
                            JSONObject joo1 = arr.getJSONObject(i);
                            AllCity tc = g.fromJson(joo1.toString(), AllCity.class);
                            cityList.add(tc);
                        }
                    }
                    catch (Exception e) {
                        Toast.makeText(SplashFile.this, "Deep" + e, Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(SplashFile.this, "" + error, Toast.LENGTH_SHORT).show();
                }
            });
            Volley.newRequestQueue(SplashFile.this).add(sr);
            StringRequest sr1 = new StringRequest(Request.Method.GET, EPISODES_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        Gson g = new Gson();
                        JSONArray arr = new JSONArray(response);
                        for (int i = 0; i < arr.length(); i++) {
                            JSONObject joo1 = arr.getJSONObject(i);
                            AllEpisode tc = g.fromJson(joo1.toString(), AllEpisode.class);
                            episodeList.add(tc);
                        }
                    }
                    catch (Exception e) {
                        Toast.makeText(SplashFile.this, "Deep" + e, Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(SplashFile.this, "" + error, Toast.LENGTH_SHORT).show();
                }
            });
            Volley.newRequestQueue(SplashFile.this).add(sr1);
            StringRequest sr2 = new StringRequest(Request.Method.GET, EVENTS_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        Gson g = new Gson();
                        JSONArray arr = new JSONArray(response);
                        for (int i = 0; i < arr.length(); i++) {
                            JSONObject joo1 = arr.getJSONObject(i);
                            AllEvent tc = g.fromJson(joo1.toString(), AllEvent.class);
                            eventList.add(tc);
                        }
                    }
                    catch (Exception e) {
                        Toast.makeText(SplashFile.this, "Deep" + e, Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(SplashFile.this, "" + error, Toast.LENGTH_SHORT).show();
                }
            });
            Volley.newRequestQueue(SplashFile.this).add(sr2);
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            Toast.makeText(SplashFile.this, ""+cityList, Toast.LENGTH_LONG).show();
            Toast.makeText(SplashFile.this, ""+eventList, Toast.LENGTH_LONG).show();
            Toast.makeText(SplashFile.this, ""+episodeList, Toast.LENGTH_LONG).show();
            Intent in = new Intent(SplashFile.this,MainActivity.class);
            in.putExtra("cities",cityList);
            in.putExtra("events",eventList);
            in.putExtra("episodes",episodeList);
            startActivity(in);
        }
    }
}

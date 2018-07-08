package com.example.android.got_buff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.got_buff.DataBases.CharacterHistoryDatabase;
import com.example.android.got_buff.ModelClasses.CharacterModelClass.Data;
import com.example.android.got_buff.ModelClasses.EventModelClass.AllEvent;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

public class SearchActivity extends AppCompatActivity {
    EditText charactername;
    Button SeeResult;
    Data character;
    CharacterHistoryDatabase db;
    private static final String BASE_URL = "https://api.got.show/characters/";
    private static String NAME ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        SeeResult = findViewById(R.id.result);
        charactername = findViewById(R.id.charactername);
        SeeResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValid())
                {
                    String URL = BASE_URL + NAME;
                    StringRequest sr2 = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                character = new Data();
                                Gson g = new Gson();
                                JSONObject jo = new JSONObject(response);
                                JSONObject jo1 = jo.getJSONObject("body");
                                character = g.fromJson(jo1.toString(),Data.class);
                            }
                            catch (Exception e) {
                                Toast.makeText(SearchActivity.this, "Deep" + e, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(SearchActivity.this, "" + error, Toast.LENGTH_SHORT).show();
                        }
                    });
                    Volley.newRequestQueue(SearchActivity.this).add(sr2);
                    //CODE FOR INSERTING INTO DATABASE
                    //CODE FOR SENDING THIS DATA TO DETAIL ACTIVITY
                    db.openWrite();
                    db.insert(character);
                    db.close();
                    Intent in = new Intent(SearchActivity.this,CharacterDetail.class);
                    in.putExtra("character",character);
                    startActivity(in);
                }
            }
        });
    }

    private boolean isValid() {
        if(TextUtils.isEmpty(charactername.getText().toString()))
            return false;
        NAME = charactername.getText().toString();
        return true;
    }

}

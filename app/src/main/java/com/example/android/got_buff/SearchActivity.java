package com.example.android.got_buff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
        db = new CharacterHistoryDatabase(this);
        SeeResult = findViewById(R.id.result);
        charactername = findViewById(R.id.charactername);
        SeeResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValid())
                {
                    Intent in = new Intent(SearchActivity.this,CharacterDetail.class);
                    in.putExtra("name",NAME);
                    startActivity(in);
                    finish();
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

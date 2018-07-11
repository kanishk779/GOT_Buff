package com.example.android.got_buff;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.got_buff.ModelClasses.CharacterModelClass.Data;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by hp on 11-07-2018.
 */

public class ShowDetailFromDatabase extends AppCompatActivity {
    private static final String BASE_URL = "https://api.got.show/";
    TextView name,slug,dateofbirth,culture,books;
    ImageView poster;
    Data character;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_character);
        initViews();
        try{
            character = (Data) getIntent().getExtras().getSerializable("character");
        }
        catch (Exception e){
            Toast.makeText(this, ""+e.toString(), Toast.LENGTH_SHORT).show();
        }
        if(character!=null)
        {
            name.setText(character.getName());
            slug.setText(character.getSlug());
            dateofbirth.setText(String.valueOf(character.getDateOfBirth()));
            culture.setText(character.getCulture());
            StringBuilder sb = new StringBuilder();
            ArrayList<String> booklist= (ArrayList<String>) character.getBooks();
            for(int i=0;i<booklist.size();i++)
            {
                sb.append(booklist.get(i));
                sb.append("\n");
            }
            books.setText(sb.toString());
            Picasso.with(this).load(BASE_URL+character.getImageLink()).into(poster);
        }
    }
    private void initViews() {
        poster = findViewById(R.id.detailcharacterimage);
        name = findViewById(R.id.detailcharactername);
        slug = findViewById(R.id.detailcharacterslug);
        dateofbirth = findViewById(R.id.detailcharacterdob);
        culture = findViewById(R.id.detailcharacterculture);
        books = findViewById(R.id.detailcharacterBooks);
    }
}

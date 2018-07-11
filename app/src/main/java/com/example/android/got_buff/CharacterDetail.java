package com.example.android.got_buff;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.got_buff.DataBases.CharacterHistoryDatabase;
import com.example.android.got_buff.ModelClasses.CharacterModelClass.Data;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by hp on 03-07-2018.
 */

public class CharacterDetail extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Data>{
    TextView name,slug,dateofbirth,culture,books;
    ImageView poster;
    TextView mEmptyStateTextView;
    ArrayList<Data> list;
    private CharacterHistoryDatabase db;
    private String NAME="";
    private static final String BASE_URL1 = "https://api.got.show/api/characters/";
    private static final String BASE_URL = "https://api.got.show/";
    private static final int CHARACTER_LOADER_ID = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load_detail_character);
        mEmptyStateTextView =  findViewById(R.id.empty_view);
        db = new CharacterHistoryDatabase(this);
        try{
            NAME = getIntent().getExtras().getString("name");
        }
        catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
        initViews();
        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(CHARACTER_LOADER_ID, null,  this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText("No Internet Connection");
        }
    }

    private void initViews() {
        poster = findViewById(R.id.loaddetailcharacterimage);
        name = findViewById(R.id.loaddetailcharactername);
        slug = findViewById(R.id.loaddetailcharacterslug);
        dateofbirth = findViewById(R.id.loaddetailcharacterdob);
        culture = findViewById(R.id.loaddetailcharacterculture);
        books = findViewById(R.id.loaddetailcharacterBooks);
    }

    @Override
    public Loader<Data> onCreateLoader(int id, Bundle args) {
        String URL = BASE_URL1 + NAME;
        return new CharacterLoader(this,URL);
    }

    @Override
    public void onLoadFinished(Loader<Data> loader, Data character) {
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        // Set empty state text to display "No earthquakes found."
        //mEmptyStateTextView.setText("No News Available");
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
            Boolean cond = true;
            db.openRead();
            list = db.readAll();
            db.closeRead();
            for(int i=0;i<list.size();i++)
            {
                if(list.get(i).getName().equalsIgnoreCase(character.getName()))
                {
                    cond = false;
                    break;
                }
            }
            if(cond)
            {
                db.openWrite();
                long i =db.insert(character);
                db.closeWrite();
                if(i>0){
                    Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    @Override
    public void onLoaderReset(Loader<Data> loader) {

    }
}

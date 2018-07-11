package com.example.android.got_buff;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.example.android.got_buff.ModelClasses.CharacterModelClass.Data;

/**
 * Created by hp on 10-07-2018.
 */

public class CharacterLoader extends AsyncTaskLoader<Data> {
    Data character;
    String mUrl;
    public CharacterLoader(Context context,String url)
    {
        super(context);
        mUrl = url;
    }
    @Override
    public Data loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        character = QueryUtils.fetchNewsData(mUrl);
        return character;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}

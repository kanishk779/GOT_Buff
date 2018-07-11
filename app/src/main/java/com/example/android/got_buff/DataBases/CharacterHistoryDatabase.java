package com.example.android.got_buff.DataBases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.got_buff.ModelClasses.CharacterModelClass.Data;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by hp on 02-07-2018.
 */

public class CharacterHistoryDatabase extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "favourite_characters";
    private static final String TABLE_NAME = "characters";
    private static final String NAME = "name";
    private static final String SlUG = "slug";
    private static final String CULTURE = "culture";
    private static final String BIRTHDATE = "dob";
    private static final String BOOKS = "books";
    private static final String IMAGE = "image";
    private final static int VERSION = 1;
    SQLiteDatabase sdW, sdR;
    private final static String CREATE_TABLE = "create table "+TABLE_NAME+"("+NAME+" varchar(20),"+SlUG+" varchar(20),"+CULTURE+" varchar(20),"+BIRTHDATE+" varchar(20),"+BOOKS+" varchar(40),"+IMAGE+" varchar(40));";
    public CharacterHistoryDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    public void openWrite() {
        sdW = getWritableDatabase();
    }

    public void closeWrite() {
        sdW.close();
    }

    public void openRead() {
        sdR = getWritableDatabase();
    }

    public void closeRead() {
        sdR.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME + "");
        this.onCreate(db);
    }
    public long insert(Data character) {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> books = (ArrayList<String>) character.getBooks();
        for(int i=0;i<books.size();i++)
        {
            sb.append(books.get(i));
            sb.append("\n");
        }
        ContentValues cv = new ContentValues();
        cv.put(NAME, character.getName());
        cv.put(SlUG, character.getSlug());
        cv.put(CULTURE,character.getCulture());
        cv.put(BIRTHDATE, String.valueOf(character.getDateOfBirth()));
        cv.put(BOOKS, String.valueOf(sb));
        cv.put(IMAGE, character.getImageLink());
        long i = sdW.insert(TABLE_NAME, null, cv);
        return i;
    }
    public Data read(String Character) {
        sdR = getReadableDatabase();
        Data t = new Data();
        Cursor c = sdR.query(TABLE_NAME, null, "" + NAME + "=? ", new String[]{Character}, null, null, null);
        if (c != null) {
            c.moveToNext();
            t.setName(c.getString(0));
            t.setSlug(c.getString(1));
            t.setCulture(c.getString(2));
            t.setDateOfBirth(c.getLong(3));
            String s = c.getString(4);
            ArrayList<String> myList = new ArrayList<>(Arrays.asList(s.split("\n")));
            t.setBooks(myList);
            t.setImageLink(c.getString(5));
        }
        return t;
    }
    public ArrayList<Data> readAll() {
        sdR = getReadableDatabase();
        ArrayList<Data> list = new ArrayList<>();
        Cursor c = sdR.query(TABLE_NAME, null, null, null, null, null, null);
        while (c.moveToNext()) {
            Data t = new Data();
            t.setName(c.getString(0));
            t.setSlug(c.getString(1));
            t.setCulture(c.getString(2));
            t.setDateOfBirth(c.getLong(3));
            String s = c.getString(4);
            ArrayList<String> myList = new ArrayList<>(Arrays.asList(s.split("\n")));
            t.setBooks(myList);
            t.setImageLink(c.getString(5));
            list.add(t);
            c.moveToNext();
        }
        return list;
    }
}

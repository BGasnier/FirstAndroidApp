package com.example.brice.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Brice on 02/04/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String WORDS_TABLE_NAME = "words";
    public static final String WORDS_COLUMN_ESTONIAN = "estonian";
    public static final String WORDS_COLUMN_FRENCH = "french";
    public static final String WORDS_COLUMN_ENGLISH = "english";

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table words " +
                        "(id integer primary key, estonian text,french text,english text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS words");
        onCreate(db);
    }

    public boolean insertWord  (String estonian, String french, String english) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("estonian", estonian);
        contentValues.put("french", french);
        contentValues.put("english", english);
        db.insert("words", null, contentValues);
        return true;
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from words where id=" + id + "", null);
        return res;
    }

    public Cursor getRandom(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from words order by random() limit 1", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, WORDS_TABLE_NAME);
        return numRows;
    }

    public Integer deleteContact (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("words",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllWords()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from words", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(WORDS_COLUMN_ESTONIAN)));
            res.moveToNext();
        }
        return array_list;
    }
}

package com.example.moviefav;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.moviefav.DatabaseContract.NoteColumns.DATE;
import static com.example.moviefav.DatabaseContract.NoteColumns.DESC;
import static com.example.moviefav.DatabaseContract.NoteColumns.POSTER;
import static com.example.moviefav.DatabaseContract.NoteColumns.RATE;
import static com.example.moviefav.DatabaseContract.NoteColumns.TABLE_NAME;
import static com.example.moviefav.DatabaseContract.NoteColumns.TITLE;


public class NoteHelper {
    private static DatabaseHelper helper;
    private static SQLiteDatabase database;
    private static final String DATABASE_TABLE = TABLE_NAME;



    public NoteHelper(Context context) {
        helper = new DatabaseHelper(context);
        open();
    }

    public void open() throws SQLException {
        database = helper.getWritableDatabase();
    }

    public long insertNote(Content content) {
            ContentValues args = new ContentValues();
            args.put(_ID,content.getId());
            args.put(TITLE, content.getTitleContent());
            args.put(DESC, content.getDescContent());
            args.put(DATE, content.getDateContent());
            args.put(RATE, content.getRateContet());
            args.put(POSTER, content.getPosterContent());

            database.insert(TABLE_NAME, null, args);


        return 0;
    }

        public void close () {
            helper.close();
            if (database.isOpen()) database.close();
        }
        public ArrayList<Content> getAllContents () {
            ArrayList<Content> arrayList = new ArrayList<>();
            Cursor cursor = database.query(DATABASE_TABLE, null,
                    null,
                    null,
                    null,
                    null,
                    _ID + " ASC",
                    null);
            cursor.moveToFirst();
            Content content;
            if (cursor.getCount() > 0) {
                do {
                    content = new Content();
                    content.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                    content.setTitleContent(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                    content.setDescContent(cursor.getString(cursor.getColumnIndexOrThrow(DESC)));
                    content.setDateContent(cursor.getString(cursor.getColumnIndexOrThrow(DATE)));
                    content.setRateContet(cursor.getInt(cursor.getColumnIndexOrThrow(RATE)));
                    content.setPosterContent(cursor.getString(cursor.getColumnIndexOrThrow(POSTER)));
                    arrayList.add(content);
                    cursor.moveToNext();

                } while (!cursor.isAfterLast());
            }
            cursor.close();
            return arrayList;
        }
        public int deleteNote ( int id){
            return database.delete(TABLE_NAME, _ID + " = '" + id + "'", null);
        }

    public Cursor queryByIdProvider(String id){
        return database.query(DATABASE_TABLE,null
                ,_ID + " = ?"
                ,new String[]{id}
                ,null
                ,null
                ,null
                ,null);
    }
    public Cursor queryProvider(){
        return database.query(DATABASE_TABLE
                ,null
                ,null
                ,null
                ,null
                ,null
                ,_ID + " DESC");
    }
    public long insertProvider(ContentValues values){
        return database.insert(DATABASE_TABLE,null,values);
    }
    public int updateProvider(String id,ContentValues values){
        return database.update(DATABASE_TABLE,values,_ID +" = ?",new String[]{id} );
    }
    public int deleteProvider(String id){
        return database.delete(DATABASE_TABLE,_ID + " = ?", new String[]{id});
    }




}
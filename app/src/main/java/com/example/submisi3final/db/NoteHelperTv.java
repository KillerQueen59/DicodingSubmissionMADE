package com.example.submisi3final.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.submisi3final.model.ContentTv;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.submisi3final.db.DatabaseContractTv.NoteColumns.DATE;
import static com.example.submisi3final.db.DatabaseContractTv.NoteColumns.DESC;
import static com.example.submisi3final.db.DatabaseContractTv.NoteColumns.POSTER;
import static com.example.submisi3final.db.DatabaseContractTv.NoteColumns.RATE;
import static com.example.submisi3final.db.DatabaseContractTv.NoteColumns.TABLE_NAME_TV;
import static com.example.submisi3final.db.DatabaseContractTv.NoteColumns.TITLE;

public class NoteHelperTv {
    private static DatabaseHelperTv helper;
    private static SQLiteDatabase database;
    private static final String DATABASE_TABLE = TABLE_NAME_TV;


    public NoteHelperTv(Context context) {
        helper = new DatabaseHelperTv(context);
        open();
    }

    public void open() throws SQLException {
        database = helper.getWritableDatabase();
    }

    public void insertNoteTv(ContentTv content) {

            ContentValues args = new ContentValues();
            args.put(_ID,content.getId());
            args.put(TITLE, content.getTitleContent());
            args.put(DESC, content.getDescContent());
            args.put(DATE, content.getDateContent());
            args.put(RATE, content.getRateContet());
            args.put(POSTER, content.getPosterContent());

            database.insert(TABLE_NAME_TV, null, args);
    }
    public int deleteNote(int id) {
        return database.delete(TABLE_NAME_TV, _ID + " = '" + id + "'", null);
    }

    public void close() {
        helper.close();
        if (database.isOpen())database.close();
    }
    public ArrayList<ContentTv> getAllContentsTv() {
        ArrayList<ContentTv> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE, null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();
        ContentTv content;
        if (cursor.getCount() > 0) {
            do {
                content = new ContentTv();
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
}

package com.example.submisi3final.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.submisi3final.db.DatabaseContract.NoteColumns.TABLE_NAME;
import static com.example.submisi3final.db.DatabaseContractTv.NoteColumns.TABLE_NAME_TV;

public class DatabaseHelperTv extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "dbContentTV";
    private static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_TABLE_NOTE = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            TABLE_NAME_TV,
            DatabaseContractTv.NoteColumns._ID,
            DatabaseContractTv.NoteColumns.TITLE,
            DatabaseContractTv.NoteColumns.DESC,
            DatabaseContractTv.NoteColumns.DATE,
            DatabaseContractTv.NoteColumns.RATE,
            DatabaseContractTv.NoteColumns.POSTER
    );


    DatabaseHelperTv(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_NOTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
}


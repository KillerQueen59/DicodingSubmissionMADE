package com.example.submisi3final.db;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

import static com.example.submisi3final.db.DatabaseContract.NoteColumns.TABLE_NAME;

public class DatabaseContract {


    public static final class NoteColumns implements BaseColumns{
        public static final String TABLE_NAME = "content";

        public static final String TITLE = "title";
        public static final String DATE = "date";
        public static final String DESC = "desc";
        public static final String RATE = "rate";
        public static final String POSTER = "poster";
    }
    public static final String AUTHORITY = "com.example.hopelessway";
    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(TABLE_NAME)
            .build();
    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString( cursor.getColumnIndex(columnName) );
    }
    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt( cursor.getColumnIndex(columnName) );
    }
}

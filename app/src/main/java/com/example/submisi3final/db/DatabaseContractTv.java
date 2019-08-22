package com.example.submisi3final.db;

import android.provider.BaseColumns;

public class DatabaseContractTv {
    static final class NoteColumns implements BaseColumns {
        static final String TABLE_NAME_TV = "contentTV";

        static final String TITLE = "title";
        static final String DATE = "date";
        static final String DESC = "desc";
        static final String RATE = "rate";
        static final String POSTER = "poster";

    }
}

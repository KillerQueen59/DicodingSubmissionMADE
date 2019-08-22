package com.example.submisi3final.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.submisi3final.db.DatabaseContract;

import org.json.JSONObject;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.submisi3final.db.DatabaseContract.getColumnInt;
import static com.example.submisi3final.db.DatabaseContract.getColumnString;

public class Content implements Parcelable {
    String titleContent;
    String descContent;
    String dateContent;
    String posterContent;
    int rateContet;
    int id;

    public Content(Cursor cursor) {
        this.id = getColumnInt(cursor, _ID);
        this.titleContent = getColumnString(cursor, DatabaseContract.NoteColumns.TITLE);
        this.descContent = getColumnString(cursor, DatabaseContract.NoteColumns.DESC);
        this.dateContent = getColumnString(cursor, DatabaseContract.NoteColumns.DATE);
        this.rateContet = getColumnInt(cursor, DatabaseContract.NoteColumns.RATE);
        this.posterContent = getColumnString(cursor, DatabaseContract.NoteColumns.POSTER);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Content(JSONObject object){
        try {
            int id = object.getInt("id");
            String title = object.getString("title");
            String date = object.getString("release_date");
            String desc = object.getString("overview");
            String poster = "https://image.tmdb.org/t/p/original/" + object.getString("poster_path");
            int rate = object.getInt("vote_average");
            this.titleContent = title;
            this.dateContent = date;
            this.descContent = desc;
            this.posterContent = poster;
            this.rateContet = rate;
            this.id = id;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected Content(Parcel in) {
        titleContent = in.readString();
        descContent = in.readString();
        dateContent = in.readString();
        posterContent = in.readString();
        rateContet = in.readInt();
        id = in.readInt();
    }

    public static final Creator<Content> CREATOR = new Creator<Content>() {
        @Override
        public Content createFromParcel(Parcel in) {
            return new Content(in);
        }

        @Override
        public Content[] newArray(int size) {
            return new Content[size];
        }
    };

    public Content() {

    }

    public String getTitleContent() {
        return titleContent;
    }

    public void setTitleContent(String titleContent) {
        this.titleContent = titleContent;
    }

    public String getDescContent() {
        return descContent;
    }

    public void setDescContent(String descContent) {
        this.descContent = descContent;
    }

    public String getDateContent() {
        return dateContent;
    }

    public void setDateContent(String dateContent) {
        this.dateContent = dateContent;
    }

    public String getPosterContent() {
        return posterContent;
    }

    public void setPosterContent(String posterContent) {
        this.posterContent = posterContent;
    }

    public int getRateContet() {
        return rateContet;
    }

    public void setRateContet(int rateContet) {
        this.rateContet = rateContet;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(titleContent);
        parcel.writeString(descContent);
        parcel.writeString(dateContent);
        parcel.writeString(posterContent);
        parcel.writeInt(rateContet);
        parcel.writeInt(id);
    }
}

package com.example.submisi3final.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class ContentTv implements Parcelable {
    String titleContent;
    String descContent;
    String dateContent;
    String posterContent;
    int rateContet;
    int id;

    public ContentTv() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ContentTv(JSONObject object){
        try {
            String title = object.getString("name");
            int id = object.getInt("id");
            String date = object.getString("first_air_date");
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

    protected ContentTv(Parcel in) {
        titleContent = in.readString();
        descContent = in.readString();
        dateContent = in.readString();
        posterContent = in.readString();
        rateContet = in.readInt();
        id = in.readInt();
    }

    public static final Parcelable.Creator<ContentTv> CREATOR = new Parcelable.Creator<ContentTv>() {
        @Override
        public ContentTv createFromParcel(Parcel in) {
            return new ContentTv(in);
        }

        @Override
        public ContentTv[] newArray(int size) {
            return new ContentTv[size];
        }
    };

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

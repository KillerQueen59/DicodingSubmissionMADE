package com.example.submisi3final.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ContentFav implements Parcelable {
    private String id;
    private String title;
    private String imagel;

    public ContentFav(String id, String title, String imagel) {
        this.id = id;
        this.title = title;
        this.imagel = imagel;
    }
    public ContentFav(String id) {
        this.id = id;
    }

    protected ContentFav(Parcel in) {
        id = in.readString();
        title = in.readString();
        imagel = in.readString();
    }

    public static final Creator<ContentFav> CREATOR = new Creator<ContentFav>() {
        @Override
        public ContentFav createFromParcel(Parcel in) {
            return new ContentFav(in);
        }

        @Override
        public ContentFav[] newArray(int size) {
            return new ContentFav[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImagel() {
        return imagel;
    }

    public void setImagel(String imagel) {
        this.imagel = imagel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(imagel);
    }
}

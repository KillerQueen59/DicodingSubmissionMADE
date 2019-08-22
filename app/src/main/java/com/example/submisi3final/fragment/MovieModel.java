package com.example.submisi3final.fragment;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.example.submisi3final.model.Content;
import com.example.submisi3final.model.ContentTv;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MovieModel extends ViewModel {
    public static final String API_KEYS = "98110c8462b93a569fa1acb99c5d9596";
    public static final String TAG = "tag";
    private MutableLiveData<ArrayList<Content>> listContents = new MutableLiveData<>();

    void setContent(){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Content> listItem = new ArrayList<>();

        String url = "https://api.themoviedb.org/3/discover/movie?api_key=" + API_KEYS + "&language=en-US";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                try {
                    JSONObject object = new JSONObject(result);
                    JSONArray list = object.getJSONArray("results");
                    for (int i = 0; i < list.length(); i++) {
                        Content content1 = new Content(list.getJSONObject(i));
                        listItem.add(content1);
                    }
                    listContents.postValue(listItem);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG, "onSuccess: " );
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }
    LiveData<ArrayList<Content>> getContent(){
        return listContents;
    }
}

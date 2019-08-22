package com.example.submisi3final.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.submisi3final.R;
import com.example.submisi3final.adapter.MovieAdapter;
import com.example.submisi3final.model.Content;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchAct extends AppCompatActivity {
    private String TAG = "MovieSearch Fragment";

    private EditText searchInputBar;
    private Button searchButton;
    private RecyclerView recyclerView;

    private String resultString;
    private ArrayList<Content> movieList;

    private RequestQueue requestQueue;

    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchInputBar = findViewById(R.id.searchInput);
        searchButton = findViewById(R.id.searchButton);
        recyclerView = findViewById(R.id.rvMovie);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        this.movieList = new ArrayList<>();

        if (savedInstanceState != null) {
            this.movieList = savedInstanceState.getParcelableArrayList("data_saved");
            movieAdapter = new MovieAdapter(this, this.movieList);
            recyclerView.setAdapter(movieAdapter);
        } else {

            searchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resultString = String.valueOf(searchInputBar.getText());
                    movieList = new ArrayList<>();
                    movieAdapter = new MovieAdapter(getApplicationContext(), movieList);
                    recyclerView.setAdapter(movieAdapter);

                    GetMovieTask getDataAsync = new GetMovieTask();
                    getDataAsync.execute(resultString);
                }
            });

            requestQueue = Volley.newRequestQueue(this);
        }
    }


    public void getData(String result){
        String url = "https://api.themoviedb.org/3/search/movie?api_key=98110c8462b93a569fa1acb99c5d9596&language=en-US&query=";
        url = url+result;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,  null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("results");

                    for (int i = 0 ; i<jsonArray.length();i++){
                        JSONObject data = jsonArray.getJSONObject(i);

                        Content content = new Content();

                        content.setId(data.getInt("id"));
                        content.setTitleContent(data.getString("title"));
                        content.setDateContent(data.getString("release_date"));
                        content.setRateContet((int) data.getDouble("vote_average"));
                        content.setTitleContent(data.getString("title"));
                        content.setDescContent(data.getString("overview"));
                        content.setPosterContent(data.getString("poster_path"));


                        movieList.add(content);
                    }
                    movieAdapter = new MovieAdapter(getApplicationContext(), movieList);
                    recyclerView.setAdapter(movieAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(request);
    }

    public class GetMovieTask extends AsyncTask<String,Void,Void> {

        @Override
        protected Void doInBackground(String... strings) {
            getData(strings[0]);
            return null;
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList("data_saved", this.movieList);
    }

}

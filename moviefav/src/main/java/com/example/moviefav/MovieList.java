package com.example.moviefav;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.google.android.material.snackbar.Snackbar;

import static com.example.moviefav.DatabaseContract.CONTENT_URI;


public class MovieList extends Fragment {
    private String TAG = "MovieList Fragment";
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private MovieAdapter movieAdapter;
    private Cursor list;
    private Cursor movieList;

    public MovieList() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_movie_list, container, false);
        recyclerView = view.findViewById(R.id.rvMovie);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        movieAdapter = new MovieAdapter(getActivity(), list);
        movieAdapter.setListMovies(list);
        recyclerView.setAdapter(movieAdapter);

        new LoadNoteAsync().execute();


        return view;

    }


    private class LoadNoteAsync extends AsyncTask<Void, Void, Cursor> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            return getContext().getContentResolver().query(CONTENT_URI,null,null,null,null);
        }

        @Override
        protected void onPostExecute(Cursor  movieItems) {
            super.onPostExecute(movieItems);

            list = movieItems;


            movieAdapter = new MovieAdapter(getActivity(), list);
            movieAdapter.setListMovies(list);
            recyclerView.setAdapter(movieAdapter);

        }
    }

    private void showSnackbarMessage(String message){
        Snackbar.make(recyclerView, message, Snackbar.LENGTH_SHORT).show();
    }

}

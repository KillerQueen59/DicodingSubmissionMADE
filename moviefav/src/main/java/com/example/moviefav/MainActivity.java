package com.example.moviefav;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        MovieList mMovieList = new MovieList();

        Bundle data = new Bundle();
        data.putString("chosen_tab", "favourite");
        mMovieList.setArguments(data);

        mFragmentTransaction.replace(R.id.frame_container, mMovieList, MovieList.class.getSimpleName());
        mFragmentTransaction.commit();
    }
}

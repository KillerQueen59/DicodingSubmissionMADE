package com.example.submisi3final.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.submisi3final.R;
import com.example.submisi3final.adapter.ViewPagerAdapter;
import com.example.submisi3final.fragment.MovieFragment;
import com.example.submisi3final.fragment.MovieFragmentFav;
import com.example.submisi3final.fragment.TvShowFragment;
import com.example.submisi3final.fragment.TvShowFragmentFav;
import com.google.android.material.tabs.TabLayout;

public class FavouriteActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        tabLayout = findViewById(R.id.tabLayout2);
        viewPager = findViewById(R.id.ViewPager2);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());


        adapter.AddFragment(new MovieFragmentFav(),"");
        adapter.AddFragment(new TvShowFragmentFav(),"");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_movie_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_tv_black_24dp);

    }

}

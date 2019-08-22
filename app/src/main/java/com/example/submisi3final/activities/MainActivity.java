package com.example.submisi3final.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import com.example.submisi3final.R;
import com.example.submisi3final.adapter.ViewPagerAdapter;
import com.example.submisi3final.fragment.MovieFragment;
import com.example.submisi3final.fragment.TvShowFragment;
import com.example.submisi3final.utils.SettingPrefAct;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.ViewPager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.AddFragment(new MovieFragment(),"");
        adapter.AddFragment(new TvShowFragment(),"");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_movie_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_tv_black_24dp);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu ,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.action_change_settings){
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS );
            startActivity(intent);
        }else if (item.getItemId()==R.id.action_fav){
            Intent intent = new Intent(MainActivity.this,FavouriteActivity.class);
            startActivity(intent);
        }else if (item.getItemId()==R.id.action_set){
            Intent mIntent = new Intent(this, SettingPrefAct.class);
            startActivity(mIntent);
        }else if (item.getItemId()==R.id.action_search){
            doSearch();
        }
        return super.onOptionsItemSelected(item);

    }
    public void doSearch(){
//        Log.d(TAG, "Search");
        Intent intent = new Intent(this,SearchAct.class);
        startActivity(intent);
    }
}

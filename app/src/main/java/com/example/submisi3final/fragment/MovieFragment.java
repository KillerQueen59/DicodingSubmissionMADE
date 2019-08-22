package com.example.submisi3final.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.submisi3final.R;
import com.example.submisi3final.adapter.MovieAdapter;
import com.example.submisi3final.model.Content;

import java.util.ArrayList;

public class MovieFragment extends Fragment {

    private MovieAdapter adapter;
    private MovieModel movieModel;
    private ArrayList<Content> c = new ArrayList<>();
    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);

        movieModel = ViewModelProviders.of(getActivity()).get(MovieModel.class);
        movieModel.getContent().observe(getActivity(),getContent);
        movieModel.setContent();
        showProgressBar();
        initRecyclerView();

        return view;
    }
    private Observer<ArrayList<Content>> getContent = new Observer<ArrayList<Content>>() {
        @Override
        public void onChanged(ArrayList<Content> contents) {
            if (contents != null){
                adapter.setData(contents);
                hidProgressBar();
            }
        }
    };

    private void initRecyclerView() {
        adapter = new MovieAdapter(getActivity(),c);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    private void showProgressBar(){
        progressBar.setVisibility(View.VISIBLE);
    }
    private void hidProgressBar(){
        progressBar.setVisibility(View.GONE );
    }

}

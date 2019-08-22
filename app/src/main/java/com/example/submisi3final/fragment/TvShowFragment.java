package com.example.submisi3final.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.submisi3final.R;
import com.example.submisi3final.adapter.MovieAdapter;
import com.example.submisi3final.adapter.TvShowAdapter;
import com.example.submisi3final.model.Content;
import com.example.submisi3final.model.ContentTv;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {
    private TvShowAdapter adapter;
    private TvModel tvModel;
    private ArrayList<ContentTv> c = new ArrayList<>();
    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    public TvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_show, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);

        tvModel = ViewModelProviders.of(getActivity()).get(TvModel.class);
        tvModel.getContent().observe(this,getContent);
        tvModel.setContent();
        showProgressBar();
        initRecyclerView();

        return view;
    }

    private Observer<ArrayList<ContentTv>> getContent = new Observer<ArrayList<ContentTv>>() {
        @Override
        public void onChanged(ArrayList<ContentTv> contents) {
            if (contents != null){
                adapter.setData(contents);
                hidProgressBar();
            }
        }
    };
    private void initRecyclerView() {
        adapter = new TvShowAdapter(getActivity());
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

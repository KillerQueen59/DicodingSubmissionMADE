package com.example.submisi3final.fragment;


import android.content.Intent;
import android.icu.text.Transliterator;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.submisi3final.R;
import com.example.submisi3final.activities.LoadDataNotes;
import com.example.submisi3final.activities.ShowDetails;
import com.example.submisi3final.adapter.MovieAdapter;
import com.example.submisi3final.model.Content;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import com.example.submisi3final.db.NoteHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragmentFav extends Fragment implements LoadDataNotes{
    private RecyclerView rvNotes;
    private static final String EXTRA_STATE = "EXTRA_STATE";
    private MovieAdapter adapter;
    private NoteHelper noteHelper;
    private View view;
    private Content content;
    private ArrayList<Content> contents = new ArrayList<>();
    private final int ALERT_DIALOG_CLOSE = 10;
    private final int ALERT_DIALOG_DELETE = 20;
    private Transliterator.Position position;


    public MovieFragmentFav() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_movie_fragment_fav, container, false);


        rvNotes = view.findViewById(R.id.recyclerView);
        rvNotes.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvNotes.setHasFixedSize(true);

        noteHelper = new NoteHelper(getActivity().getApplicationContext());

        noteHelper.open();


        adapter = new MovieAdapter(getActivity(),contents);
        rvNotes.setAdapter(adapter);
        if (savedInstanceState == null) {
            new LoadNotesAsync(noteHelper, this).execute();
        } else {
            ArrayList<Content> list = savedInstanceState.getParcelableArrayList(EXTRA_STATE);
            if (list != null) {
                adapter.setListNotes(list);
            }
        }



        return view;
    }

    @Override
    public void preExecute() {

    }

    @Override
    public void postExecute(ArrayList<Content> notes) {
        adapter.setListNotes(notes);
    }

    private static class LoadNotesAsync extends AsyncTask<Void, Void, ArrayList<Content>> {

        private final WeakReference<NoteHelper> weakNoteHelper;
        private final WeakReference<LoadDataNotes> weakCallback;

        private LoadNotesAsync(NoteHelper noteHelper, LoadDataNotes callback) {
            weakNoteHelper = new WeakReference<>(noteHelper);
            weakCallback = new WeakReference<>(callback);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            weakCallback.get().preExecute();
        }

        @Override
        protected ArrayList<Content> doInBackground(Void... voids) {

            return weakNoteHelper.get().getAllContents();
        }

        @Override
        protected void onPostExecute(ArrayList<Content> notes) {
            super.onPostExecute(notes);

            weakCallback.get().postExecute(notes);

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            // Akan dipanggil jika request codenya ADD
            if (requestCode == ShowDetails.REQUEST_ADD) {
                if (resultCode == ShowDetails.RESULT_ADD) {
                    Content note = data.getParcelableExtra(ShowDetails.EXTRA_NOTE);

                    adapter.addItem(note);

                }
            }else if (requestCode == ShowDetails.REQUES_UPDATE){
                if (resultCode == ShowDetails.RESULT_DELETE){
                    int position = data.getIntExtra(ShowDetails.EXTRA_POSITION,0);
                    adapter.removeItem(position);
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        noteHelper.close();
    }


}

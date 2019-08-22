package com.example.submisi3final.fragment;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.submisi3final.R;
import com.example.submisi3final.activities.LoadDataNotesTv;
import com.example.submisi3final.activities.ShowDetailTv;
import com.example.submisi3final.activities.ShowDetails;
import com.example.submisi3final.adapter.TvShowAdapter;
import com.example.submisi3final.model.ContentTv;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import com.example.submisi3final.db.NoteHelperTv;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragmentFav extends Fragment implements LoadDataNotesTv{
    private RecyclerView recyclerView;
    private static final String EXTRA_STATE_TV = "extra_state_tv";
    private TvShowAdapter adapter;
    private NoteHelperTv helperTv;
    private View view;

    public TvShowFragmentFav() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tv_show_fragment_fav, container, false);

        recyclerView = view.findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        helperTv = new NoteHelperTv(getActivity().getApplicationContext());
        helperTv.open();
        adapter = new TvShowAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        if (savedInstanceState == null ){
            new LoadNotesAsync(helperTv,this).execute();
        } else {
            ArrayList<ContentTv> list = savedInstanceState.getParcelableArrayList(EXTRA_STATE_TV);
            if (list != null){
                adapter.setListNotes(list);
            }
        }

        return view;
    }


    @Override
    public void preExecute() {

    }

    @Override
    public void postExecute(ArrayList<ContentTv> notes) {
        adapter.setListNotes(notes);
    }
    private static class LoadNotesAsync extends AsyncTask<Void,Void,ArrayList<ContentTv>>{
        private final WeakReference<NoteHelperTv> weakHelperTv;
        private final WeakReference<LoadDataNotesTv> weakCallback;

        public LoadNotesAsync(NoteHelperTv noteHelper, LoadDataNotesTv callBack) {
            weakHelperTv = new WeakReference<>(noteHelper);
            weakCallback = new WeakReference<>(callBack);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            weakCallback.get().preExecute();
        }

        @Override
        protected ArrayList<ContentTv> doInBackground(Void... voids) {
            return weakHelperTv.get().getAllContentsTv();
        }

        @Override
        protected void onPostExecute(ArrayList<ContentTv> contentTvs) {
            super.onPostExecute(contentTvs);
            weakCallback.get().postExecute(contentTvs);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null){
            if (requestCode == ShowDetailTv.REQUEST_ADD_TV){
                if (resultCode == ShowDetailTv.RESULT_ADD_TV){
                    ContentTv contentTv = data.getParcelableExtra(ShowDetailTv.EXTRA_NOTE);
                    adapter.addItem(contentTv);
                }
            }  else if (requestCode == ShowDetails.REQUES_UPDATE){
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
        helperTv.close();
    }
}

package com.example.submisi3final.activities;

import com.example.submisi3final.model.Content;
import com.example.submisi3final.model.ContentTv;

import java.util.ArrayList;

public interface LoadDataNotesTv {
    void preExecute();

    void postExecute(ArrayList<ContentTv> notes);
}

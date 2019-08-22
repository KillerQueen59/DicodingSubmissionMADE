package com.example.submisi3final.activities;

import com.example.submisi3final.model.Content;

import java.util.ArrayList;
public interface LoadDataNotes {
    void preExecute();

    void postExecute(ArrayList<Content> notes);
}
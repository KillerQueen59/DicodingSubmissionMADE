package com.example.moviefav;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class MovieAdapter<T> extends RecyclerView.Adapter<MovieAdapter<T>.MovieViewHolder> {
    private String TAG = "ADAPTER";
    private Context context;
    private T movieList;
    private Content content;
    private OnItemClickListener mListener;

    public T getMovieList() {
        return movieList;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setListMovies(T  movieList) {
        this.movieList = movieList;
    }

    public MovieAdapter(Context context, T  movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false);
        return new MovieAdapter.MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, final int position) {
        holder.bind(getItem(position));
        Glide.with(context)
                .load(getItem(position).getPosterContent())
                .apply(new RequestOptions().override(250,550))
                .into(holder.posterMovie);
        holder.btnMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowDetails.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(ShowDetails.EXTRA_TITLE,getItem(position));
                context.startActivity(intent);
            }
        });
    }

    private Content getItem(int position) {
        if(movieList.getClass() == ArrayList.class){
            return  ((ArrayList<Content>)movieList).get(position);
        }
        else {
            if (!((Cursor) movieList).moveToPosition(position)) {
                throw new IllegalStateException("Position invalid");
            }
            return new Content((Cursor) movieList);
        }
    }


    @Override
    public int getItemCount() {
        if (movieList == null)
            return 0;
        else if(movieList.getClass() == ArrayList.class)
            return ((ArrayList) this.movieList).size();
        else
            return ((Cursor) movieList).getCount();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle;
        TextView textDate;
        TextView textDesc;
        TextView textRate;
        ImageView posterMovie;
        Button btnMoreInfo;
        Button btnMoreFav;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.judulFilm);
            textDate = itemView.findViewById(R.id.dateFilm);
            textDesc = itemView.findViewById(R.id.descFilm);
            textRate = itemView.findViewById(R.id.rateText);
            posterMovie = itemView.findViewById(R.id.posterFilm);
            btnMoreInfo = itemView.findViewById(R.id.btnMoreInfo);
            btnMoreFav = itemView.findViewById(R.id.btnMoreFav);
        }
        void bind(Content content){
            textTitle.setText(content.getTitleContent());
            textDate.setText(content.getDateContent());
            textDesc.setText(content.getDescContent());
            textRate.setText(content.getRateContet() + "/10");

        }
    }
}

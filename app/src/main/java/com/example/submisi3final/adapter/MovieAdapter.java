package com.example.submisi3final.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.submisi3final.R;
import com.example.submisi3final.activities.ShowDetails;
import com.example.submisi3final.model.Content;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private Context context;
    private ArrayList<Content> mData;

    public ArrayList<Content> getListNotes() {
        return mData;
    }

    public void setData(ArrayList<Content> items){
        mData.addAll(items);
        notifyDataSetChanged();
    }
    public void setListNotes(ArrayList<Content> listNotes) {

        if (listNotes.size() > 0) {
            this.mData.clear();
        }
        this.mData.addAll(listNotes);

        notifyDataSetChanged();
    }

    public void addItem(Content note) {
        this.mData.add(note);
        notifyItemInserted(mData.size() - 1);
    }

    public void removeItem(int position) {
        this.mData.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mData.size());
    }


    public MovieAdapter(Context context,ArrayList arrayList) {
        this.context = context;
        this.mData = arrayList;
    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false);
        return new MovieAdapter.MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, final int position) {
        holder.bind(mData.get(position));
        Glide.with(context)
                .load(mData.get(position).getPosterContent())
                .apply(new RequestOptions().override(250,550))
                .into(holder.posterMovie);
        holder.btnMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowDetails.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(ShowDetails.EXTRA_TITLE,mData.get(position));
                context.startActivity(intent);
            }
        });
    }




    @Override
    public int getItemCount() {
        return getListNotes().size();
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

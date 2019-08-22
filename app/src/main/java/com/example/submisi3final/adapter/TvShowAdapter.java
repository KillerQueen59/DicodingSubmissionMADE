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
import com.example.submisi3final.activities.ShowDetailTv;
import com.example.submisi3final.activities.ShowDetails;
import com.example.submisi3final.model.Content;
import com.example.submisi3final.model.ContentTv;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TvViewHolder> {
    private Context context;
    private ArrayList<ContentTv> mData;

    public void setData(ArrayList<ContentTv> items){
        mData.addAll(items);
        notifyDataSetChanged();
    }
    public void setListNotes(ArrayList<ContentTv> listNotes) {

        if (listNotes.size() > 0) {
            this.mData.clear();
        }
        this.mData.addAll(listNotes);

        notifyDataSetChanged();
    }

    public void addItem(ContentTv note) {
        this.mData.add(note);
        notifyItemInserted(mData.size() - 1);
    }

    public void updateItem(int position, ContentTv note) {
        this.mData.set(position, note);
        notifyItemChanged(position, note);
    }

    public void removeItem(int position) {
        this.mData.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mData.size());
    }


    public TvShowAdapter (Context context) {
        this.context = context;
        mData = new ArrayList<>();
    }

    @NonNull
    @Override
    public TvShowAdapter.TvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tv,parent,false);
        return new TvShowAdapter.TvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowAdapter.TvViewHolder holder, final int position) {
        holder.bind(mData.get(position));
        Picasso.with(context).load(mData.get(position).getPosterContent()).into(holder.posterMovie);
        holder.btnMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowDetailTv.class);
                intent.putExtra(ShowDetailTv.EXTRA_TITLE,mData.get(position));
                context.startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class TvViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle;
        TextView textDate;
        TextView textDesc;
        TextView textRate;
        ImageView posterMovie;
        Button btnMoreInfo;

        public TvViewHolder (@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.judulTv);
            textDate = itemView.findViewById(R.id.dateTv);
            textDesc = itemView.findViewById(R.id.descTV);
            textRate = itemView.findViewById(R.id.rateTextTv);
            posterMovie = itemView.findViewById(R.id.posterTv);
            btnMoreInfo = itemView.findViewById(R.id.btnMoreInfo);
        }
        void bind(ContentTv content){
            textTitle.setText(content.getTitleContent());
            textDate.setText(content.getDateContent());
            textDesc.setText(content.getDescContent());
            textRate.setText(content.getRateContet() + "/10");

        }
    }
}
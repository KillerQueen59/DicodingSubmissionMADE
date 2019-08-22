package com.example.submisi3final.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.example.submisi3final.R;
import com.example.submisi3final.db.DatabaseContract;
import com.example.submisi3final.model.Content;
import com.example.submisi3final.model.ContentFav;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class StackRemoteViewWidget implements RemoteViewsService.RemoteViewsFactory {

    private List<Bitmap>mWidgetItems = new ArrayList<>();
    private Context mContext;
    private int mAppWidgetId;
    private Cursor cursor;

    public StackRemoteViewWidget(Context context, Intent intent) {
        mContext = context;
        mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    private ContentFav getFav(int position) {
        if (!cursor.moveToPosition(position)) {
            throw new IllegalStateException("Position invalid!");
        }

        return new ContentFav(
                cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns.POSTER)));
    }


    public void onCreate() {

        cursor = mContext.getContentResolver().query(
                DatabaseContract.CONTENT_URI,
                null,
                null,
                null,
                null
        );


    }

    @Override
    public void onDataSetChanged() {
        if (cursor != null) {
            cursor.close();
        }
        final long identityToken = Binder.clearCallingIdentity();
        cursor = mContext.getContentResolver().query(
                DatabaseContract.CONTENT_URI, null, null, null, null);
        Binder.restoreCallingIdentity(identityToken);
    }

    @Override
    public void onDestroy() {
        if (cursor != null) {
            cursor.close();
        }else cursor.close();
    }

    @Override
    public int getCount() {
//        return mWidgetItems.size();
        return cursor.getCount();
    }

    @Override
    public RemoteViews getViewAt(int position) {

        ContentFav movieFavorite = getFav(position);
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.widget_fav);
        Content movieItems = new Content(cursor);
        if (!cursor.moveToPosition(position)) {
            throw new IllegalStateException("Position invalid!");
        }


        Bitmap bmp = null;

        try {
            bmp = Glide.with(mContext)
                    .asBitmap()
                    .load(movieItems.getPosterContent())
                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
            rv.setImageViewBitmap(R.id.imageView,bmp);
        }catch (InterruptedException | ExecutionException e){
            Log.d("Widget Load Error","error");
        }




        Bundle extras = new Bundle();
        extras.putInt(FavouriteWidget.EXTRA_ITEM, position);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);

        rv.setOnClickFillInIntent(R.id.imageView, fillInIntent);
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return cursor.moveToPosition(i) ? cursor.getLong(0) : i;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}

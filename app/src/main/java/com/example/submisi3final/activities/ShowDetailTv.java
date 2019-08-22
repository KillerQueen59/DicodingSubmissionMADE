package com.example.submisi3final.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.submisi3final.R;
import com.example.submisi3final.model.ContentTv;
import com.squareup.picasso.Picasso;

import com.example.submisi3final.db.NoteHelperTv;

public class ShowDetailTv extends AppCompatActivity {

    public static final String EXTRA_TITLE = "extra_title";
    public static final String EXTRA_NOTE = "extra_note";
    public static final String EXTRA_POSITION = "extra_position";

    private boolean isEdit = false;
    public static final int REQUEST_ADD_TV = 200;
    public static final int RESULT_ADD_TV = 201;
    public static final int REQUEST_UPDATE_TV = 400;
    public static final int RESULT_DELETE_TV = 300;
    private int position;

    private NoteHelperTv noteHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        final ContentTv content = getIntent().getParcelableExtra(EXTRA_TITLE);
        noteHelper = new NoteHelperTv(this);

        ImageView Poster = findViewById(R.id.posterBesarMovie);
        final TextView Title = findViewById(R.id.titleDetailsMovie);
        TextView Desc = findViewById(R.id.descMovie);
        TextView Date = findViewById(R.id.dateMovie);
        TextView Rate = findViewById(R.id.rateText);
        ImageButton Fav = findViewById(R.id.btnMoreFav);
        ImageButton UnFav = findViewById(R.id.btnUnFav);

        Rate.setText(content.getRateContet() + "/10");
        Title.setText(content.getTitleContent());
        Desc.setText(content.getDescContent());
        Date.setText(content.getDateContent());
        Picasso.with(this).load(content.getPosterContent()).into(Poster);
        Fav.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {

                                       noteHelper.insertNoteTv(content);
                                       setResult(RESULT_ADD_TV);
                                       finish();
                                       Toast.makeText(ShowDetailTv.this, "Data berhasil di tambahkan", Toast.LENGTH_SHORT).show();
                                     }
                                   }
        );
        UnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long result = noteHelper.deleteNote(content.getId());
                if (result > 0) {
                    Intent intent = new Intent(ShowDetailTv.this,MainActivity.class);
                    intent.putExtra(EXTRA_POSITION, position);
                    setResult(RESULT_DELETE_TV, intent);
                    startActivity(intent);
                }
                Toast.makeText(ShowDetailTv.this, "Terhapus", Toast.LENGTH_SHORT).show();
            }
        });
    }


}

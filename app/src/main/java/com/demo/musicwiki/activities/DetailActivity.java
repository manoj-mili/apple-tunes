package com.demo.musicwiki.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.musicwiki.R;
import com.demo.musicwiki.entities.Music;
import com.demo.musicwiki.utils.BaseActivity;
import com.demo.musicwiki.viewmodels.MusicFeedsViewModel;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import static com.demo.musicwiki.adapters.MusicFeedsAdapter.TRACK_ID;

public class DetailActivity extends BaseActivity{

    private static final String TAG = "DetailActivity";
    Integer trackId;
    MusicFeedsViewModel musicFeedsViewModel;
    Music detailedMusic;
    Gson gson;
    private boolean isHideToolbarView = false;
    AppBarLayout appBarLayout;
    Toolbar toolbar;
    TextView collapsingTextView;
    ImageView trackImageView;
    FloatingActionButton addToCartFloatingActionButton;

    TextView artistName,collectionName,country, currency, trackPrice, collectionCensoredName, collectionPrice,
            isStreamAble, releaseDate,primaryGenreName,collectionCurrency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        musicFeedsViewModel = ViewModelProviders.of(this).get(MusicFeedsViewModel.class);
        gson = new Gson();
        Intent callingIntent = getIntent();
        trackId = callingIntent.getIntExtra(TRACK_ID,0);

        toolbar = findViewById(R.id.detailToolbar);
        collapsingTextView = findViewById(R.id.trackNameCollapsing);
        trackImageView  =findViewById(R.id.trackImage);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        addToCartFloatingActionButton = findViewById(R.id.addToCart);
        artistName = findViewById(R.id.artistName);
        collectionName = findViewById(R.id.collectionName);
        country = findViewById(R.id.country);
        currency = findViewById(R.id.trackCurrency);
        trackPrice = findViewById(R.id.trackPrice);
        collectionCensoredName = findViewById(R.id.collectionCensored);
        collectionPrice = findViewById(R.id.collectionPrice);
        isStreamAble = findViewById(R.id.isStreamAble);
        releaseDate = findViewById(R.id.releaseDate);
        primaryGenreName = findViewById(R.id.primaryGenreName);
        collectionCurrency = findViewById(R.id.collectionCurrency);


        if(trackId!=0){
            detailedMusic = musicFeedsViewModel.getMusicByTrackId(trackId);
            if(detailedMusic!=null){
                Log.d(TAG, "onCreate: " + gson.toJson(detailedMusic));
                updateUI();
            }else{
                finish();
            }
        }

        collapsingTextView.setText(detailedMusic.getTrackName());

        addToCartFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetailActivity.this, "Hope EveryOne Likes It, Thanks", Toast.LENGTH_LONG).show();
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Picasso.get().load(detailedMusic.getArtworkUrl100()).into(trackImageView);
    }

    private void updateUI() {
        toolbar.setTitle(detailedMusic.getTrackName());
        artistName.setText(detailedMusic.getArtistName());
        collectionCensoredName.setText(detailedMusic.getCollectionCensoredName());
        collectionName.setText(detailedMusic.getCollectionName());
        collectionCurrency.setText(detailedMusic.getCurrency());
        country.setText(detailedMusic.getCountry());
        currency.setText(detailedMusic.getCurrency());
        primaryGenreName.setText(detailedMusic.getPrimaryGenreName());
        trackPrice.setText(String.valueOf(detailedMusic.getTrackPrice()));
        collectionPrice.setText(String.valueOf(detailedMusic.getCollectionPrice()));
        releaseDate.setText(detailedMusic.getReleaseDate());

        if(detailedMusic.getIsStreamable().equals("true")){
            isStreamAble.setText(R.string.isStreamAbleYes);
        }else{
            isStreamAble.setText(R.string.isStreamAbleNo);
        }

    }

}

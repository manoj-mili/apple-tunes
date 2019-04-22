package com.demo.musicwiki.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.demo.musicwiki.R;
import com.demo.musicwiki.adapters.MusicFeedsAdapter;
import com.demo.musicwiki.entities.Music;
import com.demo.musicwiki.utils.BaseActivity;
import com.demo.musicwiki.utils.RequestController;
import com.demo.musicwiki.viewmodels.MusicFeedsViewModel;
import com.google.gson.Gson;

import java.util.List;

public class HomeActivity extends BaseActivity {
    private static final String TAG = "HomeActivity";

    RecyclerView musicListRecyclerView;
    MusicFeedsViewModel musicFeedsViewModel;
    MusicFeedsAdapter musicFeedsAdapter;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        musicFeedsViewModel = ViewModelProviders.of(this).get(MusicFeedsViewModel.class);

        /*toolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);*/
        musicListRecyclerView = findViewById(R.id.musicListRecyclerView);
        musicListRecyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.VERTICAL, false));
        musicListRecyclerView.setHasFixedSize(true);
        gson = new Gson();

        /*
         Initial Check to load data and needs to execute once per Application launch.

        */

        RequestController requestController = (RequestController) this.getApplicationContext();
        if(!requestController.initialTaskChecks){
            //do required one time task per application start
            Log.d(TAG, "onCreate: request controller for pulling the data from server once per application life cycle");
            musicFeedsViewModel.loadMusicFromServer();
            requestController.initialTaskChecks = true;
        }


        /*
         Because we already know there won't be any data change to what we have so we can use only notify data set else its better to use
         DiffUtils checking if any of the data was updated in live data
        */

        musicFeedsViewModel.getAllLiveMusic().observe(this, new Observer<List<Music>>() {
            @Override
            public void onChanged(List<Music> musicList) {
                if(musicList!=null && musicList.size()>0){
                    Log.d(TAG, "onChanged: data received"+gson.toJson(musicList));
                    musicFeedsAdapter = new MusicFeedsAdapter(musicList,HomeActivity.this);
                    musicListRecyclerView.setAdapter(musicFeedsAdapter);
                    musicFeedsAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: called");
    }


}

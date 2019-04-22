package com.demo.musicwiki.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.demo.musicwiki.entities.Music;
import com.demo.musicwiki.utils.Repository;

import java.util.List;

public class MusicFeedsViewModel extends AndroidViewModel {

    private Repository repository;
    public MusicFeedsViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public LiveData<List<Music>> getAllLiveMusic(){
        return repository.getAllLiveMusic();
    }

    public Music getMusicByTrackId(Integer trackId){
        return repository.getMusicByTrackId(trackId);
    }

    public void loadMusicFromServer(){
        repository.loadMusicFeedsFromServer();
    }

}

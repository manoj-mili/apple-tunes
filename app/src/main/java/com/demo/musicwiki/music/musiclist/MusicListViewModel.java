package com.demo.musicwiki.music.musiclist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.demo.musicwiki.data.Resource;
import com.demo.musicwiki.data.entities.Music;
import com.demo.musicwiki.data.repository.MusicRepository;

import java.util.List;

public class MusicListViewModel extends ViewModel {

    private MusicRepository repository;

    public MusicListViewModel(MusicRepository repository) {
        this.repository = repository;
    }

    public LiveData<Resource<List<Music>>> getAllLiveMusic(boolean isConnected) {
        return repository.getAllLiveMusic(isConnected);
    }
}

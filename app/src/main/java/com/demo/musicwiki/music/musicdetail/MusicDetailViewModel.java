package com.demo.musicwiki.music.musicdetail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.demo.musicwiki.data.entities.Music;
import com.demo.musicwiki.data.repository.MusicRepository;

public class MusicDetailViewModel extends ViewModel {
    private MusicRepository repository;
    public MusicDetailViewModel(MusicRepository repository) {
        this.repository = repository;
    }

    LiveData<Music> getMusicByTrackId(int trackId) {
        return repository.getMusicByTrackId(trackId);
    }
}

package com.demo.musicwiki.di;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.demo.musicwiki.data.repository.MusicRepository;
import com.demo.musicwiki.music.musiclist.MusicListViewModel;

public class MusicListViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private MusicRepository repository;

    MusicListViewModelFactory(MusicRepository repository) {
        this.repository = repository;
    }

    public ViewModel create(@NonNull Class modelClass) {
        return new MusicListViewModel(repository);
    }
}

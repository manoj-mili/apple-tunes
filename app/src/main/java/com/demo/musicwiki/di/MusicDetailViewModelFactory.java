package com.demo.musicwiki.di;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.demo.musicwiki.data.repository.MusicRepository;
import com.demo.musicwiki.music.musicdetail.MusicDetailViewModel;

public class MusicDetailViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private MusicRepository repository;

    public MusicDetailViewModelFactory(MusicRepository repository) {
        this.repository = repository;
    }

    public ViewModel create(@NonNull Class modelClass) {
        return new MusicDetailViewModel(repository);
    }
}

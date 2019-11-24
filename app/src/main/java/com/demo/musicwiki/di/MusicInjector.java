package com.demo.musicwiki.di;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.demo.musicwiki.data.MusicRoomDatabase;
import com.demo.musicwiki.data.RetrofitProvider;
import com.demo.musicwiki.data.repository.MusicRepository;

public class MusicInjector extends AndroidViewModel {

    public MusicInjector(@NonNull Application application) {
        super(application);
    }

    public MusicDetailViewModelFactory provideMusicDetailViewModelFactory() {
        return new MusicDetailViewModelFactory(new MusicRepository(RetrofitProvider.getRetrofitClient(), MusicRoomDatabase.getInstance(getApplication()).musicDao()));
    }

    public MusicListViewModelFactory provideMusicListViewModelFactory() {
        return new MusicListViewModelFactory(new MusicRepository(RetrofitProvider.getRetrofitClient(), MusicRoomDatabase.getInstance(getApplication()).musicDao()));
    }


}

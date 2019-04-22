package com.demo.musicwiki.dao;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.demo.musicwiki.entities.Music;

import java.util.List;

@Dao
public interface MusicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Music music);

    @Query("SELECT * FROM MUSIC_TABLE")
    LiveData<List<Music>> getAllLiveMusic();

    @Query("SELECT * FROM MUSIC_TABLE WHERE trackId =:trackId")
    Music getMusicByTrackId(Integer trackId);
}

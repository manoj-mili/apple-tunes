package com.demo.musicwiki.data.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.demo.musicwiki.data.entities.Music;

import java.util.List;

@Dao
public interface MusicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Music music);

    @Query("SELECT * FROM music ORDER BY RANDOM()")
    LiveData<List<Music>> getAllLiveMusic();

    @Query("SELECT * FROM music WHERE trackId =:trackId")
    LiveData<Music> getMusicByTrackId(Integer trackId);
}

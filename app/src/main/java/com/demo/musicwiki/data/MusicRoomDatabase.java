package com.demo.musicwiki.data;


import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Context;

import androidx.annotation.NonNull;

import com.demo.musicwiki.data.dao.MusicDao;
import com.demo.musicwiki.data.entities.Music;

@Database(entities = {Music.class}, version = 1)
public abstract class MusicRoomDatabase extends RoomDatabase {

    public abstract MusicDao musicDao();
    private static volatile MusicRoomDatabase musicRoomDatabase;


    public static MusicRoomDatabase getInstance(Context context) {

        if (musicRoomDatabase == null) {
            musicRoomDatabase = Room.databaseBuilder(context,
                    MusicRoomDatabase.class, "music_app_db")
                    .build();
        }
        return musicRoomDatabase;
    }
}

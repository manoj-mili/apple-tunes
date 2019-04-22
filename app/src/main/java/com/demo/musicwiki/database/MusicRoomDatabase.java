package com.demo.musicwiki.database;


import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.demo.musicwiki.dao.MusicDao;
import com.demo.musicwiki.entities.Music;

@Database(entities = {Music.class}, version = 1)
public abstract class MusicRoomDatabase extends RoomDatabase {

    private static final String TAG = "MusicRoomDatabase";

    private static MusicRoomDatabase musicRoomDatabase;
    public abstract MusicDao musicDao();


    public static MusicRoomDatabase getInstance(Context context){

        if (musicRoomDatabase == null) {
            musicRoomDatabase = Room.databaseBuilder(context,
                    MusicRoomDatabase.class, "music_app_db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .addCallback(roomCallback)
                    .build();
        }
        return musicRoomDatabase;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };
}

package com.demo.musicwiki.data.repository;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.demo.musicwiki.MusicApp;
import com.demo.musicwiki.data.ApiService;
import com.demo.musicwiki.data.MusicResponse;
import com.demo.musicwiki.data.NetworkBoundResource;
import com.demo.musicwiki.data.Resource;
import com.demo.musicwiki.data.dao.MusicDao;
import com.demo.musicwiki.data.entities.Music;
import com.demo.musicwiki.utils.SharedPreferenceHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;

import static com.demo.musicwiki.utils.Constants.LAST_SYNC_TIME;

public class MusicRepository {
    private MusicDao musicDao;
    private ApiService apiService;
    public MusicRepository(ApiService apiService, MusicDao musicDao) {
        this.apiService = apiService;
        this.musicDao = musicDao;
    }

    public LiveData<Resource<List<Music>>> getAllLiveMusic(boolean isConnected) {
        return new NetworkBoundResource<List<Music>, MusicResponse>(){

            @Override
            protected void saveCallResult(@NonNull MusicResponse music) {
                // Date Pattern 1982-11-30T08:00:00Z
                @SuppressLint("SimpleDateFormat") SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                @SuppressLint("SimpleDateFormat") SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");

                if(music.getMusicList() != null && music.getMusicList().size() > 0) {
                    for (Music updatedMusic : music.getMusicList()) {
                        try {
                            Date date = inputFormat.parse(updatedMusic.getReleaseDate());
                            String formattedDate = outputFormat.format(date != null ? date : "Unknown");
                            updatedMusic.setReleaseDate(formattedDate);
                            musicDao.insert(updatedMusic);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }

                    SharedPreferenceHelper.writeToSharedPreference(MusicApp.getAppContext(),LAST_SYNC_TIME,String.valueOf(System.currentTimeMillis()));
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Music> music) {
                return (music == null || music.isEmpty() || System.currentTimeMillis() - Long.valueOf(SharedPreferenceHelper.readFromSharedPreference
                        (MusicApp.getAppContext(),LAST_SYNC_TIME,String.valueOf(0))) > 43200000) && isConnected;
            }

            @NonNull
            @Override
            protected LiveData<List<Music>> loadFromDb() {
                return musicDao.getAllLiveMusic();
            }

            @NonNull
            @Override
            protected Call<MusicResponse> createCall() {
                return apiService.getMusicCollection("Michael+jackson");
            }
        }.getAsLiveData();
    }

    public LiveData<Music> getMusicByTrackId(Integer trackId) {
        return musicDao.getMusicByTrackId(trackId);
    }
}

package com.demo.musicwiki.data;

import com.demo.musicwiki.data.entities.Music;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MusicResponse {

    @SerializedName("results")
    private List<Music> musicList;

    @SerializedName("resultCount")
    private int count;

    public List<Music> getMusicList() {
        return musicList;
    }

    public int getCount() {
        return count;
    }
}

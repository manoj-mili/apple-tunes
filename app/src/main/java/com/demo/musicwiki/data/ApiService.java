package com.demo.musicwiki.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("search")
    Call<MusicResponse> getMusicCollection(@Query("term") String term);
}

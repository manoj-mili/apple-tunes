package com.demo.musicwiki.utils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.demo.musicwiki.dao.MusicDao;
import com.demo.musicwiki.database.MusicRoomDatabase;
import com.demo.musicwiki.entities.Music;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.demo.musicwiki.utils.BaseActivity.BASE_API_URL;

public class Repository {
    private static final String TAG = "Repository";
    private MusicDao musicDao;
    public Repository(Application application) {
        Log.d(TAG, "Repository: Repository call happened");
        MusicRoomDatabase musicRoomDatabase = MusicRoomDatabase.getInstance(application.getApplicationContext());
        musicDao = musicRoomDatabase.musicDao();
       // loadMusicFeedsFromServer();
    }

    public LiveData<List<Music>> getAllLiveMusic() {
        return musicDao.getAllLiveMusic();
    }

    public Music getMusicByTrackId(Integer trackId){
        return musicDao.getMusicByTrackId(trackId);
    }

    public void loadMusicFeedsFromServer(){

        Log.d(TAG, "loadMusicFeedsFromServer: called");
        JsonObjectRequest jsonObjectRequest  = new JsonObjectRequest(BASE_API_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    Log.d(TAG, "onResponse: response is -----"+response);
                    if(response.getInt("resultCount")>0){
                        Log.d(TAG, "onResponse: Inside if");
                        JSONArray jsonResultArray = response.getJSONArray("results");
                        new InsertMusicFeeds(musicDao).execute(jsonResultArray);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: "+error.toString());
            }
        });

        RequestController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    private static class InsertMusicFeeds extends AsyncTask<JSONArray,Void,Void> {
        MusicDao musicDao;
        private InsertMusicFeeds(MusicDao musicDao) {
            this.musicDao = musicDao;
        }

        @Override
        protected Void doInBackground(JSONArray... jsonArrays) {
            Log.d(TAG, "doInBackground: called");
            JSONObject jsonObject;
            for(int i=0; i<jsonArrays[0].length();i++){
                Music music = new Music();
                try {
                    jsonObject = jsonArrays[0].getJSONObject(i);
                    // Date Pattern 1982-11-30T08:00:00Z
                    @SuppressLint("SimpleDateFormat") SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                    @SuppressLint("SimpleDateFormat") SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    Date date = inputFormat.parse(jsonObject.getString("releaseDate"));
                    String formattedDate = outputFormat.format(date);

                    music.setArtistId(jsonObject.getInt("artistId"));
                    music.setArtistName(jsonObject.getString("artistName"));
                    music.setArtworkUrl100(jsonObject.getString("artworkUrl100"));
                    music.setCollectionName(jsonObject.getString("collectionName"));
                    music.setCollectionCensoredName(jsonObject.getString("collectionCensoredName"));
                    music.setCollectionPrice(jsonObject.getDouble("collectionPrice"));
                    music.setCollectionId(jsonObject.getInt("collectionId"));
                    music.setTrackId(jsonObject.getInt("trackId"));
                    music.setTrackName(jsonObject.getString("trackName"));
                    music.setTrackPrice(jsonObject.getDouble("trackPrice"));
                    music.setReleaseDate(formattedDate);
                    music.setArtistViewUrl(jsonObject.getString("artistViewUrl"));
                    music.setCountry(jsonObject.getString("country"));
                    music.setCurrency(jsonObject.getString("currency"));
                    music.setKind(jsonObject.getString("kind"));
                    music.setPreviewUrl(jsonObject.getString("previewUrl"));
                    music.setPrimaryGenreName(jsonObject.getString("primaryGenreName"));
                    music.setIsStreamable(String.valueOf(jsonObject.getBoolean("isStreamable")));
                    music.setTrackTimeMillis(jsonObject.getInt("trackTimeMillis"));

                    musicDao.insert(music);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }
    }
}

package com.demo.musicwiki.adapters;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.demo.musicwiki.R;
import com.demo.musicwiki.activities.DetailActivity;
import com.demo.musicwiki.activities.HomeActivity;
import com.demo.musicwiki.entities.Music;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

public class MusicFeedsAdapter extends RecyclerView.Adapter<MusicFeedsAdapter.ViewHolder> {

    public static final String TRACK_ID = "TRACK_ID";
    private List<Music> musicList;
    private Context context;
    private static final String TAG = "MusicFeedsAdapter";
    private Boolean isPlaying = false;
    private MediaPlayer player;


    public MusicFeedsAdapter(List<Music> musicList, Context context) {
        this.musicList = musicList;
        this.context = context;
    }

    @NonNull
    @Override
    public MusicFeedsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.music_card,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MusicFeedsAdapter.ViewHolder viewHolder, int position) {

        final Music music = musicList.get(position);

        Integer trackTimeInMilliSecs = music.getTrackTimeMillis();
        Integer trackTimeMin =  (((trackTimeInMilliSecs/1000)/60)%60);
        Integer trackTimeSec =  ((trackTimeInMilliSecs/1000) % 60);

        viewHolder.artistNameTextView.setText(music.getArtistName());
        viewHolder.trackNameTextView.setText(music.getTrackName());
        viewHolder.trackTimeMillisTextView.setText(String.valueOf(trackTimeMin).concat(":").concat(String.valueOf(trackTimeSec)));


        try {
            Picasso.get().load(music.getArtworkUrl100()).into(viewHolder.artworkImageView, new Callback() {
                @Override
                public void onSuccess() {
                    Log.d(TAG, "onSuccess: ");
                }

                @Override
                public void onError(Exception e) {
                    viewHolder.artworkImageView.setVisibility(View.GONE);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        viewHolder.playPreviewMusicFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isPlaying){
                    HomeActivity homeActivity = (HomeActivity)context;
                        playPreviewMusic(viewHolder,music.getPreviewUrl());
                        viewHolder.playPreviewMusicFloatingActionButton.setImageResource(R.drawable.ic_pause);

                }else{
                    stopPreviewMusic();
                    viewHolder.playPreviewMusicFloatingActionButton.setImageResource(R.drawable.ic_play_arrow);
                }
            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopPreviewMusic();
                viewHolder.playPreviewMusicFloatingActionButton.setImageResource(R.drawable.ic_play_arrow);
                Intent detailActivity = new Intent(context, DetailActivity.class);
                detailActivity.putExtra(TRACK_ID,music.getTrackId());
                context.startActivity(detailActivity);
            }
        });

    }

    private void stopPreviewMusic() {
        if(player!=null){
            player.release();
            isPlaying = false;
        }

    }

    private void playPreviewMusic(final ViewHolder viewHolder, String previewUrl) {
        try {
            isPlaying = true;
            Uri uri = Uri.parse(previewUrl);
            player = new MediaPlayer();
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setDataSource(context, uri);
            player.prepareAsync();

            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    player.start();
                }
            });

            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    viewHolder.playPreviewMusicFloatingActionButton.setImageResource(R.drawable.ic_play_arrow);
                }
            });
        } catch(IOException e) {
            Toast.makeText(context, "Music cannot be streamed now, Please check your internet connection", Toast.LENGTH_SHORT).show();
            System.out.println("Error is" +e.toString());
        }
    }

    @Override
    public int getItemCount() {

        return musicList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView trackNameTextView, artistNameTextView,trackTimeMillisTextView;
        FloatingActionButton playPreviewMusicFloatingActionButton;
        ImageView artworkImageView;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);

            trackNameTextView = itemView.findViewById(R.id.trackName);
            trackTimeMillisTextView = itemView.findViewById(R.id.trackTimeMillis);
            artistNameTextView = itemView.findViewById(R.id.artistName);
            playPreviewMusicFloatingActionButton = itemView.findViewById(R.id.playPreviewMusic);
            artworkImageView = itemView.findViewById(R.id.artworkImage);
        }
    }
}

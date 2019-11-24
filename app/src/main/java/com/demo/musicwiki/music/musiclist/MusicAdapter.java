package com.demo.musicwiki.music.musiclist;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.musicwiki.R;
import com.demo.musicwiki.data.entities.Music;
import com.demo.musicwiki.databinding.ViewMusicCardBinding;

import java.util.List;

import static com.demo.musicwiki.utils.ViewUtils.timeInMinutes;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {
    private List<Music> musicList;
    private MusicAdapterClickListener listener;

    public MusicAdapter(List<Music> musicList, MusicAdapterClickListener listener) {
        this.musicList = musicList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MusicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewMusicCardBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.view_music_card, viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final MusicAdapter.ViewHolder viewHolder, int position) {
        viewHolder.bind(musicList.get(position));
        viewHolder.binding.tvTrackMillis.setText(timeInMinutes(musicList.get(position).getTrackTimeMillis()));
    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ViewMusicCardBinding binding;

        public ViewHolder(@NonNull ViewMusicCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Music item) {
            binding.setMusic(item);
            binding.executePendingBindings();
            binding.setListener(listener);
        }
    }
}

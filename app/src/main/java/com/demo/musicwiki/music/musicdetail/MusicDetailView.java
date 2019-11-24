package com.demo.musicwiki.music.musicdetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.demo.musicwiki.R;
import com.demo.musicwiki.databinding.FragmentDetailViewBinding;
import com.demo.musicwiki.di.MusicInjector;

public class MusicDetailView extends Fragment {
    private MusicDetailViewModel viewModel;
    private FragmentDetailViewBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_view, container, false);
        viewModel = new ViewModelProvider(this, new MusicInjector(requireActivity().getApplication()).provideMusicDetailViewModelFactory()).get(MusicDetailViewModel.class);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        observeChanges();
    }

    private void observeChanges() {
        viewModel.getMusicByTrackId(getArguments().getInt("trackId", 0)).observe(this,
                music -> binding.setMusic(music));
    }
}

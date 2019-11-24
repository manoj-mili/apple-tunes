package com.demo.musicwiki.music.musiclist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.demo.musicwiki.R;
import com.demo.musicwiki.databinding.FragmentListViewBinding;
import com.demo.musicwiki.di.MusicInjector;
import com.demo.musicwiki.utils.InternetConnectionLiveData;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import static com.demo.musicwiki.utils.ViewUtils.buildSnackBarWithMessage;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusicListView extends Fragment implements MusicAdapterClickListener{
    private FragmentListViewBinding binding;
    private MusicAdapter adapter;
    private MusicListViewModel viewModel;
    private boolean isMusicPlayed;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_list_view,container,false);
        viewModel = new ViewModelProvider(this, new MusicInjector(requireActivity().getApplication()).provideMusicListViewModelFactory()).get(MusicListViewModel.class);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        observeChanges();
    }

    private void observeChanges() {

        new InternetConnectionLiveData(requireContext()).observe(this, isConnected ->{
            viewModel.getAllLiveMusic(isConnected).observe(this, musicList ->{
                switch (musicList.status) {
                    case ERROR:
                        Snackbar.make(binding.getRoot(),musicList.message,Snackbar.LENGTH_LONG).show();
                        break;

                    case LOADING:
                        buildSnackBarWithMessage(binding.getRoot(),requireContext(),"");
                        break;

                    case SUCCESS:
                        if (musicList.data != null && musicList.data.size() > 0) {
                            adapter = new MusicAdapter(musicList.data,this);
                            binding.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        } else if(!isConnected) {
                            Snackbar.make(binding.getRoot(),"Connection Not Available", Snackbar.LENGTH_LONG).show();
                        }
                        break;
                }
            });
        });

    }

    @Override
    public void onMusicItemClicked(int trackId, String trackName) {
        MusicListViewDirections.ActionListViewToDetailView action =
        MusicListViewDirections.actionListViewToDetailView(trackName);
        action.setTrackId(trackId);
        Navigation.findNavController(binding.getRoot()).navigate(action);
    }
}

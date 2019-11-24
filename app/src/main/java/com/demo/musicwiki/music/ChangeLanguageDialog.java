package com.demo.musicwiki.music;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.demo.musicwiki.R;
import com.demo.musicwiki.databinding.DialogChangeLanguageBinding;
import com.demo.musicwiki.utils.SharedPreferenceHelper;

import static com.demo.musicwiki.utils.Constants.ENGLISH;
import static com.demo.musicwiki.utils.Constants.KEY_PREFERRED_LANGUAGE;

public class ChangeLanguageDialog extends DialogFragment {

    private DialogChangeLanguageBinding binding;
    private String currentLanguage;
    private static LanguageChangeClickListener listener;

    public static ChangeLanguageDialog getInstance(LanguageChangeClickListener clickListener) {
        ChangeLanguageDialog dialog = new ChangeLanguageDialog();
        listener = clickListener;
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentLanguage = SharedPreferenceHelper.readFromSharedPreference(getContext(),KEY_PREFERRED_LANGUAGE,ENGLISH);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_change_language,null,false);
        binding.setLanguage(currentLanguage);
        binding.setListener(listener);
        return new AlertDialog.Builder(getActivity()).setView(binding.getRoot()).create();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        listener = null;
    }
}

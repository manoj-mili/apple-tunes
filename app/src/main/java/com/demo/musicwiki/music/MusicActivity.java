package com.demo.musicwiki.music;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.demo.musicwiki.R;
import com.demo.musicwiki.databinding.ActivityMusicBinding;
import com.demo.musicwiki.utils.LocaleManager;

import timber.log.Timber;

import static com.demo.musicwiki.utils.Constants.ENGLISH;
import static com.demo.musicwiki.utils.Constants.HINDI;
import static com.demo.musicwiki.utils.LocaleManager.getLanguage;
import static com.demo.musicwiki.utils.LocaleManager.setNewLocale;

public class MusicActivity extends AppCompatActivity implements LanguageChangeClickListener{
    ActivityMusicBinding binding;
    NavController controller;
    ChangeLanguageDialog languageDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_music);
        controller = Navigation.findNavController(this,R.id.fragment_music);
        NavigationUI.setupActionBarWithNavController(this,controller);
    }

    @Override
    public boolean onSupportNavigateUp() {
        controller.navigateUp();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_music,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_option_change_language) {
            changeLanguageOption();
        }
        // if NavigationUI.setupActionBarWithNavController is used and Menu is passed in On CreateOptionsMenu then below code is necessary
        else if(item.getItemId() == android.R.id.home) {
            onSupportNavigateUp();
        }
        return true;
    }

    private void changeLanguageOption() {
        languageDialog = ChangeLanguageDialog.getInstance(this);
        languageDialog.show(getSupportFragmentManager(),"CHANGE LANGUAGE");
    }

    @Override
    public void onOkClicked(RadioGroup radioGroup) {
        if (radioGroup.getCheckedRadioButtonId() == R.id.rb_hindi && getLanguage(MusicActivity.this).equals(ENGLISH)) {
            Timber.d("Hindi Update");
            setNewLocale(MusicActivity.this, HINDI);
            recreate();
        } else if (radioGroup.getCheckedRadioButtonId() == R.id.rb_english && getLanguage(MusicActivity.this).equals(HINDI)) {
            Timber.d("English Update");
            setNewLocale(MusicActivity.this, ENGLISH);
            recreate();
        }

        languageDialog.dismiss();
    }

    @Override
    public void onCancelClicked() {
        languageDialog.dismiss();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleManager.setLocale(newBase));
    }
}

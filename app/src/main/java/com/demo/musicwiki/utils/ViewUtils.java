package com.demo.musicwiki.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.TimeUnit;

public class ViewUtils {

    public static Snackbar buildSnackBarWithMessage(View view, Context context, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE);
        ViewGroup viewGroup = (ViewGroup) snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_text).getParent();
        ProgressBar itemProgressBar = new ProgressBar(context);
        viewGroup.addView(itemProgressBar, 0);
        return snackbar;
    }

    public static void hideSnackBarIfVisible(Snackbar snackbar) {
        if(snackbar != null && snackbar.isShownOrQueued()) {
            snackbar.dismiss();
        }
    }

    public static String timeInMinutes(long milliseconds) {
        long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds);
        return String.valueOf(minutes).concat(":").concat(String.valueOf(seconds));
    }
}

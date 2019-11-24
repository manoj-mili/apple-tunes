package com.demo.musicwiki.utils;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;

public class BindingUtils {

    @BindingAdapter("android:src")
    public static void setImageUrl(ImageView view, String url ){
        Picasso.get().load(url).fit().centerCrop().into(view);
    }
}

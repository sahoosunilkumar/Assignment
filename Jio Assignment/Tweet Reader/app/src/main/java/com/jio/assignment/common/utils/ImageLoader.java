package com.jio.assignment.common.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jio.assignment.R;

/**
 * Created by sunilkumarsahoo on 11/2/17.
 */

public class ImageLoader {
    private ImageLoader() {

    }

    public static void loadImage(String endPoint, ImageView view) {
        String url = String.format(view.getContext().getString(R.string
                .image_url), endPoint);
        Glide.with(view.getContext()).load(url).into
                (view);
    }

    public static void loadImage(ImageView view, Result result) {
        String endPoint = result.getPosterPath() != null ? result.getPosterPath() : result.getBackdropPath();
        ImageLoader.loadImage(endPoint, view);
    }
}

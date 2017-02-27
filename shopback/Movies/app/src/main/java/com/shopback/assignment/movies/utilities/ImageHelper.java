package com.shopback.assignment.movies.utilities;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.shopback.assignment.movies.common.BaseActivity;
import com.squareup.picasso.Picasso;

/**
 * Created by sunilkumarsahoo on 2/27/17.
 */

public class ImageHelper {

    public static void setImageFromUrl(Context context, String imageUrl, ImageView imageView) {

        Log.d("TAG", "Image Url is set as : "+imageUrl+" image view : "+imageView);
        Picasso.with(context)
                .load(imageUrl)
                .into(imageView);
    }

    public static String getLogoPath(String relativePath){
        return BaseActivity.getConfiguration().getImages().getBaseUrl()+BaseActivity.getConfiguration().getImages().getLogoSizes().get(1)+relativePath;
    }

    public static String getBackdropPath(String relativePath){
        return BaseActivity.getConfiguration().getImages().getBaseUrl()+BaseActivity.getConfiguration().getImages().getBackdropSizes().get(1)+relativePath;
    }
}

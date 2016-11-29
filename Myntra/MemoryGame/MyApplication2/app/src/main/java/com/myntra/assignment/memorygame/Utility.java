package com.myntra.assignment.memorygame;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;

import com.myntra.assignment.controller.entity.Item;
import com.myntra.assignment.controller.entity.Media;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunilkumarsahoo on 11/15/16.
 */
public class Utility {

    public static Media createDefaultMedia(Context context) {
        Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + context.getResources().getResourcePackageName(R
                .drawable.question)
                + '/' + context.getResources().getResourceTypeName(R.drawable
                .question) + '/' + context.getResources()
                .getResourceEntryName(R.drawable.question));
        Media media = new Media();
        media.setM(imageUri.getPath());
        return media;
    }

    public static List<Item> formatList(List<Item> list, int maxSize) {
        List<Item> newList = new ArrayList<>();
        for (int i = 0; i < maxSize; i++) {
            newList.add(list.get(i));
        }
        return newList;
    }
}

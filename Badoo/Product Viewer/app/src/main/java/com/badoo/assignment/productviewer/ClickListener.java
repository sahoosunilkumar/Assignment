package com.badoo.assignment.productviewer;

import android.view.View;

/**
 * Created by sunilkumarsahoo on 11/16/16.
 */
public interface ClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}

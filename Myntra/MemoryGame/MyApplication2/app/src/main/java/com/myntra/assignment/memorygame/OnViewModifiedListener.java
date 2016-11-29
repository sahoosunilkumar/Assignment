package com.myntra.assignment.memorygame;

import android.view.View;

/**
 * Created by sunilkumarsahoo on 11/14/16.
 */
public interface OnViewModifiedListener {
    void onItemLongClickStateChanged(int state);

    void onListItemUpdated();

    void onTimerStatusChanged(int status);

    void changeStateTo(Constants.State state);

    void onAnswerSelected(View view, boolean isCorrectAnswer);

    void showProgressBar();

    void dismissProgressBar();
}

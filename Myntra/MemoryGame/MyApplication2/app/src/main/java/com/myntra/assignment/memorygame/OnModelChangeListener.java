package com.myntra.assignment.memorygame;

import com.myntra.assignment.controller.entity.Item;

import java.util.List;

/**
 * Created by sunilkumarsahoo on 11/15/16.
 */
public interface OnModelChangeListener {
    void onInitCalled(List<Item> drawables);

    void onQuestionChanged(int questionCount);

    Game onFetchGame();

    void onItemUpdated(int randomPositionAns, int randomPositionPlace);
}

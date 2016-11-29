package com.myntra.assignment.memorygame;

import com.myntra.assignment.controller.entity.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by sunilkumarsahoo on 11/14/16.
 */
public class Game implements OnModelChangeListener {
    private int answerPosition;
    private int randomPosition;
    private Random rand;
    private int correctAnsCount;

    private List<Item> drawables;
    private List<Item> initialDrawables = new ArrayList<>();

    public int getAnswerPosition() {
        return answerPosition;
    }

    public void setAnswerPosition(int answerPosition) {
        this.answerPosition = answerPosition;
    }

    public int getRandomPosition() {
        return randomPosition;
    }

    public void setRandomPosition(int randomPosition) {
        this.randomPosition = randomPosition;
    }

    @Override
    public void onQuestionChanged(int questionCount) {

        setAnswerPosition(getRandomPosition(0, questionCount - 1));
        setRandomPosition(getRandomPosition(0, questionCount - 1));
    }

    @Override
    public Game onFetchGame() {
        return this;
    }

    private int getRandomPosition(int minimum, int maximum) {
        return minimum + rand.nextInt((maximum - minimum) + 1);
    }

    @Override
    public void onInitCalled(List<Item> itemList) {
        rand = new Random();
        if (drawables != null) {
            drawables.clear();
        } else {
            drawables = new ArrayList<>();
        }

        initialDrawables = itemList;
        drawables.addAll(initialDrawables);
    }


    @Override
    public void onItemUpdated(int randomPositionAns, int randomPositionPlace) {
        Item selectedMedia = initialDrawables.get(randomPositionAns);
        Item item = new Item();

        int count = drawables.size();
        drawables.clear();
        for (int i = 0; i < count; i++) {
            if (i != randomPositionPlace) {
                drawables.add(item);
            } else {
                drawables.add(selectedMedia);
            }
        }
    }

    public List<Item> getDrawables() {
        if (drawables == null) {
            drawables = new ArrayList<>();
        }
        return drawables;
    }

    public int getCorrectAnsCount() {
        return correctAnsCount;
    }

    public void setCorrectAnsCount(int correctAnsCount) {
        this.correctAnsCount = correctAnsCount;
    }
}

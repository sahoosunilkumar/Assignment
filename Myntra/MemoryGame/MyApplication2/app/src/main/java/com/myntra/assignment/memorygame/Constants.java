package com.myntra.assignment.memorygame;

/**
 * Created by sunilkumarsahoo on 11/14/16.
 */
public interface Constants {
    int TICKER_INTERVAL = 1000;
    int TOTAL_TICKER_TIME = 15 * TICKER_INTERVAL;
    int MAX_NO_OF_IMAGES = 9;

    enum State {
        INITIALIZING, NEW, ABOUT_TO_RUN, RUNNING, LEVEL_SUCCESS, STOP
    }

    interface TimerStatus {
        int START = 0;
        int STOP = 1;
    }

    interface LongClickState {
        int ADD = 0;
        int REMOVE = 1;
    }
}

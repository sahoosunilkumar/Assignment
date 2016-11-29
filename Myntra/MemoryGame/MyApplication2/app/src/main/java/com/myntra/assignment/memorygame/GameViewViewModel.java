package com.myntra.assignment.memorygame;

import android.util.Log;
import android.view.View;

import com.myntra.assignment.controller.ITaskListener;
import com.myntra.assignment.controller.ImageListController;
import com.myntra.assignment.controller.entity.ImageList;
import com.myntra.assignment.controller.entity.RestResponse;

/**
 * Created by sunilkumarsahoo on 11/14/16.
 */
public class GameViewViewModel implements OnStateChangeListener, ITaskListener {
    private OnViewModifiedListener viewModifiedListener;
    private OnModelChangeListener modelChangeListener;
    private ImageList imageList;

    GameViewViewModel(OnViewModifiedListener viewModifiedListener) {
        this.viewModifiedListener = viewModifiedListener;
        this.modelChangeListener = new Game();
    }

    @Override
    public void onStateChanged(Constants.State state) {
        Log.d("Sunil", "onStateChanged : " + state);
        viewModifiedListener.onItemLongClickStateChanged(Constants
                .LongClickState.REMOVE);
        switch (state) {
            case INITIALIZING:
                viewModifiedListener.showProgressBar();
                getImages();
            case NEW:
                viewModifiedListener.dismissProgressBar();
                viewModifiedListener.onListItemUpdated();
                onStateChanged(Constants.State.ABOUT_TO_RUN);
                break;
            case ABOUT_TO_RUN:
                viewModifiedListener.onTimerStatusChanged(Constants
                        .TimerStatus.START);
                break;
            case RUNNING:
                modelChangeListener.onQuestionChanged(Constants
                        .MAX_NO_OF_IMAGES);
                viewModifiedListener.changeStateTo(Constants.State.RUNNING);
                break;
            case LEVEL_SUCCESS:
                onStateChanged(Constants.State.ABOUT_TO_RUN);
                break;
            case STOP:
                break;

        }
    }

    public void onAnswerSelected(int position, View view) {
        if (position == modelChangeListener.onFetchGame().getAnswerPosition()) {
            modelChangeListener.onFetchGame().setCorrectAnsCount
                    (modelChangeListener.onFetchGame().getCorrectAnsCount() +
                            1);
            viewModifiedListener.onAnswerSelected(view, true);
            if (modelChangeListener.onFetchGame().getCorrectAnsCount() %
                    Constants.MAX_NO_OF_IMAGES == 0 && modelChangeListener
                    .onFetchGame().getCorrectAnsCount() != 0) {
                //change level
                getImages();
                onStateChanged(Constants.State.NEW);
            } else {
                onStateChanged(Constants.State.RUNNING);
            }
        } else {
            viewModifiedListener.onAnswerSelected(view, false);
        }
    }

    public OnModelChangeListener getModelChangeListener() {
        return modelChangeListener;
    }

    public void invalidate() {
        modelChangeListener.onItemUpdated(modelChangeListener.onFetchGame()
                .getAnswerPosition(), modelChangeListener.onFetchGame()
                .getRandomPosition());
        viewModifiedListener.onListItemUpdated();
    }

    private void getImages() {
        new ImageListController().getImageList(this);
    }

    @Override
    public void onSuccess(RestResponse response) {
        imageList = (ImageList) response.getResponse();
        this.modelChangeListener.onInitCalled(Utility.formatList(imageList
                .getItems(), Constants.MAX_NO_OF_IMAGES));
        onStateChanged(Constants.State.NEW);
    }

    @Override
    public void onError(String error, int code) {
        viewModifiedListener.dismissProgressBar();
    }
}

package com.sunilsahoo.game.app.detail.presenter;


import com.sunilsahoo.game.app.detail.view.GameDetailView;
import com.sunilsahoo.game.models.Datum;

/**
 * Created by sunilkumarsahoo on 8/21/17.
 */

public class GameDetailPresenter implements IGameDetailPresenter {
    private GameDetailView view;
    private Datum info;

    public GameDetailPresenter(GameDetailView view) {
        this.view = view;
    }

    @Override
    public void show() {
        this.view.show(getData());
    }

    @Override
    public Datum getData() {
        return this.info;
    }

    @Override
    public void setData(Datum info) {
        this.info = info;
    }
}

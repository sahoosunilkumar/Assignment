package com.sunilsahoo.game.app.detail.presenter;

import com.sunilsahoo.game.models.Datum;

/**
 * Created by sunilkumarsahoo on 8/21/17.
 */

public interface IGameDetailPresenter {
    void show();

    Datum getData();

    void setData(Datum date);
}

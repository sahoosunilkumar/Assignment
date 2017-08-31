package com.sunilsahoo.tictactoe.viewmodel;

import android.databinding.ObservableArrayMap;
import android.databinding.ObservableField;

import com.sunilsahoo.tictactoe.model.Board;
import com.sunilsahoo.tictactoe.model.Player;


public class TicTacToeViewModel implements ViewModel {

    public final ObservableArrayMap<String, String> cells = new
            ObservableArrayMap<>();
    public final ObservableField<String> winner = new ObservableField<>();
    private Board model;

    public TicTacToeViewModel() {
        model = new Board();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    public void onResetSelected() {
        model.restart();
        winner.set(null);
        cells.clear();
    }

    public void onClickedCellAt(int row, int col) {
        Player playerThatMoved = model.mark(row, col);
        cells.put("" + row + col, playerThatMoved == null ? null :
                playerThatMoved.toString());
        winner.set(model.getWinner() == null ? null : model.getWinner()
                .toString());
    }

}

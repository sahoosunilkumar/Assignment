package com.sunilsahoo.game.app.detail.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sunilsahoo.game.R;
import com.sunilsahoo.game.app.detail.presenter.GameDetailPresenter;
import com.sunilsahoo.game.app.detail.presenter.IGameDetailPresenter;
import com.sunilsahoo.game.app.home.BaseFragment;
import com.sunilsahoo.game.app.home.view.HomeView;
import com.sunilsahoo.game.models.Datum;

/**
 * Created by sunilkumarsahoo on 8/21/17.
 */

public class GameDetailFragment extends BaseFragment implements GameDetailView {
    public static final String TAG_EXTRA = "extra";
    public static final String TAG_FRAGMENT = GameDetailFragment.class
            .getSimpleName();
    private TextView name;
    private TextView jackpot;
    private TextView date;
    private IGameDetailPresenter presenter;
    private HomeView homeView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new GameDetailPresenter(this);
        homeView = (HomeView) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup
            container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_detail,
                container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        renderView(view);
        handleArguments(getArguments());
        handleSavedInstanceState(savedInstanceState);
    }

    private void renderView(View view) {
        name = (TextView) view.findViewById(R.id.name);
        jackpot = (TextView) view.findViewById(R.id.jackpot);
        date = (TextView) view.findViewById(R.id.date);
    }

    @Override
    public void show(Datum info) {
        name.setText(info.getName());
        jackpot.setText(String.valueOf(info.getJackpot()));
        date.setText(info.getDate());
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.show();
        homeView.removeLastLogin();
    }

    /**
     * retain saved arguments from arguments
     *
     * @param arguments bundle from which required information needs to be
     *                  extracted
     */
    private void handleArguments(Bundle arguments) {
        if (arguments != null) {
            Datum info = arguments.getParcelable(TAG_EXTRA);
            presenter.setData(info);
        }
    }

    /**
     * retain saved information from savedInstanceState
     *
     * @param savedInstanceState bundle from which saved information needs to
     *                           be extracted
     */
    private void handleSavedInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            Datum info = savedInstanceState.getParcelable(TAG_EXTRA);
            presenter.setData(info);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(TAG_EXTRA, presenter.getData());
        super.onSaveInstanceState(outState);
    }
}

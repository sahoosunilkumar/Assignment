package com.sunilsahoo.game.app.playerinfo.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sunilsahoo.game.R;
import com.sunilsahoo.game.app.home.BaseFragment;
import com.sunilsahoo.game.app.home.view.HomeView;
import com.sunilsahoo.game.app.playerinfo.domain.PlayerInfoDomain;
import com.sunilsahoo.game.app.playerinfo.presenter.IPlayerInfoPresenter;
import com.sunilsahoo.game.app.playerinfo.presenter.PlayerInfoPresenter;
import com.sunilsahoo.game.models.PlayerInfoResponse;

import javax.inject.Inject;


public class PlayerInfoFragment extends BaseFragment implements PlayerInfoView {
    private static final String TAG = "PlayerInfoFragment";
    @Inject
    public PlayerInfoDomain service;
    private IPlayerInfoPresenter presenter;
    private ImageView imageView;
    private TextView playerName;
    private TextView lastLogin;
    private TextView balance;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if(savedInstanceState == null) {
        getDeps().inject(this);
        presenter = new PlayerInfoPresenter(service, this);
//        }
//        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup
            container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player_info,
                container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        presenter.getGameInfo();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        renderView(view);
    }


    @Override
    public void showWait() {
        ((HomeView) getActivity()).showWait();
    }

    @Override
    public void removeWait() {
        ((HomeView) getActivity()).removeWait();
    }

    @Override
    public void onFailure(String appErrorMessage) {

        Log.d(TAG, appErrorMessage);
    }

    @Override
    public void onSuccess(PlayerInfoResponse response) {

        playerName.setText(response.getName());
        balance.setText(String.valueOf(response.getBalance()));
        lastLogin.setText(response.getLastLogindate());
        Glide.with(getActivity()).load(response.getAvatarLink()).into
                (imageView);
    }

    @Override
    public void removeLastLogin() {
        lastLogin.setVisibility(View.GONE);
    }

    @Override
    public void showLastLogin() {
        lastLogin.setVisibility(View.VISIBLE);
    }


    public void renderView(View view) {
        imageView = (ImageView) view.findViewById(R.id.image);
        playerName = (TextView) view.findViewById(R.id.name);
        balance = (TextView) view.findViewById(R.id.balance);
        lastLogin = (TextView) view.findViewById(R.id.lastlogin);
    }

}

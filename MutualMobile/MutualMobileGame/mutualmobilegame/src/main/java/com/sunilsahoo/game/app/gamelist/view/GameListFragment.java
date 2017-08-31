package com.sunilsahoo.game.app.gamelist.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunilsahoo.game.R;
import com.sunilsahoo.game.app.detail.view.GameDetailFragment;
import com.sunilsahoo.game.app.gamelist.domain.GameInfoDomain;
import com.sunilsahoo.game.app.gamelist.presenter.HomePresenter;
import com.sunilsahoo.game.app.gamelist.presenter.IHomePresenter;
import com.sunilsahoo.game.app.home.BaseFragment;
import com.sunilsahoo.game.app.home.view.HomeView;
import com.sunilsahoo.game.models.Datum;
import com.sunilsahoo.game.models.GameDataResponse;

import javax.inject.Inject;


public class GameListFragment extends BaseFragment implements GameListView {
    private static final String TAG = "PlayerInfoFragment";
    @Inject
    public GameInfoDomain service;
    private IHomePresenter presenter;
    private RecyclerView list;
    private HomeView homeView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeView = ((HomeView) getActivity());
//        if(savedInstanceState == null) {
        getDeps().inject(this);
        presenter = new HomePresenter(service, this);
//        }
//        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup
            container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_list, container,
                false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        presenter.getGameInfo();
    }

    @Override
    public void onResume() {
        super.onResume();
        homeView.showLastLogin();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        renderView(view);
    }


    @Override
    public void showWait() {
        homeView.showWait();
    }

    @Override
    public void removeWait() {
        homeView.removeWait();
    }

    @Override
    public void onFailure(String appErrorMessage) {

        Log.d(TAG, appErrorMessage);
    }

    @Override
    public void onSuccess(GameDataResponse response) {

        HomeAdapter adapter = new HomeAdapter(response.getData(),
                new HomeAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(Datum info) {

                        GameDetailFragment detailFragment = new
                                GameDetailFragment();
                        Bundle bundle = new Bundle();
                        bundle.putParcelable(GameDetailFragment.TAG_EXTRA,
                                info);
                        detailFragment.setArguments(bundle);
                        ((HomeView) getActivity()).showFragmentInBody
                                (detailFragment, GameDetailFragment
                                        .TAG_FRAGMENT);
                    }
                });

        list.setAdapter(adapter);

    }


    public void renderView(View view) {
        list = (RecyclerView) view.findViewById(R.id.list);
        list.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL));

        list.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

}

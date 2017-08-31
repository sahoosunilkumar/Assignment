package com.sunilsahoo.game.gamelist;

import android.support.v7.widget.RecyclerView;

import com.sunilsahoo.game.app.gamelist.domain.GameInfoDomain;
import com.sunilsahoo.game.app.gamelist.presenter.IHomePresenter;
import com.sunilsahoo.game.app.gamelist.view.GameListFragment;
import com.sunilsahoo.game.app.home.view.HomeActivity;
import com.sunilsahoo.game.app.home.view.HomeView;
import com.sunilsahoo.game.models.GameDataResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil
        .startFragment;

/**
 * Created by sunilkumarsahoo on 8/21/17.
 */

@RunWith(RobolectricTestRunner.class)
public class GameListFragmentTest {
    @Mock
    GameInfoDomain service;
    @Mock
    RecyclerView list;
    @Mock
    GameDataResponse response;
    @Mock
    HomeActivity activity;
    @Mock
    HomeView homeView;
    @InjectMocks
    private GameListFragment gameListFragment;
    @Mock
    private IHomePresenter presenter;

    @Before
    public void setUp() throws Exception {
        gameListFragment = new GameListFragment();

        startFragment(gameListFragment, HomeActivity.class);
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void onActivityCreated() throws Exception {
        gameListFragment.onActivityCreated(null);
        then(presenter).should(times(1)).getGameInfo();
    }

    @Test
    public void onSuccess() throws Exception {
        gameListFragment.onSuccess(response);
        then(list).should(times(1)).setAdapter(any(RecyclerView.Adapter.class));
    }


    @Test
    public void onResume() throws Exception {
        gameListFragment.onResume();
        then(homeView).should(times(1)).showLastLogin();
    }


    @Test
    public void showWait() throws Exception {
        gameListFragment.showWait();
        then(homeView).should(times(1)).showWait();
    }

    @Test
    public void removeWait() throws Exception {
        gameListFragment.removeWait();
        then(homeView).should(times(1)).removeWait();
    }

}

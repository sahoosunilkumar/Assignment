package com.sunilsahoo.game.detail.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.sunilsahoo.game.app.detail.presenter.IGameDetailPresenter;
import com.sunilsahoo.game.app.detail.view.GameDetailFragment;
import com.sunilsahoo.game.app.home.view.HomeActivity;
import com.sunilsahoo.game.app.home.view.HomeView;
import com.sunilsahoo.game.models.Datum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil
        .startFragment;

/**
 * Created by sunilkumarsahoo on 8/21/17.
 */

@RunWith(RobolectricTestRunner.class)
public class GameDetailFragmentTest {
    @InjectMocks
    private GameDetailFragment gameDetailFragment;
    @Mock
    private IGameDetailPresenter presenter;
    @Mock
    private Datum info;
    @Mock
    private TextView name;
    @Mock
    private TextView jackpot;
    @Mock
    private TextView date;
    @Mock
    HomeView homeView;

    @Before
    public void setUp() throws Exception {
        gameDetailFragment = new GameDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(GameDetailFragment.TAG_EXTRA, new Datum("name",
                1, "date"));
        gameDetailFragment.setArguments(bundle);


        startFragment(gameDetailFragment, HomeActivity.class);
        MockitoAnnotations.initMocks(this);

        when(presenter.getData()).thenReturn(info);
        when(info.getDate()).thenReturn("date");
        when(info.getName()).thenReturn("Name");
        when(info.getJackpot()).thenReturn(1L);
    }

    @Test
    public void onResume() throws Exception {
        gameDetailFragment.onResume();
        then(presenter).should(times(1)).show();
        then(homeView).should(times(1)).removeLastLogin();
    }

    @Test
    public void show() throws Exception {
        gameDetailFragment.show(info);
        then(name).should(times(1)).setText(anyString());
        then(jackpot).should(times(1)).setText(anyString());
        then(date).should(times(1)).setText(anyString());
    }

}

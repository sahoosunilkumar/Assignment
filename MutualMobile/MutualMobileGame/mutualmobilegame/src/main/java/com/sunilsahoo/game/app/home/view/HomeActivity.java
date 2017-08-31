package com.sunilsahoo.game.app.home.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.sunilsahoo.game.R;
import com.sunilsahoo.game.app.gamelist.view.GameListFragment;
import com.sunilsahoo.game.app.playerinfo.view.PlayerInfoFragment;
import com.sunilsahoo.game.app.playerinfo.view.PlayerInfoView;

public class HomeActivity extends AppCompatActivity implements HomeView {

    private static final String TAG_GAME_LIST_FRAG = "GAME_LIST_FRAG";
    private static final String TAG_PLAYER_INFO_FRAG = "PLAYER_INFO_FRAG";
    private ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        Fragment gameListFragment = getFragment(TAG_GAME_LIST_FRAG);
        if (gameListFragment == null) {
            gameListFragment = new GameListFragment();
            showFragmentInBody(gameListFragment, TAG_GAME_LIST_FRAG);
        }

        Fragment playerInfoFragment = getFragment(TAG_PLAYER_INFO_FRAG);
        if (playerInfoFragment == null) {
            playerInfoFragment = new PlayerInfoFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id
                    .header, playerInfoFragment, TAG_PLAYER_INFO_FRAG).commit();
        }
    }

    @Override
    public Fragment getFragment(String tag) {
        return getSupportFragmentManager().findFragmentByTag(tag);
    }

    @Override
    public void showFragmentInBody(Fragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction().replace(R.id
                .game_container_fl, fragment, tag).addToBackStack(tag).commit();
    }

    @Override
    public void showWait() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeWait() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void removeLastLogin() {
        ((PlayerInfoView) getFragment(TAG_PLAYER_INFO_FRAG)).removeLastLogin();
    }

    @Override
    public void showLastLogin() {
        ((PlayerInfoView) getFragment(TAG_PLAYER_INFO_FRAG)).showLastLogin();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

}

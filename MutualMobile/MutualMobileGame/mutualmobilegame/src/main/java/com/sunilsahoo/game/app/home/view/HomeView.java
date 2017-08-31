package com.sunilsahoo.game.app.home.view;

import android.support.v4.app.Fragment;

/**
 * Created by sunilkumarsahoo on 8/21/17.
 */

public interface HomeView {
    Fragment getFragment(String tag);

    void showFragmentInBody(Fragment fragment, String tag);

    void showWait();

    void removeWait();

    void removeLastLogin();

    void showLastLogin();
}

package com.sunilsahoo.game.app.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.sunilsahoo.game.app.deps.DaggerDeps;
import com.sunilsahoo.game.app.deps.Deps;
import com.sunilsahoo.game.networking.NetworkModule;

import java.io.File;

/**
 * Created by sunilkumarsahoo on 8/21/17.
 */

public class BaseFragment extends Fragment {
    Deps deps;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File cacheFile = new File(getActivity().getCacheDir(), "responses");
        deps = DaggerDeps.builder().networkModule(new NetworkModule
                (cacheFile)).build();
    }

    public Deps getDeps() {
        return deps;
    }
}

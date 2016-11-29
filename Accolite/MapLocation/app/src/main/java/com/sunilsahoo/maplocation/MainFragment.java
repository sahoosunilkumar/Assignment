/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sunilsahoo.maplocation;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunilsahoo.maplocation.location.SearchFragment;
import com.sunilsahoo.maplocation.maplocator.MapLocatorFragment;

/**
 * Sample fragment that contains tabs of other fragments.
 */
public class MainFragment extends Fragment implements SearchView.ISearchListener{
    private static final String TAG = "MainFragment";
    private SearchView locationSearchView;
    private SearchView ipSearchView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_fragment, container,
                false);
        locationSearchView = (SearchView) v.findViewById(R.id.locationsearchview);
        locationSearchView.setHint(getString(R.string.location_hint));
        locationSearchView.setOnSearchListener(this);
        ipSearchView = (SearchView) v.findViewById(R.id.ipsearchview);
        ipSearchView.setHint(getString(R.string.ip_hint));
        ipSearchView.setOnSearchListener(this);

        return v;
    }

    @Override
    public void onSearch(String query, View view) {
        Log.d(TAG, "view : "+view+" query : "+query);
        if(TextUtils.isEmpty(query)){
            return;
        }
        Fragment navigationFragment;
        navigationFragment = view == locationSearchView? new SearchFragment() : new MapLocatorFragment();
//        navigationFragment = new SearchFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_QUERY, query);
        navigationFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, navigationFragment).commit();
    }
}

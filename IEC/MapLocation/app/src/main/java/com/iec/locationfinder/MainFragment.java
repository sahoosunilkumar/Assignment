package com.iec.locationfinder;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sunilsahoo.inventorycontroller.entity.Result;

import java.util.List;


public class MainFragment extends Fragment implements SearchView
        .ISearchListener, MapLocationView {
    private static final String TAG = "MainFragment";
    FragmentTabsFragment tabFrag;
    private SearchView locationSearchView;
    private MapIPPresenterImpl presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_fragment, container,
                false);
        presenter = new MapIPPresenterImpl(this);
        tabFrag = new FragmentTabsFragment();
        getFragmentManager().beginTransaction().add(R.id.fragment_container,
                tabFrag).commit();
        locationSearchView = (SearchView) v.findViewById(R.id
                .locationsearchview);
        locationSearchView.setHint(getString(R.string.location_hint));
        locationSearchView.setOnSearchListener(this);

        return v;
    }

    @Override
    public void onSearch(String query, View view) {
        Log.d(TAG, "view : " + view + " query : " + query);
        if (TextUtils.isEmpty(query)) {
            return;
        }
        presenter.getItems(query);
    }

    @Override
    public void refresh(List<Result> results) {
        if (tabFrag.currentTab() == null) {
            return;
        } else {
            ((BaseFragment) tabFrag.currentTab()).setResults(results);
        }
        ((BaseFragment) tabFrag.currentTab()).refresh();
    }

    @Override
    public void showToastMessage(final String message) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }
}

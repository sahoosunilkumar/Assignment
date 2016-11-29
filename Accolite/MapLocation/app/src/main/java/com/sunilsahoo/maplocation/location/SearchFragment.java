package com.sunilsahoo.maplocation.location;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sunilsahoo.inventorycontroller.entity.LocationDetail;
import com.sunilsahoo.maplocation.Constants;
import com.sunilsahoo.maplocation.R;

import java.util.List;

public class SearchFragment extends Fragment implements SearchView {
    private SearchAdapter adapter;
    private RecyclerView recyclerView;
    private SearchPresenter presenter;
    private ProgressBar progressBar;
    private String query;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        presenter = new SearchPresenterImpl(this);
        query = getArguments().getString(Constants.KEY_QUERY);
        View view = inflater.inflate(R.layout.frag_recyclerview, null, false);
        progressBar = (ProgressBar) view.findViewById(R.id.progress);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager
                (getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // you can set the first visible item like this:
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getItems(query);
    }

    @Override
    public void onDestroy() {
        if (presenter != null) {
            presenter.onDestroy();
        }
        super.onDestroy();
    }

    @Override
    public void refreshList(final List<LocationDetail> locationList) {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter = new SearchAdapter(locationList);
                recyclerView.setAdapter(adapter);
            }
        });

    }

    @Override
    public void showProgressBar() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (progressBar != null)
                    progressBar.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public void hideProgressBar() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (progressBar != null)
                    progressBar.setVisibility(View.GONE);
            }
        });
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

    @Override
    public Context getViewContext() {
        return getActivity();
    }
}


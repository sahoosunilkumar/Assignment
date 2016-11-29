package com.sunilsahoo.maplocation.deal;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.sunilsahoo.maplocation.R;
import com.sunilsahoo.maplocation.model.Deal;

import java.util.List;

public class DealsFragment extends Fragment implements DealsView{
    private DealAdapter adapter;
    private RecyclerView recyclerView;
    private DealsPresenter presenter;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        presenter = new DealsPresenterImpl(this);
        View view = inflater.inflate(R.layout.frag_recyclerview, null, false);
        progressBar = (ProgressBar) view.findViewById(R.id.progress);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // you can set the first visible item like this:
        layoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return view;
    }


    @Override
    public void onStart() {
        presenter.getItems();
        super.onStart();
    }

    @Override
    public void onDestroy() {
        if(presenter != null) {
            presenter.onDestroy();
        }
        super.onDestroy();
    }
    @Override
    public void refreshList(List<Deal> couponList){
        adapter = new DealAdapter(couponList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showProgressBar() {
        if(progressBar != null)
            progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        if(progressBar != null)
            progressBar.setVisibility(View.GONE);
    }
}


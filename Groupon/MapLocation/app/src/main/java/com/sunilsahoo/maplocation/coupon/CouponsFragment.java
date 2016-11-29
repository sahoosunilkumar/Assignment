package com.sunilsahoo.maplocation.coupon;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.sunilsahoo.maplocation.R;
import com.sunilsahoo.maplocation.model.Coupon;

import java.util.List;


public class CouponsFragment extends Fragment implements CouponView{
    private CouponAdapter adapter;
    private RecyclerView recyclerView;
    private CouponPresenter presenter;
    private ProgressBar progressBar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        presenter = new CouponPresenterImpl(this);
        View view = inflater.inflate(R.layout.frag_recyclerview, null, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        progressBar = (ProgressBar) view.findViewById(R.id.progress);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
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
    public void refreshList(List<Coupon> couponList){
        adapter = new CouponAdapter(couponList);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void showProgressBar() {
        Log.d("Sunil", "progressBar : "+progressBar);
        if(progressBar != null)
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        if(progressBar != null)
        progressBar.setVisibility(View.GONE);
    }


}


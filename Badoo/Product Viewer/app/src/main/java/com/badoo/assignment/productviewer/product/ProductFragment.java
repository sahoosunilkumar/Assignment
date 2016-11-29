package com.badoo.assignment.productviewer.product;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.badoo.assignment.productviewer.ClickListener;
import com.badoo.assignment.productviewer.DividerItemDecoration;
import com.badoo.assignment.productviewer.MainActivity;
import com.badoo.assignment.productviewer.R;
import com.badoo.assignment.productviewer.RecyclerTouchListener;
import com.badoo.assignment.productviewer.model.TransactionMapping;

/**
 * Created by sunilkumarsahoo on 11/16/16.
 */
public class ProductFragment extends Fragment implements ProductView,
        AdapterView
                .OnItemClickListener {
    MainViewHandler handler = new MainViewHandler();
    private ProgressBar progressBar;
    private ProductPresenter presenter;
    private RecyclerView recyclerView;
    private ProductAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.product_content, container,
                false);
        initView(rootView);
        presenter = new ProductPresenterImpl(this, new
                FindProductsInteractorImpl());
        return rootView;
    }

    private void initView(View rootView) {
        progressBar = (ProgressBar) rootView.findViewById(R.id.progress);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        mAdapter = new ProductAdapter(getActivity());

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager
                (getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener
                (getActivity(), recyclerView, new ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        onItemSelected(position, ((TextView)view.findViewById(R.id.titleTV)).getText().toString());
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));

    }

    @Override
    public void onResume() {
        super.onResume();
        onSetTitle();
        presenter.onResume();
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        handler.sendEmptyMessage(Action.SHOW_PROGRESS);
    }

    @Override
    public void hideProgress() {
        handler.sendEmptyMessage(Action.HIDE_PROGRESS);
    }

    @Override
    public void setItems(final TransactionMapping transactionList) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ((ProductAdapter) recyclerView.getAdapter()).updateList
                        (transactionList);
            }
        });
    }

    @Override
    public void showMessage(final String message) {
        handler.sendEmptyMessage(Action.SHOW_ERROR);
    }

    @Override
    public Context getViewContext() {
        return getActivity();
    }

    @Override
    public void onItemSelected(int position, String title) {
        ((MainActivity) getActivity()).launchTransactionFragment(position, title);
    }

    @Override
    public void onSetTitle() {
        ((MainActivity)getActivity()).getSupportActionBar().setTitle(getString(R.string.products_title));

        ((MainActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_launcher);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        presenter.onItemClicked(position);
    }


    private interface Action {
        int HIDE_PROGRESS = 0;
        int SHOW_PROGRESS = 1;
        int SHOW_ERROR = 3;
    }

    private class MainViewHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Action.SHOW_PROGRESS:
                    progressBar.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.INVISIBLE);
                    break;
                case Action.HIDE_PROGRESS:
                    progressBar.setVisibility(View.INVISIBLE);
                    recyclerView.setVisibility(View.VISIBLE);
                    break;
                case Action.SHOW_ERROR:
                    Toast.makeText(getActivity(), getString(R.string.error),
                            Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }
}

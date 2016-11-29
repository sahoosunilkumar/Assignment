package com.badoo.assignment.productviewer.transactions;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.badoo.assignment.productviewer.DividerItemDecoration;
import com.badoo.assignment.productviewer.GBPConverter;
import com.badoo.assignment.productviewer.MainActivity;
import com.badoo.assignment.productviewer.R;
import com.badoo.assignment.productviewer.model.Transaction;

import java.util.List;

/**
 * Created by sunilkumarsahoo on 11/16/16.
 */
public class TransactionFragment extends Fragment implements TransactionView,
        AdapterView
                .OnItemClickListener {
    private ProgressBar progressBar;
    private TransactionPresenter presenter;
    private RecyclerView recyclerView;
    private TransactionsAdapter mAdapter;
    private MainViewHandler handler = new MainViewHandler();
    private int position = 0;
    private String title;
    private String prevTitle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.transaction_content,
                container, false);
        initView(rootView);
        position = getArguments().getInt("position");
        title = getArguments().getString("title");
        presenter = new TransactionPresenterImpl(this, new
                FindTractionsInteractorImpl(), position);
        return rootView;
    }

    private void initView(View rootView) {
        progressBar = (ProgressBar) rootView.findViewById(R.id.progress);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        mAdapter = new TransactionsAdapter();

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager
                (getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

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
    public void onDestroyView() {
        onResetTitle();
        super.onDestroyView();
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
    public void setItems(final GBPConverter converter, final List<Transaction> transactionList) {

        handler.sendEmptyMessage(Action.UPDATE_TOTAL);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ((TransactionsAdapter) recyclerView.getAdapter()).updateList
                        (converter, transactionList);
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
    public void updateTotal() {
        handler.sendEmptyMessage(Action.UPDATE_TOTAL);
    }

    @Override
    public void onSetTitle() {
        prevTitle = (String) ((MainActivity)getActivity()).getSupportActionBar().getTitle();
        ((MainActivity)getActivity()).getSupportActionBar().setTitle(String.format(getString(R.string.transactions_title),title));
        ((MainActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(null);
    }

    @Override
    public void onResetTitle() {
        ((MainActivity)getActivity()).getSupportActionBar().setTitle(prevTitle);
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
        int UPDATE_TOTAL = 4;
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

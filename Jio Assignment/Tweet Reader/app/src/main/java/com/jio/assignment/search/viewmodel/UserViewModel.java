package com.jio.assignment.search.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListProvider;
import android.arch.paging.PagedList;
import android.databinding.ObservableBoolean;

import com.jio.assignment.common.repository.State;
import com.jio.assignment.search.repository.ISearchRepositoryCallback;
import com.jio.assignment.search.repository.SearchRepository;

public class UserViewModel extends ViewModel implements ISearchRepositoryCallback{

    public LiveData<PagedList<Result>> userList;
    private SearchRepository tDataSource;
    private ISearchRepositoryCallback callback;
    private static final int PAGE_SIZE = 20;
    public ObservableBoolean showProgress = new ObservableBoolean();
    public ObservableBoolean showError = new ObservableBoolean();

    public UserViewModel() {
        init();
    }

    private void init() {
        callback = this;
        showError.set(false);
        showProgress.set(false);
        userList = new LivePagedListProvider<Integer, Result>() {
            @Override
            protected DataSource<Integer, Result> createDataSource() {
                tDataSource = new SearchRepository(callback);
                return tDataSource;
            }

        }.create(0, new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(PAGE_SIZE)
                .build());
    }

    @Override
    public void onStateChanged(int state) {
        showProgress.set(state == State.IN_PROGRESS);
        showError.set(state == State.FAILURE);
    }
}

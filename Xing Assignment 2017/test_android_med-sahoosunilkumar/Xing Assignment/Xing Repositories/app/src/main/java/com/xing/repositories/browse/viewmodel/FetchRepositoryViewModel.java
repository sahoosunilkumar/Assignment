package com.xing.repositories.browse.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListProvider;
import android.arch.paging.PagedList;
import android.databinding.ObservableBoolean;

import com.xing.repositories.browse.model.Repository;
import com.xing.repositories.browse.repository.FetchRepository;
import com.xing.repositories.browse.repository.IFetchRepositoryCallback;
import com.xing.repositories.common.repository.State;

public class FetchRepositoryViewModel extends ViewModel implements IFetchRepositoryCallback {

    public LiveData<PagedList<Repository>> userList;
    public ObservableBoolean showProgress = new ObservableBoolean();
    public ObservableBoolean showError = new ObservableBoolean();
    private FetchRepository fetchRepositoryDataSource;
    private IFetchRepositoryCallback callback;

    public FetchRepositoryViewModel() {
        init();
    }

    private void init() {
        callback = this;
        showError.set(false);
        showProgress.set(false);
        userList = new LivePagedListProvider<Integer, Repository>() {
            @Override
            protected DataSource<Integer, Repository> createDataSource() {
                fetchRepositoryDataSource = new FetchRepository(callback);
                return fetchRepositoryDataSource;
            }

        }.create(0, FetchRepository.getConfig());
    }

    @Override
    public void onStateChanged(int state) {
        showProgress.set(state == State.IN_PROGRESS);
        showError.set(state == State.FAILURE);
    }
}

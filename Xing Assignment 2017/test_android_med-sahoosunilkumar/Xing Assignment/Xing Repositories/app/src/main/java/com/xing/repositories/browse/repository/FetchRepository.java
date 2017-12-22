package com.xing.repositories.browse.repository;

import android.arch.paging.DataSource;
import android.arch.paging.PagedList;
import android.arch.paging.TiledDataSource;

import com.xing.repositories.browse.model.Repository;
import com.xing.repositories.common.repository.State;
import com.xing.repositories.networkinterface.NetworkApi;
import com.xing.repositories.networkinterface.NetworkApiService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class FetchRepository extends TiledDataSource<Repository> {
    private static final int PAGE_SIZE = 10;
    private NetworkApiService networkApiService;
    private IFetchRepositoryCallback callback;

    public FetchRepository(IFetchRepositoryCallback callback) {
        networkApiService = NetworkApi.createNetworkApiService();
        this.callback = callback;
    }

    @Override
    public int countItems() {
        return DataSource.COUNT_UNDEFINED;
    }

    @Override
    public List<Repository> loadRange(int startPosition, int pageSize) {
        this.callback.onStateChanged(State.IN_PROGRESS);
        int pageNumber = startPosition / pageSize;
        List<Repository> results = new ArrayList();
        try {
            Response<List<Repository>> response = networkApiService.getUser(pageNumber, pageSize).execute();
            if (response.isSuccessful() && response.code() == 200) {
                results.addAll(response.body());
            }
            this.callback.onStateChanged(State.SUCCESS);
        } catch (IOException e) {
            this.callback.onStateChanged(State.FAILURE);
        }
        return results;
    }

    public static PagedList.Config getConfig() {
        return new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(PAGE_SIZE)
                .setInitialLoadSizeHint(PAGE_SIZE)
                .build();
    }
}

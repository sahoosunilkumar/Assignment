package com.affinitas.profilematcher.search.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.location.Location;
import android.support.annotation.NonNull;

import com.affinitas.profilematcher.filter.model.FilterOptions;
import com.affinitas.profilematcher.search.model.ApiResponse;
import com.affinitas.profilematcher.search.repositories.LocationRepository;
import com.affinitas.profilematcher.search.repositories.LocationRepositoryImpl;
import com.affinitas.profilematcher.search.repositories.SearchRepository;
import com.affinitas.profilematcher.search.repositories.SearchRepositoryImpl;

public class ListUsersViewModel extends ViewModel {

    public ObservableField<Boolean> showProgress = new ObservableField<>(false);
    private MediatorLiveData<ApiResponse> mApiResponse;
    private SearchRepository mIssueRepository;
    private LocationRepository mLocationRepository;
    private FilterOptions filterOptions;

    public ListUsersViewModel() {
        mApiResponse = new MediatorLiveData<>();
        mIssueRepository = new SearchRepositoryImpl();
        mLocationRepository = new LocationRepositoryImpl();
        filterOptions = new FilterOptions();
    }

    @NonNull
    public LiveData<ApiResponse> getApiResponse() {
        return mApiResponse;
    }

    public LiveData<ApiResponse> loadUsers() {
        showProgress.set(true);
        mApiResponse.addSource(
                mIssueRepository.getUsers(filterOptions),
                apiResponse -> {
                    showProgress.set(false);
                    mApiResponse.setValue(apiResponse);
                }
        );
        return mApiResponse;
    }


    public FilterOptions getFilterOptions() {
        return filterOptions;
    }

    public void setFilterOptions(FilterOptions filterOptions) {
        this.filterOptions = filterOptions;
    }

    public void updateCurrentLocation(Location location) {
        mLocationRepository.updateLocation(location);
    }
}
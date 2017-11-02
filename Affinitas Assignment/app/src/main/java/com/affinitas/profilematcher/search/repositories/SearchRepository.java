package com.affinitas.profilematcher.search.repositories;

import android.arch.lifecycle.LiveData;

import com.affinitas.profilematcher.filter.model.FilterOptions;
import com.affinitas.profilematcher.search.model.ApiResponse;

public interface SearchRepository {

    LiveData<ApiResponse> getUsers(FilterOptions filterOptions);
}

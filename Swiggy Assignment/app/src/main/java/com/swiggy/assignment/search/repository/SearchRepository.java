package com.swiggy.assignment.search.repository;

import android.arch.lifecycle.LiveData;

import com.swiggy.assignment.search.model.ApiResponse;

public interface SearchRepository {

    LiveData<ApiResponse> getVariants();
}

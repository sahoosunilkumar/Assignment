package com.swiggy.assignment.search.repository;

import com.swiggy.assignment.common.repository.State;

public interface ISearchRepositoryCallback {
    void onStateChanged(@State int state);

}

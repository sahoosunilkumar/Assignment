package com.careem.assignment.search.repository;

import com.careem.assignment.common.repository.State;

/**
 * Created by sunilkumarsahoo on 11/2/17.
 */

public interface ISearchRepositoryCallback {
    void onStateChanged(@State int state);

}

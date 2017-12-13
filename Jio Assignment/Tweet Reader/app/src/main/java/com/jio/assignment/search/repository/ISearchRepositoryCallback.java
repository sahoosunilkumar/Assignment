package com.jio.assignment.search.repository;

import com.jio.assignment.common.repository.State;

/**
 * Created by sunilkumarsahoo on 11/2/17.
 */

public interface ISearchRepositoryCallback {
    void onStateChanged(@State int state);

}

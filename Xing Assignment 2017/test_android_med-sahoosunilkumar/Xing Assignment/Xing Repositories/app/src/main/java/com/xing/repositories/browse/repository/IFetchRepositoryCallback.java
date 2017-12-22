package com.xing.repositories.browse.repository;

import com.xing.repositories.common.repository.State;

public interface IFetchRepositoryCallback {
    void onStateChanged(@State int state);

}

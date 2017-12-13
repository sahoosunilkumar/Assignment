package com.jio.assignment.common.repository;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;

import static com.jio.assignment.common.repository.State.FAILURE;
import static com.jio.assignment.common.repository.State.IN_PROGRESS;
import static com.jio.assignment.common.repository.State.SUCCESS;
import static java.lang.annotation.RetentionPolicy.SOURCE;

@Retention(SOURCE)
@IntDef({IN_PROGRESS, SUCCESS, FAILURE})
public @interface State {
    int IN_PROGRESS = 1000;
    int SUCCESS = 1001;
    int FAILURE = 1002;
}
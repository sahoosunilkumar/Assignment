package com.badoo.assignment.productviewer.controller;

/**
 * Created by sunilkumarsahoo on 7/5/16.
 */
public interface ITaskListener {
    void onSuccess(Response response);

    void onError(String error, int code);
}

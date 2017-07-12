package com.atlassian.hipchat.assignment.expressionevaluator.common;

/**
 * Created by sunilkumarsahoo on 4/6/17.
 */

public interface ITaskListener {
    void onSuccess(String str);
    void onFailure(String str);
}

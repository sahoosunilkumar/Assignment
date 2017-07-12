package com.atlassian.hipchat.assignment.expressionevaluator.evaluator;

import com.atlassian.hipchat.assignment.model.ChatDetails;

/**
 * Created by sunilkumarsahoo on 4/6/17.
 */

public interface IEvaluator {
    void evaluate(String input, ChatDetails chatDetails);
}

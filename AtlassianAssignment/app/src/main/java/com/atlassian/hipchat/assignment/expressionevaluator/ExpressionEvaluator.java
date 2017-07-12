package com.atlassian.hipchat.assignment.expressionevaluator;

import com.atlassian.hipchat.assignment.expressionevaluator.common.ITaskListener;
import com.atlassian.hipchat.assignment.expressionevaluator.evaluator.EmoticonEvaluator;
import com.atlassian.hipchat.assignment.expressionevaluator.evaluator
        .IEvaluator;
import com.atlassian.hipchat.assignment.expressionevaluator.evaluator
        .MentionEvaluator;
import com.atlassian.hipchat.assignment.expressionevaluator.evaluator
        .URLEvaluator;
import com.atlassian.hipchat.assignment.model.ChatDetails;
import com.google.gson.Gson;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by sunilkumarsahoo on 4/6/17.
 */

public class ExpressionEvaluator {


    private final IEvaluator[] evaluatorArr;
    private final ExecutorService executor;

    /**
     * Private constructor.
     */
    private ExpressionEvaluator() {
        executor = Executors.newSingleThreadExecutor();
        evaluatorArr = new IEvaluator[]{new EmoticonEvaluator(), new URLEvaluator(), new MentionEvaluator()};
    }

    /**
     * @return Singleton instance
     */
    public static ExpressionEvaluator getInstance() {
        return HelperHolder.INSTANCE;
    }
    public void evaluate(final String inputStr, final ITaskListener taskListener){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    ChatDetails chatDetails = new ChatDetails();
                    for (IEvaluator evaluator : evaluatorArr) {
                        evaluator.evaluate(inputStr, chatDetails);
                    }

                    Gson gson = new Gson();
                    String json = gson.toJson(chatDetails);
                    if(taskListener != null){
                        taskListener.onSuccess(json);
                    }
                }catch(Exception ex){
                    if(taskListener != null){
                        taskListener.onFailure(ex.getMessage());
                    }
                }
            }
        });


    }

    /**
     * Provides the lazy-loaded Singleton instance.
     */
    private static class HelperHolder {
        private static final ExpressionEvaluator INSTANCE = new ExpressionEvaluator();
    }

}

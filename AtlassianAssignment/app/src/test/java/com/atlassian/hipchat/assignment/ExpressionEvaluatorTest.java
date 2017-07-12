package com.atlassian.hipchat.assignment;

import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;

import com.atlassian.hipchat.assignment.expressionevaluator.ExpressionEvaluator;
import com.atlassian.hipchat.assignment.expressionevaluator.common
        .ITaskListener;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by sunilkumarsahoo on 4/6/17.
 */

public class ExpressionEvaluatorTest {
    ITaskListener listener;
    String result;

    @Before
    public void init(){
        listener = new ITaskListener() {
            @Override
            public void onSuccess(String str) {
                result = str;
                synchronized (listener) {
                    listener.notifyAll();
                }
            }

            @Override
            public void onFailure(String str) {
                result = str;
                synchronized (listener) {
                    listener.notifyAll();
                }
            }
        };
    }
    @Test
    public void testEmptyInput(){
        String inputMsgStr = null;

        synchronized (listener) {
            try {
                ExpressionEvaluator.getInstance().evaluate(inputMsgStr, listener);
                listener.wait();
                Assert.assertNull(result);
                System.out.println("result : "+ result);

            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }


    @Test
    public void testMention(){
        String inputMsgStr = "@chris you around?";

        synchronized (listener) {
            try {
                ExpressionEvaluator.getInstance().evaluate(inputMsgStr, listener);
                listener.wait();
                Assert.assertEquals(result, "{\"mentions\":[\"chris\"]}");
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    @Test
    public void testEmoticon(){
        String inputMsgStr = "Good morning! (megusta) (coffee)";

        synchronized (listener) {
            try {
                ExpressionEvaluator.getInstance().evaluate(inputMsgStr, listener);
                listener.wait();
                Assert.assertEquals(result, "{\"emoticons\":[\"megusta\"," +
                        "\"coffee\"]}");
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    @Test
    public void testUrl(){
        String inputMsgStr = "Olympics are starting soon; http://www.nbcolympics.com";
        synchronized (listener) {
            try {
                ExpressionEvaluator.getInstance().evaluate(inputMsgStr, listener);
                listener.wait();
                Assert.assertTrue(result.contains("links"));
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    @Test
    public void testUrlEmoticonAndMention(){
        String inputMsgStr = "@bob @john (success) such a cool feature; https://twitter.com/jdorfman/status/430511497475670016";
        synchronized (listener) {
            try {
                ExpressionEvaluator.getInstance().evaluate(inputMsgStr, listener);
                listener.wait();
                Assert.assertTrue(result.contains("links") && result.contains("mentions") && result.contains("emoticons"));
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}

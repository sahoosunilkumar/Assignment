package com.atlassian.hipchat.assignment;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import com.atlassian.hipchat.assignment.expressionevaluator.ExpressionEvaluator;
import com.atlassian.hipchat.assignment.expressionevaluator.common
        .ITaskListener;
import com.atlassian.hipchat.assignment.model.IView;

/**
 * Created by sunilkumarsahoo on 4/6/17.
 */

public class ChatViewModel extends BaseObservable implements ITaskListener {
    public ObservableField<String> chatMsgStr = new ObservableField<>();
    public ObservableField<Boolean> showProgress = new ObservableField<>(false);
    public ObservableField<String> inputMsgStr = new ObservableField<>();
    private IView view;
    public ChatViewModel(IView view){
        this.view = view;
    }
    public void onValidationClicked(){
        showProgress.set(true);
        ExpressionEvaluator.getInstance().evaluate(inputMsgStr.get(), this);
    }

    @Override
    public void onSuccess(String str) {
        showProgress.set(false);
        chatMsgStr.set(str);
    }

    @Override
    public void onFailure(String str) {
        showProgress.set(false);
        chatMsgStr.set("");
        view.onError(str);
    }
}

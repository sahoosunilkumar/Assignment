package com.atlassian.hipchat.assignment;

import android.databinding.ObservableField;

import com.atlassian.hipchat.assignment.model.IView;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by sunilkumarsahoo on 4/6/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ChatViewModelTest {
    @Mock
    IView iView;

    @InjectMocks
    ChatViewModel viewModel = new ChatViewModel(iView);



    @Test
    public void testShowProgressInitial(){
        Assert.assertTrue(viewModel.showProgress.get()==false);
    }

    @Test
    public void testValidationError(){
        viewModel.onValidationClicked();
        Assert.assertEquals(viewModel.chatMsgStr.get(), null);
    }

    @Test
    public void testValidationSuccess(){
        viewModel.onSuccess("success");
        Assert.assertFalse(viewModel.showProgress.get());
    }
}

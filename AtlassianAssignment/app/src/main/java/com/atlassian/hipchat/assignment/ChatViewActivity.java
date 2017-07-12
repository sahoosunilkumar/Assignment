package com.atlassian.hipchat.assignment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.atlassian.hipchat.assignment.databinding.ActivityMainBinding;
import com.atlassian.hipchat.assignment.model.IView;

public class ChatViewActivity extends AppCompatActivity implements IView{

    private ChatViewModel chatViewModel;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chatViewModel = new ChatViewModel(this);
        binding = DataBindingUtil.setContentView(this, R.layout
                .activity_main);
        binding.setChatViewModel(chatViewModel);
    }

    @Override
    public void onError(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                binding.inputET.setError(message);
            }
        });
    }
}

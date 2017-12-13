package com.jio.assignment.search.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jio.assignment.R;
import com.jio.assignment.databinding.ActivityMainBinding;
import com.jio.assignment.moviedetail.view.MovieDetailActivity;
import com.jio.assignment.search.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity implements MainView {


    private UserAdapter userUserAdapter;
    private UserViewModel viewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        binding.setUserViewModel(viewModel);
        initView();

    }

    @Override
    public void onItemClick(View view, int position) {
        MovieDetailActivity.launch(view.getContext(), userUserAdapter.getItemAt(position));
    }

    @Override
    public void initView() {
        binding.userList.setLayoutManager(new LinearLayoutManager(this));
        binding.userList.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        binding.userList.setHasFixedSize(true);

        userUserAdapter = new UserAdapter(this);

        viewModel.userList.observe(this, pagedList -> {
            userUserAdapter.setList(pagedList);
        });
        binding.userList.setAdapter(userUserAdapter);
    }
}

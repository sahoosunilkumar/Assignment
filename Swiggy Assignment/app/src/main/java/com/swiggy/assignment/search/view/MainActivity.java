package com.swiggy.assignment.search.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.swiggy.assignment.R;
import com.swiggy.assignment.databinding.ActivityMainBinding;
import com.swiggy.assignment.search.model.VariantGroup;
import com.swiggy.assignment.search.model.Variants;
import com.swiggy.assignment.search.model.Variation;
import com.swiggy.assignment.search.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {


    private VariationAdapter variationAdapter;
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

    }

    @Override
    public void initView() {
        variationAdapter = new VariationAdapter(new ArrayList<>());
        variationAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(Variation variation) {
                boolean exclude = viewModel.addToCart(variation);
                Log.d("MainActivity", "exclude : "+exclude);
            }

            @Override
            public void onSubheaderClicked(int position) {

            }
        });
        binding.userList.setLayoutManager(new LinearLayoutManager(this));
        binding.userList.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        binding.userList.setHasFixedSize(true);

        binding.userList.setAdapter(variationAdapter);

        viewModel.loadVariants();
        // Handle changes emitted by LiveData
        viewModel.getApiResponse().observe(this, apiResponse -> {
            if (apiResponse.getError() != null) {
                handleError(apiResponse.getError());
            } else {
                handleResponse(apiResponse.getIssues().getVariants());
            }
        });
    }

    private void handleResponse(Variants retrieveMatches) {
        variationAdapter.setList(getVariations(retrieveMatches.getVariantGroups()));
    }

    private void handleError(Throwable error) {
        //TODO
    }

    private List<Variation> getVariations(List<VariantGroup> variantGroups) {
        List<Variation> list = new ArrayList<>();
        for (VariantGroup group : variantGroups) {
            list.addAll(group.getVariations());
        }
        return list;
    }
}

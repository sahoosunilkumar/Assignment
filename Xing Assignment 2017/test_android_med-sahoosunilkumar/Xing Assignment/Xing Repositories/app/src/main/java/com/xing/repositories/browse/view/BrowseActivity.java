package com.xing.repositories.browse.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.xing.repositories.R;
import com.xing.repositories.browse.model.Repository;
import com.xing.repositories.browse.viewmodel.FetchRepositoryViewModel;
import com.xing.repositories.common.view.ItemPickerDialogFragment;
import com.xing.repositories.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class BrowseActivity extends AppCompatActivity implements BrowseView {


    private RepositoryListAdapter repositoryListAdapter;
    private FetchRepositoryViewModel viewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(FetchRepositoryViewModel.class);
        binding.setRepositoryViewModel(viewModel);
        initView();

    }

    @Override
    public boolean onLongClick(View view, int position) {
        return launchRepositoryViewer(repositoryListAdapter.getItemAt(position));
    }

    @Override
    public void initView() {
        binding.userList.setLayoutManager(new LinearLayoutManager(this));
        binding.userList.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        binding.userList.setHasFixedSize(true);

        repositoryListAdapter = new RepositoryListAdapter(this);

        viewModel.userList.observe(this, pagedList -> {
            repositoryListAdapter.setList(pagedList);
        });
        binding.userList.setAdapter(repositoryListAdapter);
    }

    @Override
    public void onItemSelected(ItemPickerDialogFragment fragment, ItemPickerDialogFragment.Item item, int index) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(item.getStringValue()));
        startActivity(intent);
    }

    private boolean launchRepositoryViewer(Repository repository) {
        ArrayList<ItemPickerDialogFragment.Item> pickerItems = new ArrayList<>();
        pickerItems.add(new ItemPickerDialogFragment.Item(getString(R.string.repository), repository.getHtmlUrl()));
        pickerItems.add(new ItemPickerDialogFragment.Item(getString(R.string.owner_url), repository.getOwner().getHtmlUrl()));

        ItemPickerDialogFragment dialog = ItemPickerDialogFragment.newInstance(
                getString(R.string.repository_select_title),
                pickerItems,
                -1
        );
        dialog.show(getFragmentManager(), "ItemPicker");
        return true;
    }
}

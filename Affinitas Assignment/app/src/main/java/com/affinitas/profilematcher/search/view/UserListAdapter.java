package com.affinitas.profilematcher.search.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.affinitas.profilematcher.R;
import com.affinitas.profilematcher.common.view.BaseAdapter;
import com.affinitas.profilematcher.databinding.ItemRowBinding;
import com.affinitas.profilematcher.search.model.User;

public class UserListAdapter extends BaseAdapter<User> {

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemRowBinding itemRowBinding = ItemRowBinding.bind(LayoutInflater
                .from(parent
                        .getContext())
                .inflate(R.layout.item_row, parent, false));

        return new ItemHolder(itemRowBinding);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        ItemRowBinding itemRowBinding = holder.getBinding();
        itemRowBinding.setUser(getItem(position));

        itemRowBinding.executePendingBindings();
    }


}

package com.logitech.assignment.inventory.favorite;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.logitech.assignment.inventory.R;
import com.logitech.assignment.inventory.databinding.ItemRowBinding;
import com.tesco.viewmodelbinding.ViewModelRecyclerViewAdapter;

/**
 * Created by sunilkumarsahoo on 12/17/16.
 */

public class ItemAdapter extends ViewModelRecyclerViewAdapter<ItemRowBinding> {
    private ObservableList<Item> list = new ObservableArrayList<>();

    public ItemAdapter(ObservableList<Item> list) {
        super(R.layout.item_row);
        this.list = list;
        this.list.addOnListChangedCallback
                (super.getObservableCallback());
    }

    @Override
    public void onBindViewHolder(com.tesco.viewmodelbinding.common
                                             .BaseRecyclerViewHolder<ItemRowBinding> holder, int position) {
        holder.binding.setItem(list.get
                (position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
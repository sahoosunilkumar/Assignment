package com.shopback.assignment.movies.dashboard;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.util.Log;

import com.shopback.assignment.movies.utilities.ImageHelper;
import com.shopback.assignment.movies.R;
import com.shopback.assignment.movies.databinding.ItemRowBinding;
import com.sunilsahoo.inventorycontroller.entity.Movie;
import com.sunilsahoo.viewmodelbinding.ViewModelRecyclerViewAdapter;

public class MovieAdapter extends ViewModelRecyclerViewAdapter<ItemRowBinding> {
    private ObservableList<Movie> list = new ObservableArrayList<>();

    private MoviesViewModel viewModel;
    public MovieAdapter(ObservableList<Movie> list, MoviesViewModel viewModel) {
        super(R.layout.item_row);
        this.list = list;
        this.viewModel = viewModel;
        this.list.addOnListChangedCallback
                (super.getObservableCallback());
    }

    @Override
    public void onBindViewHolder(com.sunilsahoo.viewmodelbinding.common
                                             .BaseRecyclerViewHolder<ItemRowBinding> holder, int position) {
        holder.binding.setMovie(list.get
                (position));
//        load next set of item when scrolled to last element
        if ((position >= getItemCount() - 1))
            this.viewModel.getItems();
        String imageUrl = holder.binding.getMovie().getPosterPath();
        ImageHelper.setImageFromUrl(holder.binding.icon.getContext(), ImageHelper.getLogoPath(imageUrl), holder.binding.icon);
        Log.d(MovieAdapter.class.getSimpleName(), ""+holder.binding);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
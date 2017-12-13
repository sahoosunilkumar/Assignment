package com.swiggy.assignment.search.view;

import com.swiggy.assignment.search.model.Variation;

import java.util.List;

/**
 * Created by sunilkumarsahoo on 12/8/17.
 */

public class VariationAdapter extends BaseAdapter {

    public VariationAdapter(List<Variation> itemList) {
        super(itemList);
    }

    @Override
    public boolean onPlaceSubheaderBetweenItems(int position) {
        final Variation movie = movieList.get(position);
        final Variation nextMovie = movieList.get(position + 1);

        return !movie.getName().substring(0, 1).equals(nextMovie.getName().substring(0, 1));
    }

    @Override
    public void onBindItemViewHolder(final MovieViewHolder holder, final int position) {
        final Variation variation = movieList.get(position);

        holder.textName.setText(variation.getName());
        holder.textPrice.setText(String.valueOf(variation.getPrice()));
        holder.textInStock.setText(String.valueOf(variation.getInStock()));

        holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClicked(variation));
    }

    @Override
    public void onBindSubheaderViewHolder(SubheaderHolder subheaderHolder, int nextItemPosition) {
        super.onBindSubheaderViewHolder(subheaderHolder, nextItemPosition);
//        final Variation nextMovie = movieList.get(nextItemPosition);
//        subheaderHolder.mSubheaderText.setText(nextMovie.getName().substring(0, 1));
    }

}
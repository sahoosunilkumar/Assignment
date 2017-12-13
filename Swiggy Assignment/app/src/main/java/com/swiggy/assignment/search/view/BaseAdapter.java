package com.swiggy.assignment.search.view;

/**
 * Created by sunilkumarsahoo on 12/8/17.
 */

import android.support.annotation.CallSuper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.swiggy.assignment.R;
import com.swiggy.assignment.search.model.Variation;
import com.zhukic.sectionedrecyclerview.SectionedRecyclerViewAdapter;

import java.util.List;

public abstract class BaseAdapter extends SectionedRecyclerViewAdapter<BaseAdapter.SubheaderHolder, BaseAdapter.MovieViewHolder> {

    List<Variation> movieList;
    OnItemClickListener onItemClickListener;

    public BaseAdapter(List<Variation> itemList) {
        super();
        this.movieList = itemList;
    }

    public void setList(List<Variation> itemList) {
        this.movieList = itemList;
        notifyDataChanged();
    }

    @Override
    public MovieViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        return new MovieViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_variant_list, parent, false));
    }

    @Override
    public SubheaderHolder onCreateSubheaderViewHolder(ViewGroup parent, int viewType) {
        return new SubheaderHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header, parent, false));
    }

    @Override
    @CallSuper
    public void onBindSubheaderViewHolder(SubheaderHolder subheaderHolder, int nextItemPosition) {

        boolean isSectionExpanded = isSectionExpanded(getSectionIndex(subheaderHolder.getAdapterPosition()));

//        if (isSectionExpanded) {
//            subheaderHolder.mArrow.setImageDrawable(ContextCompat.getDrawable(subheaderHolder.itemView.getContext(), R.drawable.ic_keyboard_arrow_up_black_24dp));
//        } else {
//            subheaderHolder.mArrow.setImageDrawable(ContextCompat.getDrawable(subheaderHolder.itemView.getContext(), R.drawable.ic_keyboard_arrow_down_black_24dp));
//        }

        subheaderHolder.itemView.setOnClickListener(v -> onItemClickListener.onSubheaderClicked(subheaderHolder.getAdapterPosition()));

    }

    @Override
    public int getItemSize() {
        return movieList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClicked(Variation variation);

        void onSubheaderClicked(int position);
    }

    static class SubheaderHolder extends RecyclerView.ViewHolder {

        TextView mSubheaderText;

        SubheaderHolder(View itemView) {
            super(itemView);
            this.mSubheaderText = (TextView) itemView.findViewById(R.id.subheaderText);
        }

    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        TextView textName;
        TextView textPrice;
        TextView textInStock;

        MovieViewHolder(View itemView) {
            super(itemView);
            this.textName = (TextView) itemView.findViewById(R.id.name);
            this.textPrice = (TextView) itemView.findViewById(R.id.price);
            this.textInStock = (TextView) itemView.findViewById(R.id.instock);
        }
    }

}
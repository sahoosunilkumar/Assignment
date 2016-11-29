package com.sunilsahoo.maplocation.deal;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunilsahoo.maplocation.R;
import com.sunilsahoo.maplocation.databinding.DealRowBinding;
import com.sunilsahoo.maplocation.model.Deal;

import java.util.List;

public class DealAdapter
        extends RecyclerView.Adapter
        <DealAdapter.DealItemViewHolder> {

    private List<Deal> dealList;

    public DealAdapter(List<Deal> modelData) {
        if (modelData == null) {
            throw new IllegalArgumentException("modelData must not be null");
        }
        dealList = modelData;
    }

    @Override
    public DealItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int
            viewType) {

        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.deal_row, viewGroup, false);
        DealRowBinding rowBinding = DealRowBinding.bind(itemView);
        return new DealItemViewHolder(rowBinding);
    }

    @Override
    public void onBindViewHolder(DealItemViewHolder viewHolder, int position) {
        Deal deal = dealList.get(position);
        viewHolder.dealRowBinding.setDeal(deal);
    }

    @Override
    public int getItemCount() {
        return dealList.size();
    }


    public final static class DealItemViewHolder extends RecyclerView
            .ViewHolder {
        DealRowBinding dealRowBinding;
        public DealItemViewHolder(DealRowBinding dealRowBinding) {
            super(dealRowBinding.getRoot());
            this.dealRowBinding = dealRowBinding;
        }
    }
}

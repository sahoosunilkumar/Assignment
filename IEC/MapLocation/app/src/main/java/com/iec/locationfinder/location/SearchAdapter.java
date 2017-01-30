package com.iec.locationfinder.location;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunilsahoo.inventorycontroller.entity.Result;
import com.iec.locationfinder.R;
import com.iec.locationfinder.databinding.DealRowBinding;

import java.util.List;

public class SearchAdapter
        extends RecyclerView.Adapter
        <SearchAdapter.DealItemViewHolder> {

    private List<Result> dealList;

    public SearchAdapter(List<Result> modelData) {
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
        Result locationDetail = dealList.get(position);
        viewHolder.dealRowBinding.setLocationDetail(locationDetail);
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

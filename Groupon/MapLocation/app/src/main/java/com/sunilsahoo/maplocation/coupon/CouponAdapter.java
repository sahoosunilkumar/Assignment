package com.sunilsahoo.maplocation.coupon;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunilsahoo.maplocation.R;
import com.sunilsahoo.maplocation.databinding.CouponRowBinding;
import com.sunilsahoo.maplocation.model.Coupon;

import java.util.List;

public class CouponAdapter
        extends RecyclerView.Adapter
        <CouponAdapter.CouponItemViewHolder> {

    private List<Coupon> couponList;

    public CouponAdapter(List<Coupon> modelData) {
        if (modelData == null) {
            throw new IllegalArgumentException("modelData must not be null");
        }
        couponList = modelData;
    }

    @Override
    public CouponItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int
            viewType) {

        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.coupon_row, viewGroup, false);
        CouponRowBinding rowBinding = CouponRowBinding.bind(itemView);
        return new CouponItemViewHolder(rowBinding);
    }

    @Override
    public void onBindViewHolder(CouponItemViewHolder viewHolder, int position) {
        Coupon coupon = couponList.get(position);
        viewHolder.couponRowBinding.setCoupon(coupon);
    }

    @Override
    public int getItemCount() {
        return couponList.size();
    }


    public final static class CouponItemViewHolder extends RecyclerView
            .ViewHolder {
        CouponRowBinding couponRowBinding;
        public CouponItemViewHolder(CouponRowBinding couponRowBinding) {
            super(couponRowBinding.getRoot());
            this.couponRowBinding = couponRowBinding;
        }
    }
}

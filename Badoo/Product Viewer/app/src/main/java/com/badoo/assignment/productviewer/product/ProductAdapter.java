package com.badoo.assignment.productviewer.product;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.badoo.assignment.productviewer.R;
import com.badoo.assignment.productviewer.model.TransactionMapping;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter
        .MyViewHolder> {

    private TransactionMapping transactionList;
    private Context context;

    public ProductAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_row, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.titleTV.setText(transactionList.getKey(position));
        holder.infoTV.setText(String.format(context.getResources().getString
                (R.string.transaction_info), transactionList.getList
                (position).size()));
    }

    @Override
    public int getItemCount() {
        return ((transactionList != null) && (transactionList
                .getTransactionBeanList() != null)) ? transactionList
                .getTransactionBeanList().size() : 0;
    }

    public void updateList(TransactionMapping transactionList) {
        this.transactionList = transactionList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTV;
        public TextView infoTV;

        public MyViewHolder(View view) {
            super(view);
            titleTV = (TextView) view.findViewById(R.id.titleTV);
            infoTV = (TextView) view.findViewById(R.id.infoTV);
        }
    }
}

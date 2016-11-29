package com.badoo.assignment.productviewer.transactions;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.badoo.assignment.productviewer.GBPConverter;
import com.badoo.assignment.productviewer.R;
import com.badoo.assignment.productviewer.model.Transaction;

import java.util.List;

public class TransactionsAdapter extends RecyclerView
        .Adapter<TransactionsAdapter
        .MyViewHolder> {

    private List<Transaction> transactionList;
    private GBPConverter converter;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_row, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.titleTV.setText(transactionList.get(position).getCurrency() +
                " " +
                transactionList.get(position).getAmount());
        holder.infoTV.setText(converter.getBaseType() + "" +
                " " +
                Float.parseFloat(transactionList.get(position).getAmount()) * converter.get(transactionList.get(position).getCurrency()));
    }

    @Override
    public int getItemCount() {
        return (transactionList == null) ? 0 : transactionList.size();
    }

    public void updateList(GBPConverter converter, List<Transaction> transactionList) {
        this.transactionList = transactionList;
        this.converter = converter;
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

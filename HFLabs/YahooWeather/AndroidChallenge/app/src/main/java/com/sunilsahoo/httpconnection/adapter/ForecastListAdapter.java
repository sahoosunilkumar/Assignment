/**
 *
 * Responsibility : Adapter for Recycler View
 */
package com.sunilsahoo.httpconnection.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunilsahoo.httpconnection.R;
import com.sunilsahoo.httpconnection.databinding.ForecastListItemBinding;
import com.sunilsahoo.inventorycontoller.entity.Forecast;

import java.util.ArrayList;
import java.util.List;

public class ForecastListAdapter extends RecyclerView.Adapter<ForecastListAdapter
        .ForecastItemViewHolder> {

    private List<Forecast> forecastList = new ArrayList<>();
    private Context context;
    

    class ForecastItemViewHolder extends RecyclerView.ViewHolder {

        ForecastListItemBinding forecastListItemBinding;


        public ForecastItemViewHolder(ForecastListItemBinding forecastListItemBinding) {
            super(forecastListItemBinding.getRoot());
            this.forecastListItemBinding = forecastListItemBinding;
        }
    }


    public ForecastListAdapter(Context context, List<Forecast> forecastList) {
        this.forecastList = forecastList==null? new ArrayList<Forecast>() : forecastList;
        this.context = context;
    }

    @Override
    public ForecastItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ForecastItemViewHolder(ForecastListItemBinding.bind(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.forecast_list_item, parent, false)));
    }

    @Override
    public void onBindViewHolder(ForecastItemViewHolder holder, int position) {
        Forecast forecast = forecastList.get(position);
        ((TextView)(holder.forecastListItemBinding.getRoot().findViewById(R.id.tempInfo))).setText(String.format(context.getResources().getString(R.string.temperature_format), forecast.getHigh(), forecast.getLow()));
        holder.forecastListItemBinding.setForecast(forecast);
//        temperature = (TextView) view.findViewById(R.id.tempInfo);
//        forecastIV = (ImageView) view.findViewById(R.id.forecastIV);
//        holder.temperature.setText(String.format(context.getResources().getString(R.string.temperature_format), forecast.getHigh(), forecast.getLow()));
    }

    @Override
    public int getItemCount() {
        return forecastList == null ? 0 : forecastList.size();
    }

    public void updateList(List<Forecast> forecastList){
        this.forecastList.clear();
        this.forecastList.addAll(forecastList);
        notifyDataSetChanged();
    }
}


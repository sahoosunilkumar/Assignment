package com.sunilsahoo.httpconnection;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sunilsahoo.httpconnection.adapter.ForecastListAdapter;
import com.sunilsahoo.httpconnection.databinding.WeatherNowFragmentBinding;
import com.sunilsahoo.httpconnection.view.DividerItemDecoration;
import com.sunilsahoo.inventorycontoller.entity.Channel;
import com.sunilsahoo.inventorycontoller.entity.Forecast;

import java.util.List;


public class ForecastFragment extends Fragment {

    private static final String TAG = ForecastFragment.class.getName();
    private RecyclerView mRecyclerView;
    private ForecastListAdapter mAdapter;
    private WeatherInfoUpdateListener listener;
    public ForecastFragment() {
        // Required empty public constructor
    }

    public static ForecastFragment newInstance() {
        Log.d(TAG, "inside newInstance");
        ForecastFragment fragment = new ForecastFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "inside oncreateview");
        View view = inflater.inflate(R.layout.forecast_list_content, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);


        mAdapter = new ForecastListAdapter(getContext(), getForecastList());
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager
                (getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                LinearLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private List<Forecast> getForecastList(){
        try {
            return ((WeatherActivity) getActivity()).getWeatherInfo().getQuery().getResults().getChannel().getItem().getForecast();

        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    private void registerListener(){
        listener = new WeatherInfoUpdateListener();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ActionType.ACTION_WEATHER_INFO_UPDATED);
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(listener,filter);
    }

    private void unregisterListener(){
        if(listener != null) {
            LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(listener);

        }
    }

    private void invalidate(){
        mAdapter.updateList(getForecastList());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        registerListener();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        unregisterListener();
    }


    class WeatherInfoUpdateListener extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            invalidate();
        }
    }
}

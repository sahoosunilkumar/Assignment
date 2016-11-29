package com.sunilsahoo.httpconnection;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunilsahoo.httpconnection.databinding.WeatherNowFragmentBinding;
import com.sunilsahoo.inventorycontoller.entity.Channel;
import com.sunilsahoo.inventorycontoller.entity.WeatherInfo;

import java.net.URLEncoder;


public class WeatherNowFragment extends Fragment {

    private static final String TAG = WeatherNowFragment.class.getName();
    private WeatherNowFragmentBinding fragmentContentBinding;
    private WeatherInfoUpdateListener listener;
    public WeatherNowFragment() {
        // Required empty public constructor
    }

    private ImageView imageview;
    private TextView currentTemp;
    private TextView maxMinTemp;
    private TextView humidityTV;
    private TextView windTV;
    private TextView sunriseTV;
    private TextView sunsetTV;
    private TextView visibilityTV;
    public static WeatherNowFragment newInstance() {
        Log.d(TAG, "inside newInstance");
        WeatherNowFragment fragment = new WeatherNowFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "inside oncreateview");
        View view = inflater.inflate(R.layout.weather_now_fragment, container, false);
        fragmentContentBinding = WeatherNowFragmentBinding.bind(view);
        fragmentContentBinding.setChannel(getChannel());
        imageview = (ImageView) view.findViewById(R.id.image);
        currentTemp = (TextView) view.findViewById(R.id.currentTemp);
        maxMinTemp = (TextView) view.findViewById(R.id.maxMinTemp);
        humidityTV = (TextView) view.findViewById(R.id.humidityTV);
        windTV = (TextView) view.findViewById(R.id.windTV);
        sunriseTV = (TextView) view.findViewById(R.id.sunriseTV);
        sunsetTV = (TextView) view.findViewById(R.id.sunsetTV);
        visibilityTV = (TextView) view.findViewById(R.id.visibilityTV);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        registerListener();
    }

    private void invalidate(){
        fragmentContentBinding.setChannel(getChannel());
        currentTemp.setText(String.format(getResources().getString(R.string.current_temp), getChannel().getItem().getCondition().getTemp()+" "+getChannel().getUnits().getTemperature()));
        humidityTV.setText(String.format(getResources().getString(R.string.humidity), getChannel().getAtmosphere().getHumidity()));
        visibilityTV.setText(String.format(getResources().getString(R.string.visibility), getChannel().getAtmosphere().getVisibility()));
        sunriseTV.setText(String.format(getResources().getString(R.string.sunrise), getChannel().getAstronomy().getSunrise()));
        sunsetTV.setText(String.format(getResources().getString(R.string.sunset), getChannel().getAstronomy().getSunset()));
        windTV.setText(String.format(getResources().getString(R.string.wind), getChannel().getWind().getSpeed()+" "+getChannel().getUnits().getSpeed()));
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

    @Override
    public void onDetach() {
        super.onDetach();
        unregisterListener();
    }


    //    @Override
//    public void onResume() {
//        super.onResume();
//        setProductImage();
//    }
//
//    public void setProductImage(){
//        Log.d(TAG, "inside " +URLEncoder.encode(product.getImagePath())+" image path : "+product.getImagePath());
//
//        Picasso.with(this.getActivity())
//                .load(product.getImagePath())
//                .fit()
//                .error(R.mipmap.ic_launcher)
//                .into(this.imageview);
//    }

    private Channel getChannel(){
        try {
            return ((WeatherActivity) getActivity()).getWeatherInfo().getQuery().getResults().getChannel();

        }catch (Exception ex){
            return null;
        }
    }

    class WeatherInfoUpdateListener extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive broadcast");
            invalidate();
        }
    }

}

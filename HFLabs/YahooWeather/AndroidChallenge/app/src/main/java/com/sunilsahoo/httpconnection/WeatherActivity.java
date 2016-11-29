package com.sunilsahoo.httpconnection;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sunilsahoo.httpconnection.adapter.TitleAdapter;
import com.sunilsahoo.inventorycontoller.DeviceDetailController;
import com.sunilsahoo.inventorycontoller.ITaskListener;
import com.sunilsahoo.inventorycontoller.Parser;
import com.sunilsahoo.inventorycontoller.entity.RestResponse;
import com.sunilsahoo.inventorycontoller.entity.WeatherInfo;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class WeatherActivity extends AppCompatActivity implements ITaskListener, ViewPager.OnPageChangeListener {

    private static final String TAG = WeatherActivity.class.getName();
    private ViewPager mPager;
    private CustomPagerAdapter2 mAdapter;
    private List<Fragment> fragments = new ArrayList<>();
    private WeatherInfo weatherInfo;
    private Gallery myHorizontalListView;
    private TitleAdapter titleAdapter;
    private EditText locationET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diy_approach);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onSuccess(RestResponse response) {

        weatherInfo = (WeatherInfo) response.getResponse();
        Log.d(TAG, "onSuccess : "+weatherInfo);
        handler.sendEmptyMessage(ActionType.UPDATE_LIST);
    }

    @Override
    public void onError(String error, int code) {
        Log.d(TAG, "onError : "+error);
        handler.sendEmptyMessage(ActionType.SHOW_ERROR);

    }

    private final Handler handler = new Handler() {

        public void handleMessage(Message msg) {

            switch (msg.what) {
                case ActionType.UPDATE_LIST:
                    LocalBroadcastManager.getInstance(WeatherActivity.this).sendBroadcast(new Intent(ActionType.ACTION_WEATHER_INFO_UPDATED));
                    Log.d(TAG, "send broadcast");
                    break;
                case ActionType.SHOW_ERROR:
                    Toast.makeText(WeatherActivity.this, getString(R
                            .string.error), Toast.LENGTH_SHORT)
                            .show();
                    break;
                default:
                    Log.d(TAG, "unhandled action type");
            }


        }
    };

    private void init(){
        locationET = (EditText) findViewById(R.id.locationET);
        mPager = (ViewPager) findViewById(R.id.pager);
        fragments.add(WeatherNowFragment.newInstance());
        fragments.add(ForecastFragment.newInstance());
        mAdapter = new CustomPagerAdapter2(getSupportFragmentManager(), fragments);
        mPager.setAdapter(mAdapter);
        mPager.addOnPageChangeListener(this);
        myHorizontalListView = (Gallery)findViewById(R.id.horizontallistview);

        titleAdapter = new TitleAdapter(this);
        myHorizontalListView.setAdapter(titleAdapter);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int
            positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        myHorizontalListView.setSelection(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    static class CustomPagerAdapter2 extends FragmentStatePagerAdapter {

        List<Fragment> mFrags = new ArrayList<>();

        public CustomPagerAdapter2(FragmentManager fm, List<Fragment> frags) {
            super(fm);
            mFrags = frags;
        }

        @Override
        public Fragment getItem(int position) {
            int index = position % mFrags.size();
            return mFrags.get(index);
        }

        @Override
        public int getCount() {
            return mFrags.size();
        }

    }

    /**
     * retrieves device list from server
     *
     * @param view
     */
    public void getWeather(View view) {
        new DeviceDetailController().getWeatherInfo(locationET.getText().toString(),this);
    }

    public WeatherInfo getWeatherInfo(){
        return weatherInfo;
    }
}

package com.sixt.cars.app.home.view;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.sixt.cars.R;
import com.sixt.cars.app.home.BaseFragment;
import com.sixt.cars.app.home.presenter.HomePresenter;
import com.sixt.cars.app.home.presenter.IHomePresenter;
import com.sixt.cars.app.inventorylist.view.InventoryListFragment;
import com.sixt.cars.app.maplocator.InventoryMapFragment;
import com.sixt.inventorycontroller.entity.CarInfo;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements HomeView {
    private static final String TAG = HomeActivity.class.getSimpleName();
    private IHomePresenter presenter;
    private TabPagerAdapter mAdapter;
    private ViewPager mPager;
    private ProgressBar loadingProgressBar;

    private Handler mainHandler;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;
        mainHandler = new Handler(getMainLooper());
        setContentView(R.layout.activity_home);
        loadingProgressBar = (ProgressBar) findViewById(R.id.loading);
        presenter = new HomePresenter(this);
        presenter.getCars();
    }

    @Override
    public void showWait() {
        mainHandler.post(new Runnable() {

            @Override
            public void run() {
                loadingProgressBar.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public void removeWait() {
        mainHandler.post(new Runnable() {

            @Override
            public void run() {
                loadingProgressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onFailure(String appErrorMessage) {
        Log.d(TAG, "onFailure : " + appErrorMessage);
    }

    @Override
    public void onSuccess(final ArrayList<CarInfo> carListResponse) {
        mainHandler.post(new Runnable() {

            @Override
            public void run() {
                mAdapter = new TabPagerAdapter(getSupportFragmentManager(),
                        context, getFragments(carListResponse));
                mPager = (ViewPager) findViewById(R.id.pager);
                mPager.setAdapter(mAdapter);
                mPager.setCurrentItem(0);
            }
        });


    }

    @Override
    public List<Fragment> getFragments(ArrayList<CarInfo> carListResponse) {
        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        try {
            fragmentArrayList.add(BaseFragment.createInstance
                    (InventoryListFragment.class, carListResponse));

            fragmentArrayList.add(BaseFragment.createInstance
                    (InventoryMapFragment.class, carListResponse));
        } catch (Exception ex) {
            onFailure(ex.getMessage());
        }

        return fragmentArrayList;
    }


}

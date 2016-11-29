package com.landmarkshopping.assignment.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.landmarkshopping.assignment.Constants;
import com.landmarkshopping.assignment.model.GridDetail;
import com.landmarkshopping.assignment.model.Product;

import java.util.List;

public class CarouselGridPagerAdapter extends FragmentStatePagerAdapter {
    private GridDetail mLauncherGridDetail = null;
    private List<Product> totalGridList = null;

    public CarouselGridPagerAdapter(FragmentManager fm,
                                    GridDetail launcherGridDetail,
                                    List<Product> totalGridList) {

        super(fm);
        this.mLauncherGridDetail = launcherGridDetail;
        this.totalGridList = totalGridList;
    }

    @Override
    public int getCount() {
        return mLauncherGridDetail.getTotalNoOfFragments();
    }


    @Override
    public Fragment getItem(int position) {
        GridDetail launcherGridDetail = new GridDetail(mLauncherGridDetail);
        Bundle args = new Bundle();

        int maxIndex = totalGridList.size() - 1;
        int startPosition = Math.min((position
                * launcherGridDetail.getItemCountPerFragment()), maxIndex);

        int endPosition = Math.min(((position + 1)
                * launcherGridDetail.getItemCountPerFragment() - 1), maxIndex);

        launcherGridDetail.setStartPosition(startPosition);
        launcherGridDetail.setEndPosition(endPosition);
        launcherGridDetail.setCurrentGridPosition(position);
        args.putSerializable(Constants.KEY_BUNDLE_LAUNCHER_DETAIL,
                launcherGridDetail);
//		Log.d("Sunil", "size : "+totalGridList.size()+" position : "+position);

        GridFragment f = new GridFragment();
        f.setArguments(args);
        return f;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}

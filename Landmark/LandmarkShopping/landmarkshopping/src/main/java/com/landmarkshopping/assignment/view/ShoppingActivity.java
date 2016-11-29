package com.landmarkshopping.assignment.view;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.landmarkshopping.assignment.Constants;

public class ShoppingActivity extends AppCompatActivity {

    private ShoppingFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager mMgr = getSupportFragmentManager();
        mFragment = (ShoppingFragment) mMgr
                .findFragmentByTag(Constants.TAG_SHOPPING_FRAG);
//        Log.d("tag", "inside fragment "+mFragment);
        if (mFragment == null) {
            mFragment = new ShoppingFragment();
            mMgr.beginTransaction()
                    .add(android.R.id.content, mFragment, Constants
                            .TAG_SHOPPING_FRAG)
                    .commit();
        }

    }

}

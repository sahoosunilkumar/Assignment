package com.badoo.assignment.productviewer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.badoo.assignment.productviewer.product.ProductFragment;
import com.badoo.assignment.productviewer.transactions.TransactionFragment;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Log.d(TAG, "toolbar : "+getSupportActionBar());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        setContentView(R.layout.activity_main);

        ProductFragment productFragment = new ProductFragment();
        getFragmentManager().beginTransaction().add(R.id
                .frag_container, productFragment).commit();

    }


    public void launchTransactionFragment(int position, String title) {
        TransactionFragment transactionFragment = new TransactionFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        bundle.putString("title", title);
        transactionFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().addToBackStack
                (null).add(R.id
                .frag_container, transactionFragment).commit();
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed : " + getFragmentManager()
                .getBackStackEntryCount());
        if (getFragmentManager().getBackStackEntryCount() >= 1) {
            getFragmentManager().popBackStack();
        }
        else super.onBackPressed();
    }
}

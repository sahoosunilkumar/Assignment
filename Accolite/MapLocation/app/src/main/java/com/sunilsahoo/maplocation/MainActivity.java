package com.sunilsahoo.maplocation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getFragmentManager().findFragmentById(android.R.id.content) ==
                null) {
            MainFragment tabFrag = new MainFragment();
            getFragmentManager().beginTransaction().add(android.R.id.content,
                    tabFrag).commit();
        }
    }
}

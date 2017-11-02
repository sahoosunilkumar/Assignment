package com.affinitas.profilematcher.filter.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

import com.affinitas.profilematcher.R;
import com.affinitas.profilematcher.common.utils.Constants;
import com.affinitas.profilematcher.filter.model.FilterOptions;
import com.yahoo.mobile.client.android.util.rangeseekbar.RangeSeekBar;

import static com.affinitas.profilematcher.common.utils.Constants.ACTION_APPLY_FILTER_SUCCESS;
import static com.affinitas.profilematcher.common.utils.Constants.KEY_FILTER;

public class FilterActivity extends AppCompatActivity {

    private FilterOptions mFilterOptions;
    private CheckBox filterPhoto;
    private CheckBox filterFavourites;
    private CheckBox filterContacts;
    private RangeSeekBar filterAge;
    private RangeSeekBar filterHeight;
    private RangeSeekBar filterCompatibility;
    private RangeSeekBar filterDistance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        mFilterOptions = retrieveData(getIntent(), savedInstanceState);
        init();
    }

    private void init() {
        filterPhoto = findViewById(R.id.filter_photo);
        filterPhoto.setChecked(mFilterOptions.isHasPhoto());
        filterFavourites = findViewById(R.id.filter_favourites);
        filterFavourites.setChecked(mFilterOptions.isFavourite());
        filterContacts = findViewById(R.id.filter_contacts);
        filterContacts.setChecked(mFilterOptions.isHasContact());
        filterAge = findViewById(R.id.filter_age);
        filterAge.setSelectedMinValue(mFilterOptions.getMinAge());
        filterAge.setSelectedMaxValue(mFilterOptions.getMaxAge());
        filterAge.setNotifyWhileDragging(true);
        filterHeight = findViewById(R.id.filter_height);
        filterHeight.setNotifyWhileDragging(true);
        filterHeight.setSelectedMinValue(mFilterOptions.getMinHeight());
        filterHeight.setSelectedMaxValue(mFilterOptions.getMaxHeight());
        filterCompatibility = findViewById(R.id.filter_compatibility);
        filterCompatibility.setNotifyWhileDragging(true);
        filterCompatibility.setSelectedMinValue(mFilterOptions.getMinCompatibilityScore());
        filterCompatibility.setSelectedMaxValue(mFilterOptions.getMaxCompatibilityScore());
        filterDistance = findViewById(R.id.filter_distance);
        filterDistance.setNotifyWhileDragging(true);
        filterDistance.setSelectedMaxValue(mFilterOptions.getMaxDistance());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Make sure to call the super method so that the states of our views are saved
        super.onSaveInstanceState(outState);
        updateFilterOptions();
        // Save our own state now
        outState.putParcelable(Constants.KEY_FILTER, mFilterOptions);
    }

    public void applyFilter(View view) {
        updateFilterOptions();
        Intent intent = new Intent();
        intent.putExtra(Constants.KEY_FILTER, mFilterOptions);
        setResult(ACTION_APPLY_FILTER_SUCCESS, intent);
        finish();
    }

    private FilterOptions retrieveData(Intent intent, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            return getIntent().getParcelableExtra(KEY_FILTER);
        } else {
            return getIntent().getParcelableExtra(KEY_FILTER);
        }
    }

    public void updateFilterOptions() {
        mFilterOptions.setHasPhoto(filterPhoto.isChecked());
        mFilterOptions.setFavourite(filterFavourites.isChecked());
        mFilterOptions.setHasContact(filterContacts.isChecked());
        mFilterOptions.setMinAge((Integer) filterAge.getSelectedMinValue());
        mFilterOptions.setMaxAge((Integer) filterAge.getSelectedMaxValue());

        mFilterOptions.setMinCompatibilityScore((Integer) filterCompatibility.getSelectedMinValue());
        mFilterOptions.setMaxCompatibilityScore((Integer) filterCompatibility.getSelectedMaxValue());
        mFilterOptions.setMaxDistance((Integer) filterDistance.getSelectedMaxValue());

        mFilterOptions.setMinHeight((Integer) filterHeight.getSelectedMinValue());
        mFilterOptions.setMaxHeight((Integer) filterHeight.getSelectedMaxValue());
    }
}

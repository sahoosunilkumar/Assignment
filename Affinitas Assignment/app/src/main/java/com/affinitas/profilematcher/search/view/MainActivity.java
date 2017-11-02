package com.affinitas.profilematcher.search.view;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.affinitas.profilematcher.R;
import com.affinitas.profilematcher.common.utils.Constants;
import com.affinitas.profilematcher.databinding.ActivityMainBinding;
import com.affinitas.profilematcher.filter.view.FilterActivity;
import com.affinitas.profilematcher.search.model.RetrieveMatches;
import com.affinitas.profilematcher.search.viewmodel.ListUsersViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity implements MainView {

    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;
    private UserListAdapter mAdapter;
    private ListUsersViewModel mViewModel;
    private ActivityMainBinding activityMainBinding;
    private FusedLocationProviderClient mFusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ListUsersViewModel.class);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout
                .activity_main);
        activityMainBinding.setListUsersViewModel(mViewModel);
        init();
        activityMainBinding.filter.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), FilterActivity.class);
            intent.putExtra(Constants.KEY_FILTER, mViewModel.getFilterOptions());
            startActivityForResult(intent, Constants.ACTION_APPLY_FILTER_REQUEST);
        });
        mViewModel.loadUsers();
        // Handle changes emitted by LiveData
        mViewModel.getApiResponse().observe(this, apiResponse -> {
            if (apiResponse.getError() != null) {
                handleError(apiResponse.getError());
            } else {
                handleResponse(apiResponse.getIssues());
            }
        });

        getLocation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void handleResponse(RetrieveMatches retrieveMatches) {
        mAdapter.clearItems();
        if (retrieveMatches != null && retrieveMatches.getUsers().size() > 0) {
            activityMainBinding.welcomeMsg.setText(getString(R.string.welcome_msg, retrieveMatches.getUsers().size()));
            mAdapter.addItems(retrieveMatches.getUsers());
        } else {
            activityMainBinding.welcomeMsg.setText(getString(R.string.welcome_msg, 0));
            showEmpty();
        }
    }

    private void handleError(Throwable error) {
        mAdapter.clearItems();
        showError();
    }


    @Override
    public void showError() {
        Toast.makeText(this, getString(R.string.match_error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEmpty() {
        Toast.makeText(this, getString(R.string.match_not_found), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getLocation() {
        if (!checkPermissions()) {
            requestPermissions();
        } else {
            getLastLocation();
        }
    }

    public void init() {
        mAdapter = new UserListAdapter();
        activityMainBinding.recyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        activityMainBinding.recyclerView.hasFixedSize();
        activityMainBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(
                activityMainBinding.recyclerView.getContext(), LinearLayoutManager.VERTICAL
        );
        activityMainBinding.recyclerView.addItemDecoration(mDividerItemDecoration);
        activityMainBinding.recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Constants.ACTION_APPLY_FILTER_SUCCESS) {
            mViewModel.setFilterOptions(data.getParcelableExtra(Constants.KEY_FILTER));
            mViewModel.loadUsers();
        }
    }


    /**
     * Provides a simple way of getting a device's location and is well suited for
     * applications that do not require a fine-grained location and that do not need location
     * updates. Gets the best and most recent location currently available, which may be null
     * in rare cases when a location is not available.
     * <p>
     * Note: this method should be called after location permission has been granted.
     */
    @SuppressWarnings("MissingPermission")
    private void getLastLocation() {
        mFusedLocationClient.getLastLocation()
                .addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            mViewModel.updateCurrentLocation(task.getResult());
                        }
                    }
                });
    }

    /**
     * Return the current state of the permissions needed.
     */
    private boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    private void startLocationPermissionRequest() {
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                REQUEST_PERMISSIONS_REQUEST_CODE);
    }

    private void requestPermissions() {
        boolean shouldProvideRationale =
                ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION);

        // Provide an additional rationale to the user. This would happen if the user denied the
        // request previously, but didn't check the "Don't ask again" checkbox.
        if (shouldProvideRationale) {

            startLocationPermissionRequest();

        } else {
            // Request permission. It's possible this can be auto answered if device policy
            // sets the permission in a given state or the user denied the permission
            // previously and checked "Never ask again".
            startLocationPermissionRequest();
        }
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted.
                getLastLocation();
            }
        }
    }
}

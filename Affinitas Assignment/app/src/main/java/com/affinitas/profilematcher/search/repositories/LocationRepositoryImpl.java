package com.affinitas.profilematcher.search.repositories;

import android.location.Location;

import com.affinitas.profilematcher.filter.rules.FilterManager;

public class LocationRepositoryImpl implements LocationRepository {

    public LocationRepositoryImpl() {
    }


    @Override
    public void updateLocation(Location location) {
        FilterManager.getInstance().updateCurrentLocation(location);
    }
}

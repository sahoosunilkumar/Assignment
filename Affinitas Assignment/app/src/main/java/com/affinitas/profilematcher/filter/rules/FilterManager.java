package com.affinitas.profilematcher.filter.rules;


import android.location.Location;

import com.affinitas.profilematcher.filter.model.FilterOptions;
import com.affinitas.profilematcher.search.model.City;
import com.affinitas.profilematcher.search.model.User;

import java.util.ArrayList;
import java.util.List;

public final class FilterManager {

    private static final double DEFAULT_LATITUDE = 53.801277;
    private static final double DEFAULT_LONGITUDE = -1.548567;
    private FilterOptions filterOptions;
    private List<BaseFilter> filterList;
    private City city;

    private FilterManager() {
        registerFilters();
        city = new City();
        city.setLat(DEFAULT_LATITUDE);
        city.setLon(DEFAULT_LONGITUDE);
    }

    /**
     * @return Singleton instance
     */
    public static FilterManager getInstance() {
        return HelperHolder.INSTANCE;
    }

    public FilterOptions getFilterOptions() {
        return filterOptions;
    }

    public void setFilterOptions(FilterOptions filterOptions) {
        this.filterOptions = filterOptions;
    }

    public void updateCurrentLocation(Location location) {
        city.setLat(location.getLatitude());
        city.setLon(location.getLongitude());
    }

    public City getCurrentLocation() {
        return city;
    }

    public List<User> apply(List<User> users) {
        for (BaseFilter filter : filterList) {
            users = filter.apply(users);
        }
        return users;
    }

    private void registerFilters() {
        filterList = new ArrayList<>();
        filterList.add(new AgeFilter());
        filterList.add(new CompatibilityFilter());
        filterList.add(new DistanceFilter());
        filterList.add(new FavouriteFilter());
        filterList.add(new HeightFilter());
        filterList.add(new InContactFilter());
        filterList.add(new PhotoFilter());
    }

    /**
     * Provides the lazy-loaded Singleton instance.
     */
    private static class HelperHolder {
        private static final FilterManager INSTANCE = new FilterManager();
    }
}

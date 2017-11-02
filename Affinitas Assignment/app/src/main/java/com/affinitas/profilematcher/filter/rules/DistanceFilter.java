package com.affinitas.profilematcher.filter.rules;

import com.affinitas.profilematcher.common.utils.DistanceCalculator;
import com.affinitas.profilematcher.search.model.User;

public class DistanceFilter extends BaseFilter {
    @Override
    boolean filter(User user) {
        return getDistanceFrom(user) <= getFilterOptions().getMaxDistance() && isApplied() || !isApplied();
    }

    @Override
    boolean isApplied() {
        return !(getFilterOptions().getMaxDistance() == Integer.MAX_VALUE);
    }

    private double getDistanceFrom(User user) {
        return DistanceCalculator.distance(FilterManager.getInstance().getCurrentLocation().getLat(), FilterManager.getInstance().getCurrentLocation().getLon(), user.getCity().getLat(), user.getCity().getLon());
    }
}

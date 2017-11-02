package com.affinitas.profilematcher.filter.rules;

import com.affinitas.profilematcher.common.utils.Constants;
import com.affinitas.profilematcher.search.model.User;

public class CompatibilityFilter extends BaseFilter {

    @Override
    boolean filter(User user) {
        return (user.getCompatibilityScore() * 100 > getFilterOptions().getMinCompatibilityScore() && user.getCompatibilityScore() * 100 <= getFilterOptions().getMaxCompatibilityScore() && isApplied()) || !isApplied();
    }

    @Override
    boolean isApplied() {
        return !(getFilterOptions().getMaxCompatibilityScore() == Constants.MAX_COMPATIBILITY && getFilterOptions().getMinCompatibilityScore() == Constants.MIN_COMPATIBILITY);
    }
}

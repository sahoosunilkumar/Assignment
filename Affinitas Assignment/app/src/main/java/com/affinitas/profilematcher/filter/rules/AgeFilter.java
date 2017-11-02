package com.affinitas.profilematcher.filter.rules;

import com.affinitas.profilematcher.common.utils.Constants;
import com.affinitas.profilematcher.search.model.User;

public class AgeFilter extends BaseFilter {
    @Override
    boolean filter(User user) {
        return (user.getAge() >= getFilterOptions().getMinAge() && user.getAge() <= getFilterOptions().getMaxAge() && isApplied()) || !isApplied();
    }

    @Override
    boolean isApplied() {
        return !(getFilterOptions().getMaxAge() == Constants.MAX_AGE && getFilterOptions().getMinAge() == Constants.MIN_AGE);
    }
}

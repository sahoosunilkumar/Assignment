package com.affinitas.profilematcher.filter.rules;

import com.affinitas.profilematcher.common.utils.Constants;
import com.affinitas.profilematcher.search.model.User;

public class HeightFilter extends BaseFilter {
    @Override
    boolean filter(User user) {
        return (user.getHeightInCm() >= getFilterOptions().getMinHeight() && user.getHeightInCm() <= getFilterOptions().getMaxHeight() && isApplied()) || !isApplied();
    }

    @Override
    boolean isApplied() {
        return !(getFilterOptions().getMaxHeight() == Constants.MAX_HEIGHT && getFilterOptions().getMinHeight() == Constants.MIN_HEIGHT);
    }

}

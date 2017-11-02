package com.affinitas.profilematcher.filter.rules;

import com.affinitas.profilematcher.search.model.User;

public class FavouriteFilter extends BaseFilter {

    @Override
    boolean filter(User user) {
        return (getFilterOptions().isFavourite() && isApplied()) || (!isApplied());
    }

    @Override
    boolean isApplied() {
        return getFilterOptions().isFavourite();
    }
}

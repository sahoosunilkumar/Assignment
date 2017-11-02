package com.affinitas.profilematcher.filter.rules;

import com.affinitas.profilematcher.search.model.User;

/**
 * Created by sunilkumarsahoo on 10/25/17.
 */

public class InContactFilter extends BaseFilter {

    @Override
    boolean filter(User user) {
        return (user.getContactsExchanged() > 0 && isApplied()) || (!isApplied());
    }

    @Override
    boolean isApplied() {
        return getFilterOptions().isHasContact();
    }
}

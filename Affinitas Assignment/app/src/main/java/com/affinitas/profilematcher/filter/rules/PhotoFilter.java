package com.affinitas.profilematcher.filter.rules;

import android.text.TextUtils;

import com.affinitas.profilematcher.search.model.User;

/**
 * Created by sunilkumarsahoo on 10/25/17.
 */

public class PhotoFilter extends BaseFilter {

    @Override
    boolean filter(User user) {
        return (!TextUtils.isEmpty(user.getMainPhoto()) && isApplied()) || (!isApplied());
    }

    @Override
    boolean isApplied() {
        return getFilterOptions().isHasPhoto();
    }
}

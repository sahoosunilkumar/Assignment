package com.affinitas.profilematcher.filter.rules;

import com.affinitas.profilematcher.filter.model.FilterOptions;
import com.affinitas.profilematcher.search.model.User;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseFilter implements Filter {
    abstract boolean filter(User user);

    abstract boolean isApplied();

    @Override
    public List<User> apply(List<User> userList) {
        List<User> filteredUserList = new ArrayList<>();
        for (User user : userList) {
            if (filter(user)) {
                filteredUserList.add(user);
            }
        }
        return filteredUserList;
    }

    protected FilterOptions getFilterOptions() {
        return FilterManager.getInstance().getFilterOptions();
    }
}

package com.affinitas.profilematcher.filter.rules;

import com.affinitas.profilematcher.search.model.User;

import java.util.List;

/**
 * Created by sunilkumarsahoo on 10/25/17.
 */

public interface Filter {
    List<User> apply(List<User> userList);
}
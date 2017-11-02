package com.affinitas.profilematcher.search.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RetrieveMatches {

    @SerializedName("matches")
    private List<User> users = null;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}

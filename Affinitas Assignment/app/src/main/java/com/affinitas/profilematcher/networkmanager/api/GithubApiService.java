package com.affinitas.profilematcher.networkmanager.api;

import com.affinitas.profilematcher.search.model.RetrieveMatches;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface GithubApiService {
    @GET
    Call<RetrieveMatches> getIssues(@Url String url);
}

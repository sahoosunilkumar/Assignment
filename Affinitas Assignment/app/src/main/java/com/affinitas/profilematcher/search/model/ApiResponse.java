package com.affinitas.profilematcher.search.model;

public class ApiResponse {
    private RetrieveMatches retrieveMatches;
    private Throwable error;

    public ApiResponse(RetrieveMatches retrieveMatches) {
        this.retrieveMatches = retrieveMatches;
        this.error = null;
    }

    public ApiResponse(Throwable error) {
        this.error = error;
        this.retrieveMatches = null;
    }

    public RetrieveMatches getIssues() {
        return retrieveMatches;
    }

    public Throwable getError() {
        return error;
    }
}

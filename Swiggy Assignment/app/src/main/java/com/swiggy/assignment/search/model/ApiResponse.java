package com.swiggy.assignment.search.model;

public class ApiResponse {
    private FetchVariantsResponse retrieveMatches;
    private Throwable error;

    public ApiResponse(FetchVariantsResponse retrieveMatches) {
        this.retrieveMatches = retrieveMatches;
        this.error = null;
    }

    public ApiResponse(Throwable error) {
        this.error = error;
        this.retrieveMatches = null;
    }

    public FetchVariantsResponse getIssues() {
        return retrieveMatches;
    }

    public Throwable getError() {
        return error;
    }
}

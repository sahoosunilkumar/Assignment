package com.artoo.eventmanager.search.model;

public class ApiResponse {
    private Event event;
    private Throwable error;

    public ApiResponse(Event event) {
        this.event = event;
        this.error = null;
    }

    public ApiResponse(Throwable error) {
        this.error = error;
        this.event = null;
    }

    public Event getEvent() {
        return event;
    }

    public Throwable getError() {
        return error;
    }
}
